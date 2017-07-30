package com.batti.service;

/**
 * Created by yonzhang on 7/15/17.
 */
public class VolunteerSignUpStatus {
    private String status;
    private String successfulVolunteerID;

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
}
