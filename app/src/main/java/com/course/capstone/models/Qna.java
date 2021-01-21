package com.course.capstone.models;

import com.course.capstone.R;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Qna {


    @SerializedName("qnapeople")
    @Expose
    private String q_username;

    @SerializedName("parentid")
    @Expose
    private String id;

    @SerializedName("qnatitle")
    @Expose
    private String Title;

    @SerializedName("qnacontent")
    @Expose
    private  String Content;

    @SerializedName("qnadate")
    @Expose
    private String Date;

    @SerializedName("qnarate")
    @Expose
    private  int commentCount;

    @SerializedName("likerate")
    @Expose
    private int LikeCount;


    @SerializedName("id")
    @Expose
    private String qnaid;


        public Qna(String q_username, String id,String title, String content, String date, int commentCount, int likeCount) {

            this.q_username=q_username;
            this.id=id;
            this.Title = title;
            this.Content = content;
            this.Date = date;
            this.commentCount = commentCount;
            this.LikeCount = likeCount;

        }
    public String getQnaid() {
        return qnaid;
    }

    public void setQnaid(String qnaid) {
        this.qnaid = qnaid;
    }

        public String getQ_username() {
            System.out.println(q_username);
            return q_username;
    }

    public void setQ_username(String q_username) {
        this.q_username = q_username;
    }
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String title) {
            this.Title = title;
        }

        public String getContent() {
            return Content;
        }

        public void setContent(String content) {
            this.Content = content;
        }

        public String getDate() {
            return Date;
        }

        public void setDate(String date) {
            this.Date = date;
        }

        public int getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public int getLikeCount() {
            return LikeCount;
        }

        public void setLikeCount(int likeCount) {
            this.LikeCount = likeCount;
        }


    }


