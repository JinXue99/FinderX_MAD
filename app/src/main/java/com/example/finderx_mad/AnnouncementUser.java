package com.example.finderx_mad;

import java.util.Date;

public class AnnouncementUser {

    String CourseCode, TaskTitle, TaskDeadline, TaskDetails;
    String Name;

    public AnnouncementUser(){}

    public AnnouncementUser(String CourseCode, String TaskTitle, String TaskDeadline, String TaskDetails, String Name){
        this.CourseCode = CourseCode;
        this.TaskTitle = TaskTitle;
        this.TaskDeadline = TaskDeadline;
        this.TaskDetails = TaskDetails;
        this.Name = Name;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCourseCode() {return CourseCode;}

    public void setCourseCode(String courseCode) {this.CourseCode = courseCode; }

    public String getTaskTitle() {
        return TaskTitle;
    }

    public void setTaskTitle(String taskTitle) {this.TaskTitle = taskTitle;}

    public String getTaskDeadline() {
        return TaskDeadline;
    }

    public void setTaskDeadline(String taskDeadline) {this.TaskDeadline = taskDeadline;}

    public String getTaskDetails() {
        return TaskDetails;
    }

    public void setTaskDetails(String taskDetails) {this.TaskDetails = taskDetails;}

}
