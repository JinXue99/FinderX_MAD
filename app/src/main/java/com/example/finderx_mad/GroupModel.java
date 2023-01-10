package com.example.finderx_mad;

public class GroupModel {
    String name;
    String description;
    String noMembers;

    public GroupModel(String name, String description, String noMembers) {
        this.name = name;
        this.description = description;
        this.noMembers = noMembers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNoMembers() {
        return noMembers;
    }

    public void setNoMembers(String noMembers) {
        this.noMembers = noMembers;
    }
}
