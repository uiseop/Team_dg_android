package com.course.capstone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Post {
    @SerializedName("Classification")
    @Expose
    private String Classification;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("Title")
    @Expose
    private String Title;
    @SerializedName("Content")
    @Expose
    private String Content;
    @SerializedName("Postnumber")
    @Expose
    private String Postnumber;
    @SerializedName("commentCount")
    @Expose
    private String commentCount;
    @SerializedName("recommendCount")
    @Expose
    private String recommendCount;
    @SerializedName("recommendContent")
    @Expose
    private String recommendContent;
}

