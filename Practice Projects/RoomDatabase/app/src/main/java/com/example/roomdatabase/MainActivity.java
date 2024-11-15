package com.example.roomdatabase;


/* THIS TUTORIAL is To learn how to use Room Database

    ADVANTAGES:
    Room Database can be used to ORM ( object Relational mapping ) ==> Relate Tables
    Works greatly in Live Data

    REQUIREMENTS:
    Use Androidx and not support libraries

    DEPENDENCIES:
       link: https://developer.android.com/jetpack/androidx/releases/room

       def room_version = "2.3.0"
       implementation "androidx.room:room-runtime:$room_version"
       annotationProcessor "androidx.room:room-compiler:$room_version"

    USING ROOM DATABASE:
        We dont create tables ourselves
        we use class and create entities and interfaces

    HOW TO CREATE:
        1. Create Class entity
        2. Per class Entity Create Data Access Objects ( DAO ) : interfaces : an abstract method
        3. Create a Class for the Database itself
        4. Database is a heavy task. tf: implement singleton pattern to only have one instance of database

 */
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private TextView textview;
    private UniversityDatabase db;
    private Boolean delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // getData
         db = UniversityDatabase.getInstance(this);
        /*
        db.studentDao().insertSingleStudent(new Student("madeline","madeline@gmail.com", "1233450"));

        DELETE METHOD
        Student deleteMadeline = new Student();
        deleteMadeline.setId(4);
        db.studentDao().deleteSingleStudent(deleteMadeline);

        UPDATE METHOD
               Student update_brad = new Student();
        update_brad.setId(3);
        update_brad.setName("Brads");
        update_brad.setEmail("Brads@gmail.com");
        update_brad.setPhoneNumber("123456");
        db.studentDao().updateSingleStudent(update_brad);
         */


        // every time the Data is Changed it gets observed and immediately changes the data in the text view
        LiveData< List<Student> > livestudents = (LiveData<List<Student>>) db.studentDao().getAllStudentsLiveData();
        livestudents.observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(List<Student> livestudents) {

                String text = "";
                for ( Student s: livestudents){
                    text += "Id: " + s.getId() + "\nName: " + s.getName() + "\nEmail: " + s.getEmail() + "\nPhoneNumber: " + s.getPhoneNumber() + "\n ******** \n";
                }

                textview = findViewById(R.id.textView);
                textview.setText(text);

            }
        });


        // new TestAsyncTask ().execute();
    }



    private class TestAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

                ArrayList <Student> added_students = new ArrayList<>();
                added_students.add( new Student("Add1","Add1@gmail.com", "098765"));
                added_students.add( new Student("Add2","Add2@gmail.com", "098765"));
                added_students.add( new Student("Add3","Add3@gmail.com", "098765"));

                for ( Student s: added_students){
                    SystemClock.sleep(2000);
                    db.studentDao().insertSingleStudent(s);
                }

                return null;

        }
    }




}