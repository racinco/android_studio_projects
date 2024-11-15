package com.example.booklibrary;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static com.example.booklibrary.Book.BOOK_ID_KEY;


// Steps
// Create a RecAdapter class >> create a Viewholder class extends.Viewholder .. implemtent super
// extend recycler view adapter in recadapter >> implement methods
// Create a BOOK CLASS to which the recycler view would read the data
public class RecyclerViewAdapter_main extends RecyclerView.Adapter<RecyclerViewAdapter_main.Viewholder>{
    private ArrayList<Book> books_array = new ArrayList<>();  // get a list of Book Objects.
    private Context context;    // the context the is retrieved from the constructor


    public RecyclerViewAdapter_main(Context context_fromActvity) {
        this.context = context_fromActvity;
    }

    private final static String TAG ="BookRecViewAdapter";

    // we need to somehow get the list of data from the activity java itself thus
    // this code sets the books_array
    public void setBooks (ArrayList<Book> books_array_from_activity){
        this.books_array = books_array_from_activity;
    }


    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // we need to set the view we need
        View ourView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_book_card,parent,false);
        return new Viewholder(ourView);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: Called"); // this displays in the logcat if we end up in an error

        // in here we set each data of the books array on each Recycler view Card but since Recycler view just recycles
        // the used cards and changed its data. we need to place the data dynamically such as
        // set bookname,author, short description, image
        holder.txtBookname.setText(books_array.get(position).getName());
        holder.txtAuthor.setText(books_array.get(position).getAuthor());
        holder.txtShortDesc.setText(books_array.get(position).getShortDesc());

        //set the image
      Glide.with(context)
                .asBitmap()
                .load(books_array.get(position).getImageUrl())
                .into(holder.imgBook);

        // when the card is pressed Use Intent

        // when the downCardis pressed

        if (books_array.get(position).isExpanded()){
            holder.btnDownArrow.setVisibility(View.GONE);
            holder.expandedRl.setVisibility(View.VISIBLE);
        }

        holder.book_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CardFullViewActivity.class);
                intent.putExtra(BOOK_ID_KEY,books_array.get(position).getId() ); // send the ID of the book clicked
                context.startActivity(intent);
            }
        });

        holder.book_card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return books_array.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        private CardView book_card;
        private ImageView imgBook, btnDownArrow, btnUpArrow;
        private TextView txtAuthor, txtShortDesc, txtBookname, deleteBtn;
        private RelativeLayout expandedRl,collapsedRl;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            book_card = itemView.findViewById(R.id.book_card);
            imgBook = itemView.findViewById(R.id.imgBook);
            txtBookname = itemView.findViewById(R.id.txtBookname);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);
            btnDownArrow = itemView.findViewById(R.id.btnDownArrow);
            btnUpArrow = itemView.findViewById(R.id.btnUpArrow);
            expandedRl = itemView.findViewById(R.id.expandedRl);
            collapsedRl = itemView.findViewById(R.id.collapsedRl);
            txtAuthor = itemView.findViewById(R.id.txtAuthor);
            txtShortDesc = itemView.findViewById(R.id.txtshortDesc);

            btnDownArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Book book = books_array.get(getAdapterPosition()); // getAdapterPosition() will show the position of the clicked cardview
                    book.setExpanded(!book.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });

            btnUpArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Book book = books_array.get(getAdapterPosition()); // getAdapterPosition() will show the position of the clicked cardview
                    book.setExpanded(!book.isExpanded());               // inverts the isExpanded which is initially true, because of the down arrow, it becomes false
                    notifyItemChanged(getAdapterPosition());
                }
            });

        }
    }
}
