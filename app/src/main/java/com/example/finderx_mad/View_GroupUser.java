package com.example.finderx_mad;

public class View_GroupUser {

    String Name, CourseCode, Occ;

    public View_GroupUser(){}

    public View_GroupUser(String Name, String CourseCode, String Occ){
        this.CourseCode = CourseCode;
        this.Name = Name;
        this.Occ = Occ;
    }

    public void setName(String name) { Name = name;}

    public void setCourseCode(String courseCode) { CourseCode = courseCode;}

    public void setOcc(String occ) {Occ = occ;}

    public String getName() {return Name;}

    public String getCourseCode() {return CourseCode;}

    public String getOcc() {return Occ;}
}
