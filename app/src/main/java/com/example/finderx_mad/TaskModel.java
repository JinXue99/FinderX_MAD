package com.example.finderx_mad;

public class TaskModel {

    int id;
    String taskTitle, taskDetails, taskDate, taskTime;

    TaskModel(){

    }

    public TaskModel(String taskTitle, String taskDetails, String taskDate, String taskTime) {
        this.taskTitle = taskTitle;
        this.taskDetails = taskDetails;
        this.taskDate = taskDate;
        this.taskTime = taskTime;
    }

    public TaskModel(int id, String taskTitle, String taskDetails, String taskDate, String taskTime) {
        this.id = id;
        this.taskTitle = taskTitle;
        this.taskDetails = taskDetails;
        this.taskDate = taskDate;
        this.taskTime = taskTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskDetails() {
        return taskDetails;
    }

    public void setTaskDetails(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    public String getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(String taskDate) {
        this.taskDate = taskDate;
    }

    public String getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(String taskTime) {
        this.taskTime = taskTime;
    }
}
