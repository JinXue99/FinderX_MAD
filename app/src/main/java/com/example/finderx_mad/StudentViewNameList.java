package com.example.finderx_mad;

//Model Class
public class StudentViewNameList {

    String name, gmail, majoring, description,image;

    public StudentViewNameList(String name, String gmail, String majoring, String description,String image) {
        this.name = name;
        this.gmail = gmail;
        this.majoring = majoring;
        this.description = description;
        this.image = image;
    }

    //empty constructor
    public StudentViewNameList() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getMajoring() {
        return majoring;
    }

    public void setMajoring(String majoring) {
        this.majoring = majoring;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
