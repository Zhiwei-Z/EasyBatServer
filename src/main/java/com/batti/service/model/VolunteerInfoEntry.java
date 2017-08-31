package com.batti.service.model;

/**
 * Created by yonzhang on 8/21/17.
 */
public class VolunteerInfoEntry {
    private String volunteerID;
    private String streetNumber;
    private String unitNumber;
    private String streetName;
    private String streetType;
    private String city;
    private String state;
    private String zipCode;
    private int status;
    private String combinedAddress;
    private String username;
    private String email;
    private String password;
    private int idealCoverRange;
    private int jobs;

    public int getJobs() {
        return jobs;
    }

    public void setJobs(int jobs) {
        this.jobs = jobs;
    }

    public String getVolunteerID() {
        return volunteerID;
    }

    public void setVolunteerID(String volunteerID) {
        this.volunteerID = volunteerID;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdealCoverRange() {
        return idealCoverRange;
    }

    public void setIdealCoverRange(int idealCoverRange) {
        this.idealCoverRange = idealCoverRange;
    }
}
