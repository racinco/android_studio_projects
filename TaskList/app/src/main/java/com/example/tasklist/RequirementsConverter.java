package com.example.tasklist;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class RequirementsConverter {

    @TypeConverter
    public String toJson (ArrayList<Requirements> reqs){
        Gson gson = new Gson();
        return gson.toJson(reqs);
    }

    @TypeConverter
    public ArrayList<Requirements> toBook ( String json){
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Requirements>>() {}.getType();
        return  gson.fromJson(json,type);
    }

}
