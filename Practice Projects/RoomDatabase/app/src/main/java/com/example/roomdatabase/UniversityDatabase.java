package com.example.roomdatabase;
/*
    IMPORTANT:
        Needs to be abstract
        extend RoomDatabase
        annotate @Database and add entities such as Student Entity
        create a Singleton Pattern of University Database
            .addCallback
             ==> create RoomDatabase.Callback
             ==> Create class that Populates the data
 */
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = { Student.class} , version = 1) // add every entity


public abstract class UniversityDatabase  extends RoomDatabase {

    public abstract StudentDao studentDao();

    /* Singleton Pattern university Database
     synchronized means thread safe. Multiple threads cannot open the databse at once
     */

    private static UniversityDatabase instance;
    public static synchronized  UniversityDatabase getInstance(Context context) {
        if (instance == null){
            instance = Room.databaseBuilder(context, UniversityDatabase.class, "university_db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries() // though not needed since remember that it can get added
                    .addCallback(initialCallBack)
                    .build();
                    // .fallbackToDestructiveMigration() useful when we change the version of database, or we dont have any migration,
                    // deletes the whole database and create the database from scratch
                    // .addMigrations() - To add different versions.

            }
        return instance;
        }


    private static RoomDatabase.Callback initialCallBack = new RoomDatabase.Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            new PopulateInitialData(instance).execute();
            }


        };

    private static class PopulateInitialData extends AsyncTask <Void,Void,Void> {

        private StudentDao studentDao;
        public PopulateInitialData( UniversityDatabase db) {
            this.studentDao = db.studentDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            studentDao.insertSingleStudent(new Student( "Meisam", "meisam@gmail.com", "456789"));
            studentDao.insertSingleStudent(new Student( "Brad", "Brad@gmail.com", "123214"));
            studentDao.insertSingleStudent(new Student( "Tom", "Tom@gmail.com", "034564"));

            return null;
        }
    }


    }


