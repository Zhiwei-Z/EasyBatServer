package com.batti.service;

/**
 * Created by yonzhang on 7/9/17.
 */
public class CustomerSignInStatus {
    private String status;
    private String customerID;
    private int customerStatus;
    private int customerPickStatus;

    public String getStatus() {
        return status;
    }

    public void setStatus(String statue) {
        this.status = statue;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public int getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(int customerStatus) {
        this.customerStatus = customerStatus;
    }

    public int getCustomerPickStatus() {
        return customerPickStatus;
    }

    public void setCustomerPickStatus(int customerPickStatus) {
        this.customerPickStatus = customerPickStatus;
    }
}
