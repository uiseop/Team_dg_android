package com.course.capstone.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Comment {
    @SerializedName("commentpeople")
    @Expose
    private String Commentpeople;
    @SerializedName(" commentcontent")
    @Expose
    private String Commentcontent;


    @SerializedName("commentdate")
    @Expose
    private String Commentdate;

    @SerializedName("parentid")
    @Expose
    private String parentid;

    @SerializedName("id")
    @Expose
    private String Commentid;


    public Comment(String commentpeople, String commentcontent, String commentdate, String parentid) {
       this. Commentpeople = commentpeople;
        this.Commentcontent = commentcontent;
        this.Commentdate = commentdate;
        this.parentid = parentid;

    }


    public String getCommentpeople() {
        return Commentpeople;
    }

    public void setCommentpeople(String commentpeople) {
        Commentpeople = commentpeople;
    }

    public String getCommentcontent() {
        return Commentcontent;
    }

    public void setCommentcontent(String commentcontent) {
        Commentcontent = commentcontent;
    }

    public String getCommentdate() {
        return Commentdate;
    }

    public void setCommentdate(String commentdate) {
        Commentdate = commentdate;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getCommentid() {
        return Commentid;
    }

    public void setCommentid(String commentid) {
        Commentid = commentid;
    }




}
