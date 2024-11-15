package com.example.tasklist;

public class Requirements {

    private String name;
    private Boolean isReqChecked;

    public Requirements(String name, Boolean isReqChecked) {
        this.name = name;
        this.isReqChecked = isReqChecked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getReqChecked() {
        return isReqChecked;
    }

    public void setReqChecked(Boolean reqChecked) {
        isReqChecked = reqChecked;
    }
}
