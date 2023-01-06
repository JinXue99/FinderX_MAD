package com.example.finderx_mad;


public class TaskModel {

    String taskTitle, taskDetails, taskDeadline;

    public TaskModel() {
    }

    public TaskModel(String taskTitle, String taskDetails, String taskDeadline) {
        this.taskTitle = taskTitle;
        this.taskDetails = taskDetails;
        this.taskDeadline = taskDeadline;
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

    public String getTaskDeadline() {
        return taskDeadline;
    }

    public void setTaskDeadline(String taskDeadline) {
        this.taskDeadline = taskDeadline;
    }


}