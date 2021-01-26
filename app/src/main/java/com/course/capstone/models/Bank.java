package com.course.capstone.models;

import android.graphics.drawable.Drawable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bank {

    private Drawable icon;
    @SerializedName("bankname")
    @Expose
    private String text;
    @SerializedName("account")
    @Expose
    private int account;
    @SerializedName("parentid")
    @Expose
    private String parentid;

    DataManager dataManager = DataManager.getInstance();
    public void setParentid(){
        this.parentid = dataManager.getUser().getId();
    }

    public Bank(String text, int account, String parentid) {
        this.text = text;
        this.account = account;
        this.parentid = parentid;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    private boolean check;

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
