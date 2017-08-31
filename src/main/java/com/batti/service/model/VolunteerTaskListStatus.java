package com.batti.service.model;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by yonzhang on 8/14/17.
 */
public class VolunteerTaskListStatus {
    private String status;
    private SortedMap<String, Double> tasks;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public SortedMap<String, Double> getTasks() {
        return tasks;
    }

    public void setTasks(SortedMap<String, Double> tasks) {
        this.tasks = tasks;
    }
}
