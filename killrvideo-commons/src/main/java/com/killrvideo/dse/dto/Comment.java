package com.killrvideo.dse.dto;

import java.util.Date;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import ma.markware.charybdis.model.annotation.ClusteringKey;
import ma.markware.charybdis.model.annotation.Column;
import ma.markware.charybdis.model.annotation.CreationDate;
import org.hibernate.validator.constraints.Length;

/**
 * Bean standing for comment on video.
 *
 * @author DataStax Developer Advocates team.
 */
public class Comment extends AbstractEntity {
    
    /** Serial. */
    private static final long serialVersionUID = 7675521710612951368L;
    
    /** Column names in the DB. */
    public static final String COLUMN_USERID    = "userid";
    public static final String COLUMN_VIDEOID   = "videoid";
    public static final String COLUMN_COMMENTID = "commentid";
    public static final String COLUMN_COMMENT   = "comment";

    @Column
    @NotNull
    protected UUID userid;
    
    @NotNull
    @Column
    protected UUID videoid;

    @Column
    @ClusteringKey
    @NotNull
    protected UUID commentid;

    @Length(min = 1, message = "The comment must not be empty")
    @Column
    protected String comment;

    @NotNull
    @CreationDate
    private Date dateOfComment;
    
    /**
     * Default constructor.
     */
    public Comment() {
    }
    
    /**
     * Default constructor.
     */
    public Comment(String comment) {
        this.comment = comment;
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
     * Setter for attribute 'videoid'.
     * @param videoid
     * 		new value for 'videoid '
     */
    public void setVideoid(UUID videoid) {
        this.videoid = videoid;
    }

    /**
     * Getter for attribute 'commentid'.
     *
     * @return
     *       current value of 'commentid'
     */
    public UUID getCommentid() {
        return commentid;
    }

    /**
     * Setter for attribute 'commentid'.
     * @param commentid
     * 		new value for 'commentid '
     */
    public void setCommentid(UUID commentid) {
        this.commentid = commentid;
    }

    /**
     * Getter for attribute 'comment'.
     *
     * @return
     *       current value of 'comment'
     */
    public String getComment() {
        return comment;
    }

    /**
     * Setter for attribute 'comment'.
     * @param comment
     * 		new value for 'comment '
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Getter for attribute 'dateOfComment'.
     *
     * @return
     *       current value of 'dateOfComment'
     */
    public Date getDateOfComment() {
        return dateOfComment;
    }

    /**
     * Setter for attribute 'dateOfComment'.
     * @param dateOfComment
     * 		new value for 'dateOfComment '
     */
    public void setDateOfComment(Date dateOfComment) {
        this.dateOfComment = dateOfComment;
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
     * Getter for attribute 'videoid'.
     *
     * @return
     *       current value of 'videoid'
     */
    public UUID getVideoid() {
        return videoid;
    }
    

}
