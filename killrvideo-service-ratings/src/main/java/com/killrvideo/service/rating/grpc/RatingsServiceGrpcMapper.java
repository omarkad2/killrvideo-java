package com.killrvideo.service.rating.grpc;

import static com.killrvideo.utils.GrpcMappingUtils.uuidToUuid;

import com.killrvideo.dse.dto.VideoRating;
import com.killrvideo.dse.dto.VideoRatingByUser;
import java.util.Optional;
import killrvideo.ratings.RatingsServiceOuterClass.GetRatingResponse;
import killrvideo.ratings.RatingsServiceOuterClass.GetUserRatingResponse;

/**
 * Helper and mappers for DAO <=> GRPC Communications
 *
 * @author DataStax Developer Advocates Team
 */
public class RatingsServiceGrpcMapper {
    
    /**
     * Hide constructor.
     */
    private RatingsServiceGrpcMapper() {
    }
     
   /**
     * Mapping to generated GPRC beans.
     */
    public static GetRatingResponse maptoRatingResponse(VideoRating vr) {
        return GetRatingResponse.newBuilder()
                .setVideoId(uuidToUuid(vr.getVideoid()))
                .setRatingsCount(Optional.ofNullable(vr.getRatingCounter()).orElse(0L))
                .setRatingsTotal(Optional.ofNullable(vr.getRatingTotal()).orElse(0L))
                .build();
    }
    
    /**
     * Mapping to generated GPRC beans.
     */
    public static GetUserRatingResponse maptoUserRatingResponse(VideoRatingByUser vr) {
        return GetUserRatingResponse.newBuilder()
                .setVideoId(uuidToUuid(vr.getVideoid()))
                .setUserId(uuidToUuid(vr.getUserid()))
                .setRating(vr.getRating())
                .build();
    }
    
}
