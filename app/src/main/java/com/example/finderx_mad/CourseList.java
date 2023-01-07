package com.example.finderx_mad;

public class CourseList {
    String Code,Name;

    public CourseList(String code, String name) {
        this.Code = code;
        this.Name = name;
    }

    public CourseList(){
    }

    public String getCode() {
        return Code;
    }

    public String getName() {
        return Name;
    }

    public void setCode(String code) {
        Code = code;
    }

    public void setName(String name) {
        Name = name;
    }
}
