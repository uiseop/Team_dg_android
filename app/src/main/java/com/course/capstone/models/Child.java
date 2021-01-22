package com.course.capstone.models;

public class Child {
    String shop;
    int cost;

    public Child(String shop, int cost) {
        this.shop = shop;
        this.cost = cost;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
