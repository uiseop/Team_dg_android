package com.course.capstone.models;

public class NewsData{
    private String title;
    private String url;

    //Getter&Setter 추가
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    //toString 추가(생략 가능)
    @Override
    public String toString() { return title+": "+url; }
}