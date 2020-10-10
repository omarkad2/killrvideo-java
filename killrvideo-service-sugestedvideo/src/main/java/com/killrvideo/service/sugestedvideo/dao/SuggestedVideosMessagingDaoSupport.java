package com.killrvideo.service.sugestedvideo.dao;

import static com.killrvideo.service.sugestedvideo.grpc.SuggestedVideosServiceGrpcMapper.mapVideoAddedtoVideoDTO;

import com.killrvideo.utils.GrpcMappingUtils;
import java.time.Instant;
import java.util.UUID;
import killrvideo.ratings.events.RatingsEvents.UserRatedVideo;
import killrvideo.user_management.events.UserManagementEvents.UserCreated;
import killrvideo.video_catalog.events.VideoCatalogEvents.YouTubeVideoAdded;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Message processing for suggestion services.
 *
 * @author Cedrick LUNVEN (@clunven)
 */
public abstract class SuggestedVideosMessagingDaoSupport {
    
    /** Loger for that class. */
    private static Logger LOGGER = LoggerFactory.getLogger(SuggestedVideosMessagingDaoSupport.class);
    
    @Autowired
    protected SuggestedVideosDseDao sugestedVideoDseDao;
    
    /**
     * Message is consumed from specialized class but treatment is the same, updating graph.
     * 
     * @param userVideoRated
     *      a user has rated a video event
     */
    protected void onVideoRatingMessage(UserRatedVideo userVideoRated) {
        String videoId = userVideoRated.getVideoId().getValue();
        UUID   userId  = UUID.fromString(userVideoRated.getUserId().getValue());
        int rating     = userVideoRated.getRating();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("[NewUserEvent] Processing rating with user {} and video {}", userId, videoId);
        }
        sugestedVideoDseDao.updateGraphNewUserRating(videoId, userId, rating);
    }
    
    /**
     * Message is consumed from specialized class but treatment is the same, updating graph.
     * 
     * @param userCreationMessage
     *      a user has been created
     */
    protected void onUserCreatingMessage(UserCreated userCreationMessage) {
        final UUID userId       = UUID.fromString(userCreationMessage.getUserId().getValue());
        final Instant userCreation = GrpcMappingUtils.timestampToInstant(userCreationMessage.getTimestamp());
        final String email      = userCreationMessage.getEmail();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("[NewUserEvent] Processing for user {} ", userId);
        }
        sugestedVideoDseDao.updateGraphNewUser(userId, email, userCreation);
    }
    
    /**
     * Message is consumed from specialized class but treatment is the same, updating graph.
     * 
     * @param videoAdded
     *      a video has been created
     */
    protected void onYoutubeVideoAddingMessage(YouTubeVideoAdded videoAdded) {
       sugestedVideoDseDao.updateGraphNewVideo(mapVideoAddedtoVideoDTO(videoAdded));
    }
    

}
