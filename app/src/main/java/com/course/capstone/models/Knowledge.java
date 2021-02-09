package com.course.capstone.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Knowledge{
    @Expose
    @SerializedName("knowledgetitle")
    private String knowledgetitle;

    @Expose
    @SerializedName("knowledgepeople")
    private String knowledgepeople;

    @Expose
    @SerializedName("knowledgecontent")
    private String knowledgecontent;

    @Expose
    @SerializedName("parentid")
    private String parentid;

    @Expose
    @SerializedName("knowledgedate")
    private String knowledgedate;

    @SerializedName("id")
    @Expose
    private String id;

    public Knowledge(String knowledgetitle, String knowledgepeople, String knowledgecontent, String parentid, String knowledgedate) {
        this.knowledgetitle = knowledgetitle;
        this.knowledgepeople = knowledgepeople;
        this.knowledgecontent = knowledgecontent;
        this.parentid = parentid;
        this.knowledgedate = knowledgedate;
    }

    public Knowledge(String knowledgetitle, String knowledgepeople, String knowledgecontent, String parentid, String knowledgedate, String id) {
        this.knowledgetitle = knowledgetitle;
        this.knowledgepeople = knowledgepeople;
        this.knowledgecontent = knowledgecontent;
        this.parentid = parentid;
        this.knowledgedate = knowledgedate;
        this.id = id;
    }

    public String getKnowledgetitle() {
        return knowledgetitle;
    }

    public String getKnowledgepeople() {
        return knowledgepeople;
    }

    public String getKnowledgecontent() {
        return knowledgecontent;
    }

    public String getParentid() {
        return parentid;
    }

    public String getKnowledgedate() {
        return knowledgedate;
    }

    public String getId() {
        return id;
    }
}
