package com.course.capstone.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Edu {
    @SerializedName("eduname")
    @Expose
    private String eduname;

    @SerializedName("eduurl")
    @Expose
    private String eduurl;

    @SerializedName("eduimgurl")
    @Expose
    private String eduimgurl;

    @SerializedName("eduage")
    @Expose
    private ArrayList<String> eduage;

    @SerializedName("educategory")
    @Expose
    private ArrayList<String> educategory;

    @SerializedName("id")
    @Expose
    private String id;

    public Edu(String eduname, String eduurl, String eduimgurl, ArrayList<String> eduage, ArrayList<String> educategory) {
        this.eduname = eduname;
        this.eduurl = eduurl;
        this.eduimgurl = eduimgurl;
        this.eduage = eduage;
        this.educategory = educategory;
    }

    public String getEduname() {
        return eduname;
    }

    public void setEduname(String eduname) {
        this.eduname = eduname;
    }

    public String getEduurl() {
        return eduurl;
    }

    public void setEduurl(String eduurl) {
        this.eduurl = eduurl;
    }

    public String getEduimgurl() {
        return eduimgurl;
    }

    public void setEduimgurl(String eduimgurl) {
        this.eduimgurl = eduimgurl;
    }

    public ArrayList<String> getEduage() {
        return eduage;
    }

    public void setEduage(ArrayList<String> eduage) {
        this.eduage = eduage;
    }

    public ArrayList<String> getEducategory() {
        return educategory;
    }

    public void setEducategory(ArrayList<String> educategory) {
        this.educategory = educategory;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
