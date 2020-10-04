package com.killrvideo.service.comment.dto;

import com.killrvideo.model.CommonConstants;
import com.killrvideo.service.comment.dao.CommentDseDao;
import java.util.UUID;
import ma.markware.charybdis.model.annotation.Column;
import ma.markware.charybdis.model.annotation.Table;

/**
 * Specialization for USER.
 *
 * @author DataStax Developer Advocates team.
 */
@Table(name=
           CommentDseDao.TABLENAME_COMMENTS_BY_USER,
       keyspace=
           CommonConstants.KILLRVIDEO_KEYSPACE)
public class CommentByUser extends Comment {
    
    /** Serial. */
    private static final long serialVersionUID = 1453554109222565840L;
    
    /**
     * Default constructor.
     */
    public CommentByUser() {}
    
    /**
     * Copy constructor.
     *
     * @param c
     */
    public CommentByUser(Comment c) {
        this.commentid  = c.getCommentid();
        this.userid     = c.getUserid();
        this.videoid    = c.getVideoid();
        this.comment    = c.getComment();
    }

    /**
     * Getter for attribute 'userid'.
     *
     * @return
     *       current value of 'userid'
     */
    @Column
    @PartitionKey
    public UUID getUserid() {
        return userid;
    }

}
