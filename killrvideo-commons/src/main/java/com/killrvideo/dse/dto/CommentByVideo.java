package com.killrvideo.dse.dto;

import com.killrvideo.model.CommonConstants;
import java.util.UUID;
import ma.markware.charybdis.model.annotation.Column;
import ma.markware.charybdis.model.annotation.PartitionKey;
import ma.markware.charybdis.model.annotation.Table;

/**
 * Specialization for VIDEO.
 *
 * @author DataStax Developer Advocates team.
 */
@Table(name="comments_by_video",
       keyspace=CommonConstants.KILLRVIDEO_KEYSPACE)
public class CommentByVideo extends Comment {
    
    /** Serial. */
    private static final long serialVersionUID = -6738790629520080307L;

    public CommentByVideo() {
    }
    
    public CommentByVideo(Comment c) {
        this.commentid  = c.getCommentid();
        this.userid     = c.getUserid();
        this.videoid    = c.getVideoid();
        this.comment    = c.getComment();
    }

    /**
     * Getter for attribute 'videoid'.
     *
     * @return
     *       current value of 'videoid'
     */
    @Column
    @PartitionKey
    public UUID getVideoid() {
        return videoid;
    }
    
    
}
