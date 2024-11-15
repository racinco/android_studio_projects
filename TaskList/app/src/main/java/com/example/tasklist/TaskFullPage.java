package com.example.tasklist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import static com.example.tasklist.TaskEntity.TASK_ID_KEY;
//TODO create interface for TaskFull Page
public class TaskFullPage extends AppCompatActivity {


    private  TaskDatabase database;
    private TextView titleTextView, status, description, date,cardTitle_TFP;
    private long taskID;
    private LinearLayout linearLayoutForRequirements;
    private CheckBox checkBoxForReqs, checkBoxForReqsAdded, checkBoxForRemovingReqs;
    private ArrayList<Requirements> array_requirements;
    private ArrayList<EditText> arrayRequirements_edtText;
    private Button addReqsBtntaskFullPage, addReqsButton_TFP, deleteReqsButton_TaskFullPage;
    private FloatingActionButton save_fab_TFP,  cancel_fab_TFP;
    private int addedIdForCB;
    private EditText tasksTitle_Edt_TFP, scheduledDate_Edt_TFP, editTextRequirements;
    private LinearLayout edtText_linearLayout_TFP, edtText_linearLayout_TFP_remove;
    private CardView editReqs_card;
    private ConstraintLayout fab_constraint_TFP, constraint_editTask_layout, constraint_RemoveRequirements_layout;
    private ArrayList<CheckBox> checkBoxesForRemoval;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_full_page);

        // get Intent

        Intent intent = getIntent();
        if (intent != null){
            taskID = intent.getLongExtra(TASK_ID_KEY, -1);
        }
        // set Databse
        database = TaskDatabase.getInstance(this);
        // set Views
        initViews();
        // TODO change Data retrieval to Live Data retrieval
        // get single entity
        TaskEntity task = database.taskdao().getSingleTask(taskID);
        if(task == null){
            Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
        }

        // set taskData to views in TEXT
        if (null != task){
            // SET TITLE
            titleTextView.setText(task.getTitle());
            // SET DESCRIPTION
            description.setText(task.getDescription());
            //SET DATE
            date.setText(task.getDate());
            //SET STATUS
            if (task.getStatus()){
                status.setText("DONE");
            }else{
                status.setText("PENDING");
            }

            // SET REQUIREMENTS
            String reqs_String = "";
            array_requirements = new ArrayList<>();
            array_requirements =  task.getRequirements();

            // create a check box for each requirement
            for ( int i = 0 ; i < array_requirements.size(); i++){
                checkBoxForReqs = new CheckBox(this);
                checkBoxForReqs.setId(i);
                checkBoxForReqs.setChecked(array_requirements.get(i).getReqChecked());
                checkBoxForReqs.setText(array_requirements.get(i).getName());

                checkBoxForReqs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                        // Update the database if checked
                        if (isChecked){
                            Requirements updateRequirements = new Requirements(array_requirements.get(compoundButton.getId()).getName(), true);
                            array_requirements.set(compoundButton.getId(), updateRequirements);
                            database.taskdao().updateRequirements(taskID, array_requirements);
                        }else{
                            Requirements updateRequirements = new Requirements(array_requirements.get(compoundButton.getId()).getName(), false);
                            array_requirements.set(compoundButton.getId(), updateRequirements);
                            database.taskdao().updateRequirements(taskID, array_requirements);
                        }

                    }
                });

                linearLayoutForRequirements.addView(checkBoxForReqs);

            }

            //CREATE a checkbox for the Views
            for ( int i = 0 ; i < array_requirements.size(); i++){
                checkBoxForRemovingReqs = new CheckBox(TaskFullPage.this);
                checkBoxForRemovingReqs.setId(i);
                checkBoxForRemovingReqs.setText(array_requirements.get(i).getName());


                checkBoxesForRemoval.add(checkBoxForRemovingReqs);
                edtText_linearLayout_TFP_remove.addView(checkBoxForRemovingReqs);

            }

            // SHOW CARD
            addReqsBtntaskFullPage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    reverseVisibilityCard();
                }
            });

            // HIDE CARD
            cancel_fab_TFP.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    reverseVisibilityCard();
                    if(constraint_RemoveRequirements_layout.getVisibility() == View.VISIBLE){
                        reverseVisibilityLayout();
                    }
                }
            });


            // CREATE New Edit Texts for Each add button pressed
            addReqsButton_TFP.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editTextRequirements = new EditText(TaskFullPage.this);
                    editTextRequirements.setHint("Enter");
                    editTextRequirements.setEms(10);
                    editTextRequirements.setTextSize(20);
                    editTextRequirements.setTypeface(Typeface.DEFAULT, Typeface.BOLD_ITALIC );
                    edtText_linearLayout_TFP.addView(editTextRequirements);
                    arrayRequirements_edtText.add(editTextRequirements);
                }
            });

            // SAVE NEW EDITS
            addedIdForCB = array_requirements.size()-1;
            save_fab_TFP.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Read data then Replace textviews

                    if (tasksTitle_Edt_TFP.getText().toString().equals("")){
                        // do nothing
                    }else{
                        titleTextView.setText(tasksTitle_Edt_TFP.getText().toString());
                    }

                    if (scheduledDate_Edt_TFP.getText().toString().equals("")){
                            // do nothing
                    }else{
                        date.setText(scheduledDate_Edt_TFP.getText().toString());
                    }

                    for ( EditText e: arrayRequirements_edtText){
                        if (e.getText().toString().equals("")) {
                                // do nothing
                            }else{
                                Requirements addedRequirements = new Requirements(e.getText().toString(), false);
                                array_requirements.add(addedRequirements);

                                addedIdForCB +=1;
                                checkBoxForReqsAdded = new CheckBox( TaskFullPage.this);
                                checkBoxForReqsAdded.setId(addedIdForCB);
                                checkBoxForReqsAdded.setChecked(false);
                                checkBoxForReqsAdded.setText(e.getText().toString());
                                linearLayoutForRequirements.addView(checkBoxForReqsAdded);
                            }
                    }

                    if(constraint_RemoveRequirements_layout.getVisibility() == View.VISIBLE){

                        // TODO DELETE THE SELECTED ROWS
                        int count = 0;
                        for (CheckBox e: checkBoxesForRemoval){

                            if(e.isChecked()){
                                for (int i = 0; i <array_requirements.size(); i++){
                                    if (e.getText().toString().equals(array_requirements.get(i).getName())){
                                        array_requirements.remove(i);
                                    }
                                }
                            }

                        }
                        Toast.makeText(TaskFullPage.this, String.valueOf(count), Toast.LENGTH_SHORT).show();
                    }

                    database.taskdao().updateTask(taskID,titleTextView.getText().toString(), date.getText().toString(), array_requirements);
                    resetAllEditTexts();

                    // reset the whole TASK FULL PAGE
                    Intent intent = new Intent(TaskFullPage.this, TaskFullPage.class);
                    intent.putExtra(TASK_ID_KEY,taskID);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                }


            });

            // SHOW CARD
            deleteReqsButton_TaskFullPage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    reverseVisibilityCard();
                    reverseVisibilityLayout();

                }
            });

        }
    }

    private void initViews (){
        arrayRequirements_edtText = new ArrayList<>();
        checkBoxesForRemoval = new ArrayList<>();

        titleTextView =findViewById(R.id.title_fullPage);
        status = findViewById(R.id.status_fullPage);
        description = findViewById(R.id.description_fullPage);
        date = findViewById(R.id.date_fullPage);
        cardTitle_TFP =findViewById(R.id.cardTitle_TFP);

        addReqsBtntaskFullPage = findViewById(R.id.addReqsButton_TaskFullPage);
        save_fab_TFP = findViewById(R.id.save_fab_TFP);
        cancel_fab_TFP = findViewById(R.id.cancel_fab_TFP);
        addReqsButton_TFP = findViewById(R.id.addReqsButton_TFP);
        deleteReqsButton_TaskFullPage = findViewById(R.id.deleteReqsButton_TaskFullPage);

        tasksTitle_Edt_TFP = findViewById(R.id.tasksTitle_Edt_TFP);
        scheduledDate_Edt_TFP = findViewById(R.id.scheduledDate_Edt_TFP);

        linearLayoutForRequirements = (LinearLayout) findViewById(R.id.linearLayout_forRequirements);
        edtText_linearLayout_TFP_remove = (LinearLayout) findViewById(R.id.edtText_linearLayout_TFP_remove);
        edtText_linearLayout_TFP = findViewById(R.id.edtText_linearLayout_TFP);
        fab_constraint_TFP = findViewById(R.id.fab_constraint_TFP);
        constraint_RemoveRequirements_layout = findViewById(R.id.constraint_RemoveRequirements_layout);
        constraint_editTask_layout = findViewById(R.id.constraint_editTask_layout);

        editReqs_card = findViewById(R.id.editReqs_card);

    }

    private void reverseVisibilityCard (){
        // revese the visibility of the card
        switch (editReqs_card.getVisibility()){
            case View.VISIBLE:
                editReqs_card.setVisibility(View.GONE);
                fab_constraint_TFP.setVisibility(View.GONE);
                break;

            case View.GONE:
                editReqs_card.setVisibility(View.VISIBLE);
                fab_constraint_TFP.setVisibility(View.VISIBLE);
                break;
        }

    }

    private void resetAllEditTexts(){
        tasksTitle_Edt_TFP.setText("");
        scheduledDate_Edt_TFP.setText("");
        arrayRequirements_edtText.clear();
    }
    
    private void reverseVisibilityLayout(){
        switch ( constraint_editTask_layout.getVisibility()){

            case View.VISIBLE:
                constraint_editTask_layout.setVisibility(View.GONE);
                constraint_RemoveRequirements_layout.setVisibility(View.VISIBLE);
                cardTitle_TFP.setText("REMOVE");
                break;
            case View.GONE:
                constraint_editTask_layout.setVisibility(View.VISIBLE);
                constraint_RemoveRequirements_layout.setVisibility(View.GONE);
                cardTitle_TFP.setText("EDIT TASK");
                break;

        }
    }


    }
