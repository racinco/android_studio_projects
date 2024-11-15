package com.example.booklibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView booksRecView, booksRecView_right;
    private RecyclerViewAdapter_main booksRecView_adapter, booksRecView_adapter_right;
    private DatabaseHelper databaseHelper;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Book> books_arraylist = new ArrayList<>();

        DatabaseRetriever databaseRetriever = new DatabaseRetriever(this);
        databaseRetriever.initiateRetrieveData_all("books");
        books_arraylist = databaseRetriever.retrieveData();

        /*String text = "";
        for (Book book: books_arraylist ){
            text  = book.getAuthor();
        }

        textView.setText(text);

        // initialize the date
           /*
       booksRecView_adapter = new RecyclerViewAdapter_main(this);
       booksRecView_adapter.setBooks( insert array list of data); >> set data
       booksRecView = findViewById(R.id.booksRecView);
       booksRecView.setAdapter(booksRecView_adapter);
       booksRecView.setLayoutManager(new LinearLayoutManager(this));
        */
        booksRecView = findViewById(R.id.booksRecView);
        booksRecView_adapter = new RecyclerViewAdapter_main(this);
        booksRecView_adapter.setBooks(books_arraylist);
        booksRecView.setAdapter(booksRecView_adapter);
        booksRecView.setLayoutManager(new GridLayoutManager(this,2));



    }
}