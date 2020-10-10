package com.killrvideo.service.video.dto;

import com.killrvideo.model.CommonConstants;
import com.killrvideo.service.video.dao.VideoCatalogDseDao;
import java.time.Instant;
import java.util.UUID;
import ma.markware.charybdis.model.annotation.Column;
import ma.markware.charybdis.model.annotation.PartitionKey;
import ma.markware.charybdis.model.annotation.Table;

/**
 * Pojo representing DTO for table 'latest_videos'
 *
 * @author DataStax Developer Advocates team.
 */
@Table(keyspace = CommonConstants.KILLRVIDEO_KEYSPACE,
    name = VideoCatalogDseDao.TABLENAME_LATEST_VIDEOS)
public class LatestVideo extends VideoPreview {

    /** Serial. */
   private static final long serialVersionUID = -8527565276521920973L;

    /** Column names in the DB. */
    public static final String COLUMN_USERID   = "userid";
    public static final String COLUMN_YYYYMMDD = "yyyymmdd";

    @Column
    @PartitionKey
    private String yyyymmdd;

    @Column
    private UUID userid;

    /**
     * Default constructor.
     */
    public LatestVideo() {}

    /**
     * Constructor with all parameters.
     */
    public LatestVideo(String yyyymmdd, UUID userid, UUID videoid, String name, String previewImageLocation, Instant addedDate) {
        super(name, previewImageLocation, addedDate, videoid);
        this.yyyymmdd = yyyymmdd;
        this.userid = userid;
    }
    
    /**
     * Getter for attribute 'yyyymmdd'.
     *
     * @return
     *       current value of 'yyyymmdd'
     */
    public String getYyyymmdd() {
        return yyyymmdd;
    }

    /**
     * Setter for attribute 'yyyymmdd'.
     * @param yyyymmdd
     * 		new value for 'yyyymmdd '
     */
    public void setYyyymmdd(String yyyymmdd) {
        this.yyyymmdd = yyyymmdd;
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
    
    
}
