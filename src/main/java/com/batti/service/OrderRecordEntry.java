package com.batti.service;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by yonzhang on 8/21/17.
 */
public class OrderRecordEntry {
    private String orderID;
    private String customerID;
    private int pickStatus;
    private Date createdDate;
    private Time createdTime;
    private Date modifiedDate;
    private Time modifiedTime;

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public int getPickStatus() {
        return pickStatus;
    }

    public void setPickStatus(int pickStatus) {
        this.pickStatus = pickStatus;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Time getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Time createdTime) {
        this.createdTime = createdTime;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Time getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Time modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}
