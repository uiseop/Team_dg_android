package com.course.capstone.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

public class Card {
    @Expose
    @SerializedName("img") private String imageCard;
    @Expose
    @SerializedName("name") private String cardName;
    @Expose
    @SerializedName("rewards_img")private List<String> imageReward;
    @Expose
    @SerializedName("desc") private String desc;
    @Expose
    @SerializedName("rewards")private List<String> reward;

    @Expose
    @SerializedName("annual_fee")private String anuual_fee;
    @Expose
    @SerializedName("companyname")private String companyname;

    public Card(){
    }
    public String getImageCard(){
        return imageCard;
    }
    public String getCardName(){
        return cardName;
    }
    public String getDesc(){
        return desc;
    }
    public String getReward(){ return reward.get(0); }
    public String getReward1(){if(reward == null){
        return null;
    }else{return reward.get(1);}
    }
    public String getImageReward(){
        return imageReward.get(0);
    }
    public String getImageReward1(){if(imageReward==null){
        return null;
    }else{return imageReward.get(1);}
    }
    public String getAnuual_fee(){return anuual_fee;}
    public String getCompanyname(){return companyname;}

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setImageCard(String imageCard) {
        this.imageCard = imageCard;
    }

    public void setImageReward(String imageReward) {
        this.imageReward = Collections.singletonList(imageReward);
    }

    public void setReward(String reward) {
        this.reward = Collections.singletonList(reward);
    }

    public void setCompanyname(String companyname){this.companyname = companyname;}
    public void setAnuual_fee(String anuual_fee) {this.anuual_fee = anuual_fee;}
}

