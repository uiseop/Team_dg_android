package com.course.capstone.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Payment {
    @SerializedName("category")
    @Expose
    String category;

    @SerializedName("shopname")
    @Expose
    String shopname;

    @SerializedName("amount")
    @Expose
    int amount;

    @SerializedName("parentaccount")
    @Expose
    int parentaccount;

    public int getParentaccount() {
        return parentaccount;
    }

    public void setParentaccount(int parentaccount) {
        this.parentaccount = parentaccount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
