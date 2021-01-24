package com.course.capstone.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Like {
    @SerializedName("likepeople")
    @Expose
    private String likepeople;
    @SerializedName("parentqnaid")
    @Expose
    private String parentqnaid;
    @SerializedName("likeid")
    @Expose
    private String id;

    public Like(String likepeople, String parentqnaid) {
        this.likepeople = likepeople;
        this.parentqnaid = parentqnaid;

    }

    public String getLikepeople() {
        return likepeople;
    }

    public void setLikepeople(String likepeople) {
        this.likepeople = likepeople;
    }

    public String getParentqnaid() {
        return parentqnaid;
    }

    public void setParentqnaid(String parentqnaid) {
        this.parentqnaid = parentqnaid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
