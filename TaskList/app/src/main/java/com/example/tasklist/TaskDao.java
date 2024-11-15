package com.example.tasklist;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface TaskDao {

    @Insert
    void insertSingleTask( TaskEntity taskEntity);

    @Delete
    void deleteSingleTask(TaskEntity taskEntity);

    @Query("UPDATE tasks SET status = :newStatus where taskId = :taskId")
    void updateStatus (long taskId, Boolean newStatus);

    @Query("SELECT * FROM  Tasks ORDER BY type ASC")
    LiveData<List<TaskEntity>> getAlltasks();

    @Query("SELECT * FROM tasks WHERE taskId=:id")
    TaskEntity getSingleTask(long id);s

    @TypeConverters(RequirementsConverter.class)
    @Query("UPDATE tasks SET requirements= :newRequirements WHERE taskId =:taskId")
    void updateRequirements (long taskId, ArrayList<Requirements> newRequirements);

    @TypeConverters(RequirementsConverter.class)
    @Query("UPDATE tasks SET title= :newTitle , date= :newDate , requirements = :newRequirements WHERE taskId =:taskId ")
    void updateTask(long taskId, String newTitle, String newDate, ArrayList<Requirements> newRequirements);
}
