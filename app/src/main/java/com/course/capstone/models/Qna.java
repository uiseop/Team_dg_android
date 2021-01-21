package com.course.capstone.models;

import com.course.capstone.R;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Qna {

        String id;
        String Title;
        String Content;
        String Date;
        String commentCount;
        String LikeCount;


        public Qna(String id, String title, String content, String date, String commentCount, String likeCount) {
            this.id = id;
            this.Title = title;
            this.Content = content;
            this.Date = date;
            this.commentCount = commentCount;
            this.LikeCount = likeCount;

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

        public String getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(String commentCount) {
            this.commentCount = commentCount;
        }

        public String getLikeCount() {
            return LikeCount;
        }

        public void setLikeCount(String likeCount) {
            this.LikeCount = likeCount;
        }


    }


