package com.example.solelyfortesting;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class RequirementsConverter {

    @TypeConverter
    public String reqsToJson (ArrayList<RequirementsObject> reqsObject){
        Gson gson = new Gson();
        return gson.toJson(reqsObject);
    }
    @TypeConverter
    public  ArrayList<RequirementsObject> jsonToReqs (String json){
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<RequirementsObject>>() {}.getType();
        return  gson.fromJson(json,type);

    }




}
