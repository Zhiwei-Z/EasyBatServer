package com.batti.service.model;


/**
 * Created by yonzhang on 6/17/17.
 */
public class Order {

    private String order_id;
    private String customer_id;
    private int pick_status;
    private String address;

    public Order(String order_id, String customer_id, int pick_status, String address) {
        this.order_id = order_id;
        this.customer_id = customer_id;
        this.pick_status = pick_status;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public int getPick_status() {
        return pick_status;
    }

    public void setPick_status(int pick_status) {
        this.pick_status = pick_status;
    }
}
