package com.example.finderx_mad;

public class CourseListTeacher {
    String Code,Name;

    public CourseListTeacher(String code, String name) {
        this.Code = code;
        this.Name = name;
    }

    public CourseListTeacher() {
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
