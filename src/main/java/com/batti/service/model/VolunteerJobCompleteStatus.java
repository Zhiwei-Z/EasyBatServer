package com.batti.service.model;

/**
 * Created by yonzhang on 8/25/17.
 */
public class VolunteerJobCompleteStatus {
    private String status;
    private int remainingTasks;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRemainingTasks() {
        return remainingTasks;
    }

    public void setRemainingTasks(int remainingTasks) {
        this.remainingTasks = remainingTasks;
    }
}
