package com.example.booklibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.booklibrary.Book.BOOK_ID_KEY;

public class CardFullViewActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private  TextView txt_id, txt_bookname, txt_author, txt_shortDescription, txt_longDescription, txt_pages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_full_view);
        ArrayList <Book> books_arraylist = new ArrayList<>();
        Intent intent =getIntent();

        initializeXmlData();
        if (intent != null){
            int bookId = intent.getIntExtra(BOOK_ID_KEY,-1);
            // using the book Id retrieve the data from the database

            /*
            databaseHelper = new DatabaseHelper(this);
            SQLiteDatabase database = databaseHelper.getReadableDatabase();

            Cursor cursor = database.query("books", null,"id=?",new String[]{String.valueOf(bookId)},null,null,null);

            if (cursor != null){
                if (cursor.moveToFirst()){
                    Book books = new Book();
                    int id = cursor.getInt(cursor.getColumnIndex("id")); // the cursor contains the entire rows value. The cursor.get columnIndex "id" gets the index of the COLUMN ID
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

                    books_arraylist.add(books);      // add each book to the book array list



                    Toast.makeText(this, books_arraylist.get(0).toString(), Toast.LENGTH_SHORT).show();
                }else{
                    cursor.close();
                    database.close();
                }
                cursor.close();
                database.close();
            }else{
                database.close();
            }
            */

            DatabaseRetriever databaseRetriever = new DatabaseRetriever(this);
            databaseRetriever.initiateRetrieveData_select("books", "id=?", new String[]{String.valueOf(bookId)}); // TODO fix null problem here
           // databaseRetriever.initiateRetrieveData_all("books");
            books_arraylist = databaseRetriever.retrieveData();

            txt_id.setText(Integer.toString(books_arraylist.get(0).getId()));
            txt_pages.setText(Integer.toString (books_arraylist.get(0).getPages()) );
            txt_author.setText(books_arraylist.get(0).getAuthor());
            txt_bookname.setText(books_arraylist.get(0).getName());
            txt_longDescription.setText(books_arraylist.get(0).getLongDesc());
            txt_shortDescription.setText(books_arraylist.get(0).getShortDesc());

        }
    }

    private void initializeXmlData(){
        txt_id = findViewById(R.id.txt_id);
        txt_bookname = findViewById(R.id.txt_bookname);
        txt_author = findViewById(R.id.txt_author);
        txt_pages = findViewById(R.id.txt_pages);
        txt_shortDescription = findViewById(R.id.txt_shortDescription);
        txt_longDescription = findViewById(R.id.txt_longDescription);
    }
}