package com.course.capstone.models;

public class ChartDTO {

    private String title;
    private String current;
    private String gapPrice;
    private String gapRate;

    public ChartDTO() {
        this.title = title;
        this.current = current;
        this.gapPrice = gapPrice;
        this.gapRate = gapRate;
    }

    public void setTitle(String title){this.title = title;}
    public void setCurrent(String current){this.current=current;}
    public void setGapPrice(String gapPrice){this.gapPrice=gapPrice;}
    public void setGapRate(String gapRate){this.gapRate=gapRate;}

    public String getTitle() {
        return title;
    }

    public String getCurrent() {
        return current;
    }

    public String getGapPrice(){
        return gapPrice;
    }

    public String getGapRate() {
        return gapRate;
    }
}