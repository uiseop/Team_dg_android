package com.course.capstone.models;

public class DataItem {

    private String title;
    private int viewType;
    public DataItem( String title ,int viewType) {
        this.title= title;
        this.viewType = viewType;

    }
    public int getViewType() {
        return viewType;
    }
    public String getTitle() {
        return title;
    }
}
