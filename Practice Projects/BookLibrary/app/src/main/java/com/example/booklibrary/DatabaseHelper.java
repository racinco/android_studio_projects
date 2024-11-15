package com.example.booklibrary;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_name =  "booksDatabase";
    private static final int DB_version = 1;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_name, null, DB_version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlStatement = "CREATE TABLE books (id INTEGER , bookname TEXT , Author TEXT, Pages INTEGER, ImageURL TEXT, shortDesc TEXT,longDesc TEXT );";
        sqLiteDatabase.execSQL(sqlStatement);

        ContentValues values = new ContentValues();
        values.put("id","1");
        values.put("bookname", "Security Analysis");
        values.put("Author", "Benjamin Graham");
        values.put("Pages", "94");
        values.put("ImageURL","https://kinsta.com/wp-content/uploads/2019/08/jpg-vs-jpeg-1024x512.jpg");
        values.put("shortDesc", "A book created by Benjamin Graham ");
        values.put("longDesc", "A book created by Benjamin Graham");

        sqLiteDatabase.insert("books",null,values);

        ContentValues values2 = new ContentValues();
        values2.put("id","2");
        values2.put("bookname", "Sell Like Crazy");
        values2.put("Author", "Sabri Suby");
        values2.put("Pages", "312");
        values2.put("ImageURL","https://kinsta.com/wp-content/uploads/2019/08/jpg-vs-jpeg-1024x512.jpg");
        values2.put("shortDesc", "A book created by Sabri ");
        values2.put("longDesc", "A book created by Sabri");
        sqLiteDatabase.insert("books",null,values2);

        ContentValues values3 = new ContentValues();
        values3.put("id","3");
        values3.put("bookname", "Electronic Devices and Circuit Theory");
        values3.put("Author", "Robert L. Boylested");
        values3.put("Pages", "213");
        values3.put("ImageURL","https://kinsta.com/wp-content/uploads/2019/08/jpg-vs-jpeg-1024x512.jpg");
        values3.put("shortDesc", "A book created by Robert ");
        values3.put("longDesc", "A book created by Robert");
        sqLiteDatabase.insert("books",null,values3);

        ContentValues values4 = new ContentValues();
        values4.put("id","4");
        values4.put("bookname", "Semiconductor Device Fundamentals");
        values4.put("Author", "Robert Pierret");
        values4.put("Pages", "817");
        values4.put("ImageURL","https://kinsta.com/wp-content/uploads/2019/08/jpg-vs-jpeg-1024x512.jpg");
        values4.put("shortDesc", "A book created by Robert ");
        values4.put("longDesc", "A book created by Robert");
        sqLiteDatabase.insert("books",null,values4);

        ContentValues values5 = new ContentValues();
        values5.put("id","5");
        values5.put("bookname", "Security Analysis");
        values5.put("Author", "Benjamin Graham");
        values5.put("Pages", "94");
        values5.put("ImageURL","https://kinsta.com/wp-content/uploads/2019/08/jpg-vs-jpeg-1024x512.jpg");
        values5.put("longDesc", "A book created by Benjamin Graham");
        sqLiteDatabase.insert("books",null,values5);

        ContentValues values6 = new ContentValues();
        values6.put("id","6");
        values6.put("bookname", "Sell Like Crazy");
        values6.put("Author", "Sabri Suby");
        values6.put("Pages", "312");
        values6.put("ImageURL","https://kinsta.com/wp-content/uploads/2019/08/jpg-vs-jpeg-1024x512.jpg");
        values6.put("shortDesc", "A book created by Sabri ");
        values6.put("longDesc", "A book created by Sabri");
        sqLiteDatabase.insert("books",null,values6);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
