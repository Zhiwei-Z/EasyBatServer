package com.batti.service.model;

import java.util.SortedMap;

/**
 * Created by yonzhang on 8/25/17.
 */
public class UnoccupiedAddressListStatus {
    private String status;
    private SortedMap<String, Double> addresses;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public SortedMap<String, Double> getAddresses() {
        return addresses;
    }

    public void setAddresses(SortedMap<String, Double> addresses) {
        this.addresses = addresses;
    }
}
