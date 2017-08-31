package com.batti.service.model;

/**
 * Created by yonzhang on 7/15/17.
 */
public class VolunteerSignUpStatus {
    private String status;
    private String successfulVolunteerID;
    private String address;
    private String username;
    private int coverRange;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public void setSuccessfulVolunteerID(String successfulVolunteerID) {
        this.successfulVolunteerID = successfulVolunteerID;
    }

    public String getSuccessfulVolunteerID() {
        return successfulVolunteerID;
    }

    public int getCoverRange() {
        return coverRange;
    }

    public void setCoverRange(int coverRange) {
        this.coverRange = coverRange;
    }
}
