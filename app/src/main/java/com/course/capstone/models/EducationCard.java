package com.course.capstone.models;

public class EducationCard {
    private int image;
    private String title;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private String content;



    public EducationCard( int image,String title,String content) {
        this.title = title;
        this.image = image;
        this.content=content;
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
