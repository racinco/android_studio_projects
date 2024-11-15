package com.example.booklibrary;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DatabaseRetriever {
    // This database retriever class will act as a way to retrieve the data from the database helper Class with ease

    private Context context;
    private ArrayList<Book> bookArrayList;
    private DatabaseHelper databaseHelper;
    private Cursor cursor;
    public DatabaseRetriever(Context context) {
        this.context = context;
    }

    public void initiateRetrieveData_select (String table, String selection, String [] selectionArgs ){
        try{
            databaseHelper = new DatabaseHelper(context);
            bookArrayList = new ArrayList<>();
            SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

            cursor = sqLiteDatabase.query(table,null,selection,selectionArgs,null,null,null);


            if (cursor != null ){
                if (cursor.moveToFirst()){
                    int id = cursor.getInt(cursor.getColumnIndex("id"));
                    String bookname = cursor.getString(cursor.getColumnIndex("bookname"));
                    String Author = cursor.getString(cursor.getColumnIndex("Author"));
                    int Pages = cursor.getInt(cursor.getColumnIndex("Pages"));
                    String ImageURL = cursor.getString(cursor.getColumnIndex("ImageURL"));
                    String shortDesc = cursor.getString(cursor.getColumnIndex("shortDesc"));
                    String longDesc = cursor.getString(cursor.getColumnIndex("longDesc"));
                    for (int i = 0 ; i < cursor.getCount(); i++){
                        Book books = new Book();



                        books.setId(id);
                        books.setName(bookname);
                        books.setAuthor(Author);
                        books.setPages(Pages);
                        books.setImageUrl(ImageURL);
                        books.setShortDesc(shortDesc);
                        books.setLongDesc(longDesc);

                        bookArrayList.add(books);
                        cursor.moveToNext();
                    }

                }else{
                    cursor.close();
                    sqLiteDatabase.close();
                }
                cursor.close();
                sqLiteDatabase.close();
            }else{
                sqLiteDatabase.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

    }
    public void initiateRetrieveData_all (String table ){
        try{
            databaseHelper = new DatabaseHelper(context);
            bookArrayList = new ArrayList<>();
            SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

            cursor = sqLiteDatabase.query(table,null,null,null,null,null,null);

            if (cursor != null ){
                if (cursor.moveToFirst()){


                    for (int i = 0 ; i < cursor.getCount(); i++){
                        Book books = new Book();
                        int id = cursor.getInt(cursor.getColumnIndex("id"));
                        String bookname = cursor.getString(cursor.getColumnIndex("bookname"));
                        String Author = cursor.getString(cursor.getColumnIndex("Author"));
                        int Pages = cursor.getInt(cursor.getColumnIndex("Pages"));
                        String ImageURL = cursor.getString(cursor.getColumnIndex("ImageURL"));
                        String shortDesc = cursor.getString(cursor.getColumnIndex("shortDesc"));
                        String longDesc = cursor.getString(cursor.getColumnIndex("longDesc"));
                        books.setId(id);
                        books.setName(bookname);
                        books.setAuthor(Author);
                        books.setPages(Pages);
                        books.setImageUrl(ImageURL);
                        books.setShortDesc(shortDesc);
                        books.setLongDesc(longDesc);

                        bookArrayList.add(books);
                        cursor.moveToNext();
                    }

                }else{
                    cursor.close();
                    sqLiteDatabase.close();
                }
                cursor.close();
                sqLiteDatabase.close();
            }else{
                sqLiteDatabase.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

    }
    public ArrayList<Book> retrieveData(){
        return bookArrayList;
    }

}
