package com.course.capstone.models;

public class Card {
    private int imageCard;
    private String cardName;
    private int imageReward;
    private String desc;
    private String reward;

    public Card(){
    }
    public int getImageCard(){
        return imageCard;
    }
    public String getCardName(){
        return cardName;
    }
    public String getDesc(){
        return desc;
    }
    public String getReward(){
        return reward;
    }
    public int getImageReward(){
        return imageReward;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setImageCard(int imageCard) {
        this.imageCard = imageCard;
    }

    public void setImageReward(int imageReward) {
        this.imageReward = imageReward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }
}
