package com.batti.service.model;

/**
 * Created by yonzhang on 8/21/17.
 */
public class CustomerInfoEntry {
    private String customerID;
    private String streetNumber;
    private String unitNumber;
    private String streetName;
    private String streetType;
    private String city;
    private String state;
    private String zipCode;
    private int status;
    private String combinedAddress;
    private String nickname;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetType() {
        return streetType;
    }

    public void setStreetType(String streetType) {
        this.streetType = streetType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCombinedAddress() {
        return combinedAddress;
    }

    public void setCombinedAddress(String combinedAddress) {
        this.combinedAddress = combinedAddress;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCustomerID() {

        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    @Override
    public String toString() {
        return "CustomerInfoEntry{" +
                "customerID='" + customerID + '\'' +
                ", streetNumber='" + streetNumber + '\'' +
                ", unitNumber='" + unitNumber + '\'' +
                ", streetName='" + streetName + '\'' +
                ", streetType='" + streetType + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", status=" + status +
                ", combinedAddress='" + combinedAddress + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
