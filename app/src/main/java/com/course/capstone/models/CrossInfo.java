package com.course.capstone.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CrossInfo {
    @Expose
    @SerializedName("crossurl")
    private String crossUrl;

    @Expose
    @SerializedName("imageurl")
    private String img;

    public CrossInfo(){
    }


    public String getCrossUrl() {
        return crossUrl;
    }

    public void setCrossUrl(String crossUrl) {
        this.crossUrl = crossUrl;
    }

}
