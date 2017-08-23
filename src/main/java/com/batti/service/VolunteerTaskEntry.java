package com.batti.service;

/**
 * Created by yonzhang on 8/21/17.
 */
public class VolunteerTaskEntry {
    private String choiceID;
    private String orderID;
    private String volunteerID;
    private int pickUpStatus;

    public String getChoiceID() {
        return choiceID;
    }

    public void setChoiceID(String choiceID) {
        this.choiceID = choiceID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getVolunteerID() {
        return volunteerID;
    }

    public void setVolunteerID(String volunteerID) {
        this.volunteerID = volunteerID;
    }

    public int getPickUpStatus() {
        return pickUpStatus;
    }

    public void setPickUpStatus(int pickUpStatus) {
        this.pickUpStatus = pickUpStatus;
    }
}
