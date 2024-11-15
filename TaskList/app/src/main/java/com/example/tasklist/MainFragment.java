package com.example.tasklist;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.content.ContentValues.TAG;

public class MainFragment extends Fragment  {

    private TaskDatabase database;
    private RecyclerView main_recView;
    private  RecViewAdapter recViewAdapter;
    private FloatingActionButton addFloatbtn, save_fab, cancel_fab,more_fab;
    private ConstraintLayout fab_group;
    private CardView addTasksCard;
    private EditText tasksTitle_Edt,scheduledDate_Edt,editTextRequirements;
    private Button addReqsBtn;
    private ArrayList<EditText> arrayRequirements;
    private int addedId;
    private LinearLayout linearLayout_edtText;
    private ConstraintLayout constraint_task_layout, constraint_description_layout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.activity_main, container, false);
        // initiate parameters
        database =TaskDatabase.getInstance(getActivity());
        linearLayout_edtText = (LinearLayout) view.findViewById(R.id.edtText_linearLayout); // use this for the adding of edit text views
        initializeParameters(view);

        // BUTTONS
        addFloatbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reverseVisibilityAddTask(view);
            }
        });

        save_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // gather the data
                String taskTitle = tasksTitle_Edt.getText().toString();
                if(taskTitle.equals("")){
                    taskTitle = "Untitled";
                }

                String dueDate = scheduledDate_Edt.getText().toString();
                if(dueDate.equals("")){
                    dueDate = "NO DATE";
                }

                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                Date date = new Date();
                String schedDate = dateFormat.format(date);

                // Turn the Requirements into an ArrayList oF Requirements Object
                String requirements = "";
                ArrayList<Requirements> reqsClass_array = new ArrayList<>();
                for ( EditText e: arrayRequirements){
                    Requirements reqs_object = new Requirements(e.getText().toString(),false);
                    reqsClass_array.add(reqs_object);
                }

                database.taskdao().insertSingleTask(new TaskEntity(taskTitle,reqsClass_array,schedDate, false));

                //reset the arraylist
                linearLayout_edtText.removeAllViews();
                resetReqsandAddedid();
            }
        });

        cancel_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reverseVisibilityAddTask(view);
                resetReqsandAddedid();
            }

        });

        more_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (constraint_task_layout.getVisibility()){
                    case 0:
                        constraint_task_layout.setVisibility(view.GONE);
                        constraint_description_layout.setVisibility(view.VISIBLE);
                        break;
                    case 8:
                        constraint_task_layout.setVisibility(view.VISIBLE);
                        constraint_description_layout.setVisibility(view.GONE);
                        break;
                }
            }
        });

        addReqsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addedId += 1;
                editTextRequirements = new EditText(getActivity());
                editTextRequirements.setId(addedId);
                editTextRequirements.setHint("Enter");
                editTextRequirements.setEms(10);
                editTextRequirements.setTextSize(20);
                editTextRequirements.setTypeface(Typeface.DEFAULT, Typeface.BOLD_ITALIC );
                linearLayout_edtText.addView(editTextRequirements);
                arrayRequirements.add(editTextRequirements);
            }
        });



        // DATA

        LiveData<List<TaskEntity>> liveTasks = (LiveData<List <TaskEntity> >) database.taskdao().getAlltasks();
        liveTasks.observe(getActivity(), new Observer<List<TaskEntity>>() {
            @Override
            public void onChanged(List<TaskEntity> taskEntities) {
                // TODO change the order of the task entities
                Log.d(TAG, "ID of getActivity: "+ getActivity().toString());
                recViewAdapter = new RecViewAdapter(getActivity()) ;
                recViewAdapter.setArraylist_tasks(taskEntities);
                main_recView.setAdapter(recViewAdapter);
                main_recView.setLayoutManager(new GridLayoutManager(getActivity(),1));
            }
        });
        return view;
    }
    // initialize Views
    private void initializeParameters(View view){
        main_recView= view.findViewById(R.id.main_recView);
        addFloatbtn = view.findViewById(R.id.addFab);
        save_fab = view.findViewById(R.id.save_fab);
        cancel_fab = view.findViewById(R.id.cancel_fab);
        more_fab = view.findViewById(R.id.more_fab);

        tasksTitle_Edt = view.findViewById(R.id.tasksTitle_Edt);
        scheduledDate_Edt = view.findViewById(R.id.scheduledDate_Edt);

        addReqsBtn = view.findViewById(R.id.addReqsButton);
        arrayRequirements = new ArrayList<EditText>();

        constraint_description_layout = view.findViewById(R.id.constraint_description_layout);
        constraint_task_layout = view.findViewById(R.id.constraint_task_layout);

        reverseVisibilityAddTask(view);
    }
    // reverse the visibility of the AddTask Card
    private void reverseVisibilityAddTask(View view){
        addTasksCard = view.findViewById(R.id.addTasks_card);
        fab_group = view.findViewById(R.id.fab_constraint);

        switch (addTasksCard.getVisibility()){
            case View.VISIBLE:
                addTasksCard.setVisibility(View.GONE);
                fab_group.setVisibility(View.GONE);
                break;
            case View.GONE:
                addTasksCard.setVisibility(View.VISIBLE);
                fab_group.setVisibility(View.VISIBLE);
                break;
        }
    }
    // reset both the arraylist and the addded id after getting the data
    private void resetReqsandAddedid(){
        addedId = 0;
        arrayRequirements = new ArrayList<>();
        tasksTitle_Edt.setText("");
        scheduledDate_Edt.setText("");
    }

}
