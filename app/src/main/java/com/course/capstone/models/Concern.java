package com.course.capstone.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Concern {
    @SerializedName("interestname")
    @Expose
    private String name;

    @SerializedName("parentid")
    @Expose
    private String parentid;

    public String getParentid() {
        return parentid;
    }

    public Concern(String name, String parentid) {
        this.name = name;
        this.parentid = parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    private boolean check;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
