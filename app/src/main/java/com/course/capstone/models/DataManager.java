package com.course.capstone.models;

public class DataManager {
    private User user;
    private Qna qna;

    private DataManager() {

    }

    private static class LazyHolder {
        public static final DataManager INSTANCE = new DataManager();
    }

    public static DataManager getInstance() {
        return LazyHolder.INSTANCE;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Qna getQna() {
        return qna;
    }

    public void setQna(Qna qna) {
        this.qna = qna;
    }

}
