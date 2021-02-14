package com.course.capstone.models;

public class EducationCard {
    private int image;
    private String title;



    public EducationCard( int image,String title) {
        this.title = title;
        this.image = image;

    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
