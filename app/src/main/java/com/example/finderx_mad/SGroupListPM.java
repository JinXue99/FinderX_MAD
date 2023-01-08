package com.example.finderx_mad;

public class SGroupListPM {
    String TName,Name;

    public SGroupListPM(String TName, String name) {
        this.TName = TName;
        Name = name;
    }

    public SGroupListPM(){

    }

    public String getTName() {
        return TName;
    }

    public void setTName(String TName) {
        this.TName = TName;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
