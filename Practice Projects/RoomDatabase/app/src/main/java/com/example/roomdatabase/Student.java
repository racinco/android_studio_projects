package com.example.roomdatabase;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

// create entity or basically Student table
@Entity (tableName = "Students")
public class Student {

    // define different fields by creating variables :: variables ==> fields ( id, name, email )
    // the name of the variables is the name of the column

    @PrimaryKey (autoGenerate = true) // set id as primary kid with auto increment
    private int id;

    private String name;
    private String email;

    @ColumnInfo(name = "phone_number") // change vcolumn name
    private String phoneNumber;

    public Student(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    @Ignore
    public Student() {
    }
    @Ignore // tells that this constructor is not part of entity // Maybe we can use in the java application
    public Student(int id, String name, String email, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }


    // set id since it is not instructed in constructor
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
