package com.course.capstone.models;

public class Interest {
    private String degree;
    private String won;

    public Interest(String degree, String won) {
        this.degree = degree;
        this.won = won;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getWon() {
        return won;
    }

    public void setWon(String won) {
        this.won = won;
    }
}
