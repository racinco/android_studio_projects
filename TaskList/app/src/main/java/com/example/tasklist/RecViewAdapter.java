package com.example.tasklist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static com.example.tasklist.TaskEntity.TASK_ID_KEY;

public class RecViewAdapter extends RecyclerView.Adapter<RecViewAdapter.Viewholder>  {

    public interface DeleteContact{
        void OnDeleteContact(long taskID);
    }
    public interface UpdateContact{
        void OnUpdateContact(long taskId, Boolean newStatus);
    }


    private DeleteContact deleteContact;
    private UpdateContact updateContact;

    private List<TaskEntity> arraylist_tasks =  new ArrayList<>();
    private Context context;

    // pass Context
    public RecViewAdapter(Context context) {
        this.context = context;
    }

    // set books
    public void setArraylist_tasks(List<TaskEntity> arraylist_tasks) {
        this.arraylist_tasks = arraylist_tasks;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myview = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_task_card, parent,false);
        return new Viewholder(myview);
    }

    @Override
    public void onBindViewHolder(@NonNull final Viewholder holder, final int position) {
        //TODO add To do list // CHECKED

        // Set the star color  NOTE: if status null, then there woould be an error
        try {
            if (arraylist_tasks.get(position).getStatus()){
                holder.starImageView.setImageIcon(Icon.createWithResource(context,R.drawable.ic_star_gold));

            }
            if (arraylist_tasks.get(position).getStatus() == false){
                holder.starImageView.setImageIcon(Icon.createWithResource(context,R.drawable.ic_star));
            }
        }catch ( Exception e){
            e.printStackTrace();
        }





        holder.titleTextView.setText(arraylist_tasks.get(position).getTitle());
        if (arraylist_tasks.get(position).getStatus()){
            holder.status.setText("DONE");
        }else{
            holder.status.setText("PENDING");
        }

        holder.task_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (context, TaskFullPage.class);
                intent.putExtra(TASK_ID_KEY, arraylist_tasks.get(position).getTaskId());
                context.startActivity(intent);
            }
        });

        holder.task_card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                // TODO Deleting with the use of interface// DONE
                AlertDialog.Builder alert_dialog = new AlertDialog.Builder(context)
                        .setTitle("Delete Task")
                        .setMessage("Do you wish to delete task?")
                        .setNegativeButton("No", null)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                try{
                                    deleteContact = (DeleteContact) context;
                                    deleteContact.OnDeleteContact(arraylist_tasks.get(position).getTaskId());
                                }catch(Exception e){
                                    e.printStackTrace();
                                }

                            }
                        });
                alert_dialog.show();
                return true;
            }
        });

        holder.starImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (arraylist_tasks.get(position).getStatus()){
                    // TODO Update Database by clicking star
                    try{
                        updateContact = (UpdateContact) context;
                        updateContact.OnUpdateContact(arraylist_tasks.get(position).getTaskId(), false);
                        Toast.makeText(context, "Removed From Favorites", Toast.LENGTH_SHORT).show();
                    }catch(Exception e){
                        e.printStackTrace();
                    }

                }
                if(arraylist_tasks.get(position).getStatus() == false){
                    try{
                        updateContact = (UpdateContact) context;
                        updateContact.OnUpdateContact(arraylist_tasks.get(position).getTaskId(), true);
                        Toast.makeText(context, "Added To Favorites", Toast.LENGTH_SHORT).show();
                    }catch(Exception e){
                       e.printStackTrace();
                    }

                }


            }
        });


    }

    @Override
    public int getItemCount() {
        return arraylist_tasks.size();
    }




    public class Viewholder extends RecyclerView.ViewHolder{
        // TODO CREATE viewholder class // DONE
        // TODO add parameters // DONE
        private CardView task_card;
        private TextView titleTextView, status;
        private ImageView starImageView;


        public Viewholder(@NonNull View itemView) {
            super(itemView);
            task_card = itemView.findViewById(R.id.task_card);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            status = itemView.findViewById(R.id.status);
            starImageView = itemView.findViewById(R.id.starImageView);
            // TODO add ID // DONE
        }
    }



}
