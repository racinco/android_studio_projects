package com.example.tasklist;


import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.util.ArrayList;

// TODO 08/19 Requirements Changed
@Entity (tableName = "Tasks")
public class TaskEntity {
    @Ignore
    public final static String TASK_ID_KEY = "taskID";
    @PrimaryKey (autoGenerate = true)
    private long taskId;

    private String title;
    private String type;
   // private String  requirements;
    private String description;
    private String date;
    private Boolean status;

    @TypeConverters(RequirementsConverter.class)
    private ArrayList<Requirements> requirements;



    // Constructors
    public TaskEntity(String title, String type, ArrayList<Requirements> requirements, String description, String date, Boolean status) {
        this.title = title;
        this.type = type;
        this.requirements = requirements;
        this.description = description;
        this.date = date;
        this.status = status;
    }
    @Ignore
    public TaskEntity() {
    }

    @Ignore
    public TaskEntity(String title, ArrayList<Requirements> requirements, String date, Boolean status) {
        this.title = title;
        this.requirements = requirements;
        this.date = date;
        this.status = status;
    }
    @Ignore
    public TaskEntity(long taskId) {
        this.taskId = taskId;
    }

    // set id
    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    // setters

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRequirements(ArrayList<Requirements> requirements) {
        this.requirements = requirements;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }


    // getters

    public long getTaskId() {
        return taskId;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public ArrayList<Requirements> getRequirements() {
        return requirements;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public Boolean getStatus() {
        return status;
    }

    // TODO not sure if ToString...will be affected by the change in String to String array
    @Override
    public String toString() {
        return "taskId=" + taskId +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", requirements='" + requirements + '\'' +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                ", status='" + status + '\''
                ;
    }
}
