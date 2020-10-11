package com.killrvideo.service.rating.dao;

import com.killrvideo.dse.dao.DaoSupport;
import com.killrvideo.service.rating.dto.VideoRating;
import com.killrvideo.service.rating.dto.VideoRatingByUser;
import com.killrvideo.service.rating.dto.VideoRatingByUser_Table;
import com.killrvideo.service.rating.dto.VideoRating_Table;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * Implementations of operation for Videos.
 *
 * @author DataStax Developer Advocates team.
 */
@Repository("killrvideo.rating.dao.dse")
public class RatingDao extends DaoSupport {

	/** Logger for that class. */
    private static Logger LOGGER = LoggerFactory.getLogger(RatingDao.class);
    
    /** Dse Data Model concerns. */
    public static final String TABLENAME_VIDEOS_RATINGS         = "video_ratings";
    public static final String TABLENAME_VIDEOS_RATINGS_BYUSER  = "video_ratings_by_user";
    
    /**
     * Create a rating.
     *
     * @param videoId
     *      current videoId
     * @param userId
     *      current userid
     * @param rating
     *      current rating
     */
    public CompletableFuture<Void> rateVideo( UUID videoId, UUID userId, Integer rating) {
        
        // Param validations
        assertNotNull("rateVideo", "videoId", videoId);
        assertNotNull("rateVideo", "userId", userId);
        assertNotNull("rateVideo", "rating", rating);

        CompletableFuture<Boolean> updateAsync = cqlTemplate.dsl().withConsistency(ma.markware.charybdis.model.option.ConsistencyLevel.LOCAL_QUORUM)
                                                            .update(VideoRating_Table.video_ratings)
                                                            //TODO add incr op in charybdis
                                                            .set(VideoRating_Table.ratingCounter, new Random().nextLong())
                                                            .set(VideoRating_Table.ratingTotal, Long.valueOf(rating))
                                                            .where(VideoRating_Table.videoid.eq(videoId))
                                                            .executeAsync();

//        VideoRatingByUser entity = new VideoRatingByUser(videoId, userId, rating);

        CompletableFuture<Boolean> insertAsync = cqlTemplate.dsl()
                                                            .insertInto(VideoRatingByUser_Table.video_ratings_by_user,
                                                                        VideoRatingByUser_Table.videoid, VideoRatingByUser_Table.userid,
                                                                        VideoRatingByUser_Table.rating)
                                                            .values(videoId, userId, rating)
                                                            .executeAsync();

        // Logging at DEBUG
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Rating {} on video {} for user {}", rating, videoId, userId);
        }
        
        /**
         * Here, instead of using logged batch, we can insert both mutations asynchronously
         * In case of error, we log the request into the mutation error log for replay later
         * by another micro-service
         *
         * Something else to notice is I am using both a prepared statement with executeAsync()
         * and a call to the mapper's saveAsync() methods.  I could have kept things uniform
         * and stuck with both prepared/bind statements, but I wanted to illustrate the combination
         * and use the mapper for the second statement because it is a simple save operation with no
         * options, increments, etc...  A key point is in the case you see below both statements are actually
         * prepared, the first one I did manually in a more traditional sense and in the second one the
         * mapper will prepare the statement for you automagically.
         */
        return CompletableFuture.allOf(updateAsync, insertAsync);
    }
    
    /**
     * VideoId matches the partition key set in the VideoRating class.
     * 
     * @param videoId
     *      unique identifier for video.
     * @return
     *      find rating
     */
    public CompletableFuture< Optional < VideoRating > > findRating(UUID videoId) {
        assertNotNull("findRating", "videoId", videoId);
        return cqlTemplate.dsl().selectFrom(VideoRating_Table.video_ratings).where(VideoRating_Table.videoid.eq(videoId))
                          .fetchOptionalAsync().thenApply(optRecord ->
                                                              optRecord.map(record -> {
                                                                  VideoRating videoRating = new VideoRating();
                                                                  videoRating.setVideoid(record.get(VideoRating_Table.videoid));
                                                                  videoRating.setRatingCounter(record.get(VideoRating_Table.ratingCounter));
                                                                  videoRating.setRatingTotal(record.get(VideoRating_Table.ratingTotal));
                                                                  return videoRating;
                                                              })
            );
    }
    
    /**
     * Find rating from videoid and userId.
     *
     * @param videoId
     *      current videoId
     * @param userId
     *      current user unique identifier.
     * @return
     *      video rating is exist.
     */
    public CompletableFuture< Optional < VideoRatingByUser > > findUserRating(UUID videoId, UUID userId) {
        assertNotNull("findUserRating", "videoId", videoId);
        assertNotNull("findUserRating", "userId", userId);
        return cqlTemplate.dsl()
                          .selectFrom(VideoRatingByUser_Table.video_ratings_by_user)
                          .where(VideoRatingByUser_Table.videoid.eq(videoId))
                          .and(VideoRatingByUser_Table.userid.eq(userId))
                          .fetchOptionalAsync().thenApply(optRecord -> optRecord.map(record -> new VideoRatingByUser(
                              record.get(VideoRatingByUser_Table.videoid), record.get(VideoRatingByUser_Table.userid),
                              record.get(VideoRatingByUser_Table.rating))));
    }
  
}
