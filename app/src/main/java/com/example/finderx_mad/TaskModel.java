package com.example.finderx_mad;


public class TaskModel{

    String taskTitle, taskDetails, taskDeadline, courseCode;
    String taskDateandTime;

    public TaskModel() {
    }

    public TaskModel(String taskTitle, String taskDetails, String taskDeadline, String taskDateandTime, String courseCode) {
        this.taskTitle = taskTitle;
        this.taskDetails = taskDetails;
        this.taskDeadline = taskDeadline;
        this.taskDateandTime = taskDateandTime;
        this.courseCode = courseCode;
    }

    public TaskModel(String taskTitle, String taskDateandTime) {
        this.taskTitle = taskTitle;
        this.taskDateandTime = taskDateandTime;
    }

    public TaskModel(String taskModel) {
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

    public String getTaskDateandTime() {
        return taskDateandTime;
    }

    public void setTaskDateandTime(String taskDate) {
        this.taskDateandTime = taskDate;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
}