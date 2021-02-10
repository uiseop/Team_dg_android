package com.course.capstone.models;

public class Cross {
    private int score;
    private String category;

    public Cross(int score, String category) {
        this.score = score;
        this.category = category;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
