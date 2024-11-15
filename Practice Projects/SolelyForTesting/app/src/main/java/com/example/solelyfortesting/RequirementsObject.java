package com.example.solelyfortesting;

import java.util.ArrayList;

public class RequirementsObject {

    private ArrayList<String> array_reqs;
    private Boolean reqsChecked;

    public RequirementsObject(ArrayList<String> array_reqs, Boolean reqsChecked) {
        this.array_reqs = array_reqs;
        this.reqsChecked = reqsChecked;
    }
}
