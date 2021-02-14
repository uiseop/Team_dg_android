package com.course.capstone.models;

public class Youtube {
    private String title;
    private String address;
    private String imgurl;

    public Youtube(String title, String address, String imgurl) {
        this.title = title;
        this.address = address;
        this.imgurl = imgurl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }
}
