package com.example.finderx_mad;

public class CourseList {
    String Code,Name;
    private int imageView;

    public CourseList(String code, String name,int imageView) {
        this.Code = code;
        this.Name = name;
        this.imageView = imageView;
    }

    public CourseList(){
    }

    public int getImageView() {
        return imageView;
    }

    public void setImageView(int imageView) {
        this.imageView = imageView;
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
