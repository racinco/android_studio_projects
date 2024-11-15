package com.example.solelyfortesting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    private CheckBox checkBox;
    private Button addButton, saveButton;
    private EditText editText;
    private int addedId ;
    private LinearLayout linearLayout2;
    private ArrayList<EditText> arrayList_edittxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        /*
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.parent);
        linearLayout2 = (LinearLayout) findViewById(R.id.buttonDynamic);
        addButton = findViewById(R.id.addButton);
        saveButton = findViewById(R.id.saveButton);


        ArrayList<String> checkList= new ArrayList<>();
        checkList.add("check 1");
        checkList.add("check 2");
        checkList.add("check 3");
        checkList.add("check 4");
        checkList.add("check 5");
        arrayList_edittxt = new ArrayList<EditText>();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText = new EditText(MainActivity.this);
                editText.setId(addedId);
                editText.setHint("Enter");
                editText.setEms(10);
                editText.setTextSize(20);
                editText.setTypeface(Typeface.DEFAULT, Typeface.BOLD_ITALIC );
                arrayList_edittxt.add(editText);
                linearLayout2.addView(editText);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt = "";
                for (EditText e: arrayList_edittxt){
                    txt += e.getText().toString() + "\n " ;
                }
                Toast.makeText(MainActivity.this, txt, Toast.LENGTH_SHORT).show();
            }
        });


        for (int i = 0 ; i < checkList.size(); i++){
            checkBox = new CheckBox(this);
            checkBox.setId(i);
            checkBox.setText(checkList.get(i));
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean ischecked) {
                    if (ischecked == true) {
                        String id = String.valueOf( compoundButton.getId());
                        Toast.makeText(MainActivity.this, id, Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(MainActivity.this, "Unchecked", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            linearLayout.addView(checkBox);
        }

         */

        final ListView listView_cb = (ListView) findViewById(R.id.listView_CB);
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("1");
        stringList.add("2");
        stringList.add("3");
        stringList.add("4");
        stringList.add("5");
        stringList.add("6");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_checked, stringList);
        listView_cb.setAdapter(arrayAdapter);


        listView_cb.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(MainActivity.this, String.valueOf(l),Toast.LENGTH_SHORT).show();
            }
        });
        listView_cb.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(MainActivity.this, "long",Toast.LENGTH_SHORT).show();
                return false;
            }
        });









    }
}