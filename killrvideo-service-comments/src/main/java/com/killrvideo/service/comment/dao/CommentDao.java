package com.killrvideo.service.comment.dao;


import com.killrvideo.dse.dao.DseDaoSupport;
import com.killrvideo.dse.dto.ResultListPage;
import com.killrvideo.service.comment.dto.Comment;
import com.killrvideo.service.comment.dto.CommentByUser;
import com.killrvideo.service.comment.dto.CommentByUser_Table;
import com.killrvideo.service.comment.dto.CommentByVideo;
import com.killrvideo.service.comment.dto.CommentByVideo_Table;
import com.killrvideo.service.comment.dto.QueryCommentByUser;
import com.killrvideo.service.comment.dto.QueryCommentByVideo;
import java.nio.ByteBuffer;
import java.time.Instant;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import ma.markware.charybdis.batch.Batch;
import ma.markware.charybdis.dsl.DslFunctions;
import ma.markware.charybdis.dsl.Record;
import ma.markware.charybdis.dsl.select.SelectExtraWhereExpression;
import ma.markware.charybdis.model.field.SelectableField;
import ma.markware.charybdis.query.PageRequest;
import ma.markware.charybdis.query.PageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

/**
 * Implementation of queries and related to {@link Comment} objects within DataStax Enterprise.
 * Comments are store in 2 tables and all queries are performed against Apache Cassandra.
 * 
 * @author DataStax Developer Advocates team.
 */
@Repository
public abstract class CommentDao extends DseDaoSupport {

    private static Logger LOGGER = LoggerFactory.getLogger(CommentDao.class);
    
    /** Data Modelling. */
    public static final String TABLENAME_COMMENTS_BY_VIDEO  = "comments_by_video";
    public static final String TABLENAME_COMMENTS_BY_USER   = "comments_by_user";

    private static final SelectableField<Long> COMMENT_BY_USER_COMMENTID_WRITETIME_FIELD = DslFunctions.writetime(CommentByUser_Table.commentid);
    private static final SelectableField<Long> COMMENT_BY_VIDEO_COMMENTID_WRITETIME_FIELD = DslFunctions.writetime(CommentByVideo_Table.commentid);

    public CommentDao() {
        super();
    }

    public void insertComment(final Comment comment) {
        // Execute statement (nothing to return, just INSERT here)
        buildBatchStatementInsertComment(comment).execute();
    }

    public CompletableFuture<Void> insertCommentAsync(final Comment comment) {
        return buildBatchStatementInsertComment(comment).executeAsync();
    }

    public ResultListPage<Comment> findCommentsByVideoId(final QueryCommentByVideo query) {
        PageResult<Record> recordPageResult = buildStatementVideoComments(query).fetchPage(PageRequest.fromString(query.getPageState().orElse(null),
                                                                                                                  query.getPageSize()));
        return mapToCommentList(recordPageResult);
    }

    public CompletableFuture <ResultListPage<Comment>> findCommentsByVideosIdAsync(final QueryCommentByVideo query) {
        return buildStatementVideoComments(query).fetchPageAsync(PageRequest.fromString(query.getPageState().orElse(null),
                                                                                        query.getPageSize()))
                                                 .thenApplyAsync(this::mapToCommentList); // Iterate on resultSet to build result bean
    }

    public ResultListPage<Comment> findCommentsByUserId(final QueryCommentByUser query) {
        // Like before but inlined as a boss 
        PageResult<Record> recordPageResult = buildStatementUserComments(query).fetchPage(PageRequest.fromString(query.getPageState().orElse(null),
                                                                                                                 query.getPageSize()));
        return mapToCommentList(recordPageResult);
    }

    public CompletableFuture<ResultListPage<Comment>> findCommentsByUserIdAsync(final QueryCommentByUser query) {
        return buildStatementUserComments(query).fetchPageAsync(PageRequest.fromString(query.getPageState().orElse(null),
                                                                                  query.getPageSize()))
                                                .thenApplyAsync(this::mapToCommentList);
    }

    public void updateComment(final Comment c) {
        Assert.notNull(c, "Comment object is required");
        Assert.notNull(c.getUserid(), "userid is required to update a comment");
        Assert.notNull(c.getVideoid(), "videoid is required to update a comment");
        Assert.notNull(c.getCommentid(), "commentid is required to update a comment");
        Batch batch = cqlTemplate.batch().unlogged();
        cqlTemplate.crud(batch).update(CommentByUser_Table.comments_by_user, new CommentByUser(c));
        cqlTemplate.crud(batch).update(CommentByVideo_Table.comments_by_video, new CommentByVideo(c));
        batch.execute();
    }
    
    /**
     * Delete a comment.
     * 
     * @param comment
     * 		entity with identifiers
     */
    public void deleteComment(final Comment comment) {
        // Check parameterss
        Assert.notNull(comment, 			   "Comment object is required");
        Assert.notNull(comment.getUserid(),    "userId is required to delete a comment");
        Assert.notNull(comment.getVideoid(),   "VideoId is required to delete a comment");
        Assert.notNull(comment.getCommentid(), "CommetId is required to delete a comment");

        // Run as LWT Batch
        Batch batch = cqlTemplate.batch().logged();
        cqlTemplate.crud(batch).delete(CommentByVideo_Table.comments_by_video, new CommentByVideo(comment));
        cqlTemplate.crud(batch).delete(CommentByUser_Table.comments_by_user, new CommentByUser(comment));
        batch.execute();
    }

    private Batch buildBatchStatementInsertComment(Comment comment) {
        Batch batch = cqlTemplate.batch().withConsistency(ma.markware.charybdis.model.option.ConsistencyLevel.LOCAL_QUORUM)
                                 .unlogged().usingTimestamp(System.currentTimeMillis());
        cqlTemplate.crud(batch).create(CommentByVideo_Table.comments_by_video, new CommentByVideo(comment));
        cqlTemplate.crud(batch).create(CommentByUser_Table.comments_by_user, new CommentByUser(comment));
        return batch;
    }

    private ResultListPage<Comment> mapToCommentList(PageResult<Record> rs) {
    	ResultListPage<Comment> result = new ResultListPage<>();
        for (final Record record : rs.getResults()) {
            Comment c = new Comment();
            c.setComment(record.get(CommentByVideo_Table.comment));
            c.setUserid(record.get(CommentByVideo_Table.userid));
            c.setCommentid(record.get(CommentByVideo_Table.commentid));
            c.setVideoid(record.get(CommentByVideo_Table.videoid));
            c.setDateOfComment(Instant.ofEpochMilli(record.get(COMMENT_BY_USER_COMMENTID_WRITETIME_FIELD)));
            result.getResults().add(c);
        }
        result.setPagingState(
                Optional.ofNullable(rs.getPagingState())
                        .map(ByteBuffer::toString));
        return result;
    }

    private SelectExtraWhereExpression buildStatementUserComments(final QueryCommentByUser query) {

        SelectExtraWhereExpression queryExpression = cqlTemplate.dsl()
                                                                .withConsistency(ma.markware.charybdis.model.option.ConsistencyLevel.LOCAL_QUORUM)
                                                                .select(CommentByUser_Table.userid, CommentByUser_Table.commentid,
                                                                        COMMENT_BY_USER_COMMENTID_WRITETIME_FIELD)
                                                                .from(CommentByUser_Table.comments_by_user)
                                                                .where(CommentByUser_Table.userid.eq(query.getUserId()));

        if (query.getCommentId()
                 .isPresent()) {
            queryExpression.and(CommentByUser_Table.commentid.eq(query.getCommentId()
                                                                      .get()));
        }
        return queryExpression;
    }

    private SelectExtraWhereExpression buildStatementVideoComments(final QueryCommentByVideo query) {
        SelectExtraWhereExpression queryExpression = cqlTemplate.dsl()
                                                                .withConsistency(ma.markware.charybdis.model.option.ConsistencyLevel.QUORUM)
                                                                .select(CommentByVideo_Table.videoid, CommentByVideo_Table.commentid,
                                                                        COMMENT_BY_VIDEO_COMMENTID_WRITETIME_FIELD)
                                                                .from(CommentByVideo_Table.comments_by_video)
                                                                .where(CommentByVideo_Table.videoid.eq(query.getVideoId()));

        if (query.getCommentId().isPresent()) {
            queryExpression.and(CommentByVideo_Table.commentid.eq(query.getCommentId().get()));
        }
        return queryExpression;
    }
}
