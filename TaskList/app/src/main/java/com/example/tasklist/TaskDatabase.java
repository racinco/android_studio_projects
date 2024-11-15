package com.example.tasklist;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.ArrayList;

@Database(entities = {TaskEntity.class}, version = 1)   // only have one entity
public abstract class TaskDatabase extends RoomDatabase {

    public abstract TaskDao taskdao();

    // Create a Singleton patternD

    private static TaskDatabase instance;

    public static synchronized TaskDatabase getInstance( Context context){

        if (instance == null){
            instance = Room.databaseBuilder(context,TaskDatabase.class, "task_db" )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .addCallback(initialCallback)
                    .build();
        }
        return instance;
    }


    private static RoomDatabase.Callback initialCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            new PopulateInitialData(instance).execute();
        }
    };

    private static class PopulateInitialData extends AsyncTask<Void,Void,Void>{

        private TaskDao taskDao;

        public PopulateInitialData( TaskDatabase database ) {
            this.taskDao = database.taskdao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            // insert data
            ArrayList<Requirements> array_requirements = new ArrayList<>();
           Requirements requirements = new Requirements("One Requirement", true);
            Requirements requirements2 = new Requirements("Two Requirement", false);

            array_requirements.add(requirements);
            array_requirements.add(requirements2);

            // Home
            taskDao.insertSingleTask( new TaskEntity("Task 1", "Home",array_requirements , "This is the description", "Jan 2021", false) ) ;
            taskDao.insertSingleTask( new TaskEntity("Task 2", "Home", array_requirements, "This is the description", "Feb 2021", false) ) ;
            taskDao.insertSingleTask( new TaskEntity("Task 3", "Home", array_requirements, "This is the description", "Mar 2021", false) ) ;

            // Work
            taskDao.insertSingleTask( new TaskEntity("Work 1", "Work", array_requirements, "This is the description", "Jan 2021", false) ) ;
            taskDao.insertSingleTask( new TaskEntity("Work 2", "Work", array_requirements, "This is the description", "Feb 2021", false) ) ;
            taskDao.insertSingleTask( new TaskEntity("Work 3", "Work", array_requirements, "This is the description", "Mar 2021", false) ) ;

            // Thesis
            taskDao.insertSingleTask( new TaskEntity("Thesis 1", "Thesis", array_requirements, "This is the description", "Jan 2021", false) ) ;
            taskDao.insertSingleTask( new TaskEntity("Thesis 2", "Thesis ", array_requirements, "This is the description", "Feb 2021", false) ) ;
            taskDao.insertSingleTask( new TaskEntity("Thesis 3", "Thesis", array_requirements, "This is the description", "Mar 2021", false) ) ;


            return null;
        }
    }






}
