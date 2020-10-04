package com.killrvideo.dse.dto;

import com.killrvideo.model.CommonConstants;
import java.io.Serializable;
import java.util.UUID;
import ma.markware.charybdis.model.annotation.Column;
import ma.markware.charybdis.model.annotation.PartitionKey;
import ma.markware.charybdis.model.annotation.Table;

/**
 * Pojo representing DTO for table 'video_playback_stats'.
 *
 * @author DataStax Developer Advocates team.
 */
@Table(keyspace =
        CommonConstants.KILLRVIDEO_KEYSPACE, 
       name = "video_playback_stats")
public class VideoPlaybackStats implements Serializable {

    /** Serial. */
    private static final long serialVersionUID = -8636413035520458200L;
    
    /** COLUNMNS NAMES. */
    public static final String COLUMN_VIDEOID   = "videoid";
    public static final String COLUMN_VIEWS     = "views";

    @Column
    @PartitionKey
    private UUID videoid;

    /**
     * "views" column is a counter type in the underlying DSE database.  As of driver version 3.2 there
     * is no "@Counter" annotation that I know of.  No worries though, just use the incr() function
     * while using the QueryBuilder.  Something similar to with(QueryBuilder.incr("views")).
     */
    @Column
    private Long views;

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
     * Getter for attribute 'views'.
     *
     * @return
     *       current value of 'views'
     */
    public Long getViews() {
        return views;
    }

    /**
     * Setter for attribute 'views'.
     * @param views
     * 		new value for 'views '
     */
    public void setViews(Long views) {
        this.views = views;
    }
    
    
}
