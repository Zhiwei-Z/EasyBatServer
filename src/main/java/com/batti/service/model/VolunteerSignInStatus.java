package com.batti.service.model;

/**
 * Created by yonzhang on 7/15/17.
 */
public class VolunteerSignInStatus {
    private String status;
    private String volunteerID;
    private String username;
    private String address;
    private int coverRange;

    public int getCoverRange() {
        return coverRange;
    }

    public void setCoverRange(int coverRange) {
        this.coverRange = coverRange;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String statue) {
        this.status = statue;
    }

    public String getVolunteerID() {
        return volunteerID;
    }

    public void setVolunteerID(String volunteerID) {
        this.volunteerID = volunteerID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
