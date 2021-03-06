package com.killrvideo.service.comment.grpc;

import static com.killrvideo.service.comment.grpc.CommentsServiceGrpcMapper.mapFromDseUserCommentToGrpcResponse;
import static com.killrvideo.service.comment.grpc.CommentsServiceGrpcMapper.mapFromDseVideoCommentToGrpcResponse;
import static com.killrvideo.service.comment.grpc.CommentsServiceGrpcMapper.mapFromGrpcUserCommentToDseQuery;
import static com.killrvideo.service.comment.grpc.CommentsServiceGrpcMapper.mapFromGrpcVideoCommentToDseQuery;
import static com.killrvideo.service.comment.grpc.CommentsServiceGrpcMapper.validateGrpcRequest_GetUserComments;
import static com.killrvideo.service.comment.grpc.CommentsServiceGrpcValidator.validateGrpcRequestCommentOnVideo;
import static com.killrvideo.service.comment.grpc.CommentsServiceGrpcValidator.validateGrpcRequestGetVideoComment;
import static java.util.UUID.fromString;

import com.killrvideo.dse.dto.Comment;
import com.killrvideo.dse.dto.QueryCommentByUser;
import com.killrvideo.dse.dto.QueryCommentByVideo;
import com.killrvideo.messaging.dao.MessagingDao;
import com.killrvideo.service.comment.dao.CommentDseDao;
import com.killrvideo.utils.GrpcMappingUtils;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import killrvideo.comments.CommentsServiceGrpc.CommentsServiceImplBase;
import killrvideo.comments.CommentsServiceOuterClass.CommentOnVideoRequest;
import killrvideo.comments.CommentsServiceOuterClass.CommentOnVideoResponse;
import killrvideo.comments.CommentsServiceOuterClass.GetUserCommentsRequest;
import killrvideo.comments.CommentsServiceOuterClass.GetUserCommentsResponse;
import killrvideo.comments.CommentsServiceOuterClass.GetVideoCommentsRequest;
import killrvideo.comments.CommentsServiceOuterClass.GetVideoCommentsResponse;
import killrvideo.comments.events.CommentsEvents.UserCommentedOnVideo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Exposition of comment services with GPRC Technology & Protobuf Interface
 * 
 * @author DataStax advocates team.
 */
@Service
public class CommentsServiceGrpc extends CommentsServiceImplBase {
     
    /** Loger for that class. */
    private static Logger LOGGER = LoggerFactory.getLogger(CommentsServiceGrpc.class);
    
    /** Communications and queries to DSE (Comment). */
    @Autowired
    private CommentDseDao dseCommentDao;
    
    @Autowired
    private MessagingDao messagingDao;
    
    @Value("${killrvideo.discovery.services.comment : CommentsService}")
    private String serviceKey;
  
    @Value("${killrvideo.messaging.destinations.commentCreated : topic-kv-commentCreation}")
    private String messageDestination;
    
    /** {@inheritDoc} */
    @Override
    public void commentOnVideo(final CommentOnVideoRequest grpcReq, StreamObserver<CommentOnVideoResponse> grpcResObserver) {
        
        // Boilerplate Code for validation delegated to {@link CommentsServiceGrpcValidator}
        validateGrpcRequestCommentOnVideo(LOGGER, grpcReq, grpcResObserver);
        
        // Stands as stopwatch for logging and messaging 
        final Instant starts = Instant.now();
        
        // Mapping GRPC => Domain (Dao)
        Comment q = new Comment();
        q.setVideoid(fromString(grpcReq.getVideoId().getValue()));
        q.setCommentid(fromString(grpcReq.getCommentId().getValue()));
        q.setUserid(fromString(grpcReq.getUserId().getValue()));
        q.setComment(grpcReq.getComment());
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Insert comment on video {} for user {} : {}",  q.getVideoid(), q.getUserid(), q);
        }
        
        CompletableFuture<Void> futureDse = dseCommentDao.insertCommentAsync(q);
        
        // If OK, then send Message to Kafka
        CompletableFuture<Object> futureDseThensKafka = futureDse.thenCompose(rs -> {
            return messagingDao.sendEvent(messageDestination, UserCommentedOnVideo.newBuilder()
                    .setCommentId(grpcReq.getCommentId())
                    .setVideoId(grpcReq.getVideoId())
                    .setUserId(grpcReq.getUserId())
                    .setCommentTimestamp(GrpcMappingUtils.instantToTimeStamp(Instant.now()))
                    .build());
        });
        
        futureDseThensKafka.whenComplete((result, error) -> {
            if (error != null ) {
                traceError("commentOnVideo", starts, error);
                grpcResObserver.onError(Status.INTERNAL.withCause(error).asRuntimeException());
            } else {
                traceSuccess("commentOnVideo", starts);
                grpcResObserver.onNext(CommentOnVideoResponse.newBuilder().build());
                grpcResObserver.onCompleted();
            }
         });
    }
    
    /** {@inheritDoc} */
    @Override
    public void getVideoComments(final GetVideoCommentsRequest grpcReq, StreamObserver<GetVideoCommentsResponse> responseObserver) {
        
        // Parameter validations
        validateGrpcRequestGetVideoComment(LOGGER, grpcReq, responseObserver);
        
        // Stands as stopwatch for logging and messaging 
        final Instant starts = Instant.now();
        
        // Mapping GRPC => Domain (Dao) : Dedicated bean creating for flexibility
        QueryCommentByVideo query = mapFromGrpcVideoCommentToDseQuery(grpcReq);
             
        // ASYNCHRONOUS works with ComputableFuture
        dseCommentDao.findCommentsByVideosIdAsync(query).whenComplete((result, error) -> {
            if (result != null) {
                traceSuccess( "getVideoComments", starts);
                responseObserver.onNext(mapFromDseVideoCommentToGrpcResponse(result));
                responseObserver.onCompleted();
            } else if (error != null){
                traceError("getVideoComments", starts, error);
                messagingDao.sendErrorEvent(getServiceKey(), error);
                responseObserver.onError(error);
            }
        });
    }
    
    /** {@inheritDoc} */
    @Override
    public void getUserComments(final GetUserCommentsRequest grpcReq, StreamObserver<GetUserCommentsResponse> responseObserver) {

        // GRPC Parameters Validation
        validateGrpcRequest_GetUserComments(LOGGER, grpcReq, responseObserver);
        
        // Stands as stopwatch for logging and messaging 
        final Instant starts = Instant.now();
        
        // Mapping GRPC => Domain (Dao) : Dedicated bean creating for flexibility
        QueryCommentByUser query = mapFromGrpcUserCommentToDseQuery(grpcReq);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Listing comment for user {}",  query.getUserId());
        }
       
        // ASYNCHRONOUS works with ComputableFuture
        dseCommentDao.findCommentsByUserIdAsync(query).whenComplete((result, error) -> {
            if (result != null) {
                traceSuccess("getUserComments", starts);
                responseObserver.onNext(mapFromDseUserCommentToGrpcResponse(result));
                responseObserver.onCompleted();
            } else if (error != null){
                traceError("getUserComments", starts, error);
                messagingDao.sendErrorEvent(getServiceKey(), error);
                responseObserver.onError(error);
            }
        });
    }
    
    /**
     * Utility to TRACE.
     *
     * @param method
     *      current operation
     * @param start
     *      timestamp for starting
     */
    private void traceSuccess(String method, Instant starts) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("End successfully '{}' in {} millis", method, Duration.between(starts, Instant.now()).getNano()/1000);
        }
    }
    
    /**
     * Utility to TRACE.
     *
     * @param method
     *      current operation
     * @param start
     *      timestamp for starting
     */
    private void traceError(String method, Instant starts, Throwable t) {
        LOGGER.error("An error occured in {} after {}", method, Duration.between(starts, Instant.now()), t);
    }

    /**
     * Getter accessor for attribute 'serviceKey'.
     *
     * @return
     *       current value of 'serviceKey'
     */
    public String getServiceKey() {
        return serviceKey;
    }

}
