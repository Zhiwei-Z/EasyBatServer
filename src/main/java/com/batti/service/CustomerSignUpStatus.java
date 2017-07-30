package com.batti.service;

/**
 * Created by yonzhang on 6/27/17.
 */
public class CustomerSignUpStatus {
    private String status;
    private String successfulCustomerID;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public void setSuccessfulCustomerID(String successfulCustomerID) {
        this.successfulCustomerID = successfulCustomerID;
    }

    public String getSuccessfulCustomerID() {
        return successfulCustomerID;
    }
}
