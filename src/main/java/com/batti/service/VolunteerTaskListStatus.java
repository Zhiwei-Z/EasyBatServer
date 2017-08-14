package com.batti.service;

import java.util.ArrayList;

/**
 * Created by yonzhang on 8/14/17.
 */
public class VolunteerTaskListStatus {
    private String status;
    private ArrayList<String> tasks;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<String> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<String> tasks) {
        this.tasks = tasks;
    }
}
