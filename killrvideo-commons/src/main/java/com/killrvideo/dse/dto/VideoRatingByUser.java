package com.killrvideo.dse.dto;

import com.killrvideo.model.CommonConstants;
import java.io.Serializable;
import java.util.UUID;
import ma.markware.charybdis.model.annotation.ClusteringKey;
import ma.markware.charybdis.model.annotation.Column;
import ma.markware.charybdis.model.annotation.PartitionKey;
import ma.markware.charybdis.model.annotation.Table;

/**
 * Pojo representing DTO for table 'video_ratings_by_user'.
 *
 * @author DataStax Developer Advocates team.
 */
@Table(name= "video_ratings_by_user",
       keyspace=
         CommonConstants.KILLRVIDEO_KEYSPACE)
public class VideoRatingByUser implements Serializable {

    /** Serial. */
    private static final long serialVersionUID = 7124040203261999049L;

    @Column
    @PartitionKey
    private UUID videoid;

    @Column
    @ClusteringKey
    private UUID userid;

    @Column
    private int rating;

    /**
     * Default constructor (reflection)
     */
    public VideoRatingByUser() {}

    /**
     * Constructor with all parameters.
     */
    public VideoRatingByUser(UUID videoid, UUID userid, int rating) {
        this.videoid = videoid;
        this.userid = userid;
        this.rating = rating;
    }

    /**
     * Getter for attribute 'videoid'.
     *
     * @return
     *       current value of 'videoid'
     */
    public UUID getVideoid() {
        return videoid;
    }

    /**
     * Setter for attribute 'videoid'.
     * @param videoid
     * 		new value for 'videoid '
     */
    public void setVideoid(UUID videoid) {
        this.videoid = videoid;
    }

    /**
     * Getter for attribute 'userid'.
     *
     * @return
     *       current value of 'userid'
     */
    public UUID getUserid() {
        return userid;
    }

    /**
     * Setter for attribute 'userid'.
     * @param userid
     * 		new value for 'userid '
     */
    public void setUserid(UUID userid) {
        this.userid = userid;
    }

    /**
     * Getter for attribute 'rating'.
     *
     * @return
     *       current value of 'rating'
     */
    public int getRating() {
        return rating;
    }

    /**
     * Setter for attribute 'rating'.
     * @param rating
     * 		new value for 'rating '
     */
    public void setRating(int rating) {
        this.rating = rating;
    }
    
}
