package com.course.capstone.models;

import com.course.capstone.R;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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
    private String Content;

    @SerializedName("qnadate")
    @Expose
    private String Date;

    @SerializedName("qnarate")
    @Expose
    private int commentCount;

    @SerializedName("likerate")
    @Expose
    private int LikeCount;
    @SerializedName("id")
    @Expose
    private String qnaid;


    @SerializedName("likepeople")
    @Expose
    private ArrayList<String> likepeople = new ArrayList<>();

    @SerializedName("commentpeople")
    @Expose
    private ArrayList<String> commentpeople = new ArrayList<>();

    public Qna(String q_username, String id, String title, String content, String date, int commentCount, int likeCount, String qnaid, ArrayList<String> likepeople, ArrayList<String> commentpeople) {
        this.q_username = q_username;
        this.id = id;
        Title = title;
        Content = content;
        Date = date;
        this.commentCount = commentCount;
        LikeCount = likeCount;
        this.qnaid = qnaid;
        this.likepeople = likepeople;
        this.commentpeople = commentpeople;
    }


    public Qna(String q_username, String id, String title, String content, String date, int commentCount, int likeCount, String qnaid, ArrayList<String> likepeople) {
        this.q_username = q_username;
        this.id = id;
        Title = title;
        Content = content;
        Date = date;
        this.commentCount = commentCount;
        LikeCount = likeCount;
        this.qnaid = qnaid;
        this.likepeople = likepeople;
    }


    public Qna(String q_username, String id, String title, String content, String date, int commentCount, int likeCount, String qnaid) {
        this.q_username = q_username;
        this.id = id;
        Title = title;
        Content = content;
        Date = date;
        this.commentCount = commentCount;
        this.LikeCount = likeCount;
        this.qnaid = qnaid;
    }


    public Qna(String q_username, String id, String title, String content, String date, int commentCount, int likeCount) {

        this.q_username = q_username;
        this.id = id;
        this.Title = title;
        this.Content = content;
        this.Date = date;
        this.commentCount = commentCount;
        this.LikeCount = likeCount;

    }

    Comparator<Qna> comparator = new Comparator<Qna>() {
        @Override
        public int compare(Qna a, Qna b) {
            return b.getLikeCount() - a.getLikeCount();
        }
    };

    static class SortByDate implements Comparator<Qna> {
        @Override
        public int compare(Qna a, Qna b) {
            return a.Date.compareTo(b.Date);
        }
    }

    static class SortByLikecount implements Comparator<Qna> {
        @Override
        public int compare(Qna a, Qna b) {
            return a.LikeCount - b.LikeCount;
        }
    }

    public ArrayList<String> getLikepeople() {
        return likepeople;
    }

    public void setLikepeople(ArrayList<String> likepeople) {
        this.likepeople = likepeople;
    }

    public ArrayList<String> getCommentpeople() {
        return commentpeople;
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


