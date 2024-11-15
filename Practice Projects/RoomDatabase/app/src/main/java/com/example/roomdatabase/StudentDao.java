package com.example.roomdatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface StudentDao {

    // abstract method: converts student object to a record and add to table
    @Insert
    void insertSingleStudent (Student student);
    // you can pass a List of students/ delete/ insert / update?
    @Insert
    void insertListStudents (ArrayList<Student> students);



    // Updating and Deleting Works on the ID of an object
    // As long as we set the Id of the student, we can delete the student
    @Delete
    void deleteSingleStudent (Student student);

    @Update
    void updateSingleStudent (Student student);



    @Query("SELECT * FROM students") // provide an sql statement
    List<Student> getAllStudents();

    @Query("SELECT * FROM students WHERE name=:name")
   List<Student> getSingleStudent(String name);

    @Query("SELECT * FROM students") // provide an sql statement
    LiveData <List<Student> > getAllStudentsLiveData();

}
