package com.course.capstone.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("userid")
    @Expose
    private String userid;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("birthdate")
    @Expose
    private String birthdate;
    @SerializedName("emailaddress")
    @Expose
    private String emailaddress;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("id")
    @Expose
    private String id;

    public User() {
    }

    public User(String name, String userid, String password, String birthdate, String emailaddress) {
        this.name = name;
        this.userid = userid;
        this.password = password;
        this.birthdate = birthdate;
        this.emailaddress = emailaddress;
    }

    public User(String name, String userid, String password, String birthdate, String emailaddress, String token) {
        this.name = name;
        this.userid = userid;
        this.password = password;
        this.birthdate = birthdate;
        this.emailaddress = emailaddress;
        this.token = token;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
