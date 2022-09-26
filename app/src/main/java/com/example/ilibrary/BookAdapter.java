package com.example.ilibrary;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static com.example.ilibrary.BookActivty.BOOK_ID_KEY;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder>{
    private static final String TAG = "BookAdapter";
    private ArrayList<Book> books = new ArrayList();
    private Context mContaxt;
    private String parentActivity;

    public BookAdapter(Context mContaxt, String parentActivity) {
        this.mContaxt = mContaxt;
        this.parentActivity = parentActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookAdapter.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: Called");
        holder.txtBook.setText(books.get(position).getName());
        Glide.with(mContaxt)
                .asBitmap()
                .load(books.get(position).getImageUrl())
                .into(holder.bookImg);

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContaxt, BookActivty.class);
                intent.putExtra(BOOK_ID_KEY, books.get(position).getId());
                mContaxt.startActivity(intent);
            }
        });


        holder.txtAuthor.setText(books.get(position).getAuthor());
        holder.txtShortdesc.setText(books.get(position).getShortDesc());

        if (books.get(position).isExpanded()) {
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedRelayout.setVisibility(View.VISIBLE);
            holder.downArrow.setVisibility(View.GONE);

            if (parentActivity.equals("allBooks")) {
                holder.btnDelete.setVisibility(View.GONE);
            } else if (parentActivity.equals("alreadyRead")) {
                holder.btnDelete.setVisibility(View.VISIBLE);
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContaxt);
                        builder.setMessage("Are you sure you want to delete " + books.get(position).getName() + "?");
                        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (Utils.getInstance().removeFromAlreadyReadBooks(books.get(position))) {
                                    Toast.makeText(mContaxt, "Book Removed Successfully", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        builder.create().show();
                    }
                });
            } else if (parentActivity.equals("wantToRead")) {
                holder.btnDelete.setVisibility(View.VISIBLE);
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContaxt);
                        builder.setMessage("Are you sure you want to delete " + books.get(position).getName() + "?");
                        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (Utils.getInstance().removeFromWishList(books.get(position))) {
                                    Toast.makeText(mContaxt, "Book Removed Successfully", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        builder.create().show();
                    }
                });
            } else if (parentActivity.equals("currentlyReading")) {
                holder.btnDelete.setVisibility(View.VISIBLE);
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContaxt);
                        builder.setMessage("Are you sure you want to delete " + books.get(position).getName() + "?");
                        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (Utils.getInstance().removeFromCureentRead(books.get(position))) {
                                    Toast.makeText(mContaxt, "Book Removed Successfully", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        builder.create().show();
                    }
                });
            } else {
                holder.btnDelete.setVisibility(View.VISIBLE);
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContaxt);
                        builder.setMessage("Are you sure you want to delete " + books.get(position).getName() + "?");
                        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (Utils.getInstance().removeFromFavBooks(books.get(position))) {
                                    Toast.makeText(mContaxt, "Book Removed Successfully", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        builder.create().show();
                    }
                });
            }
        }else {
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedRelayout.setVisibility(View.GONE);
            holder.downArrow.setVisibility(View.VISIBLE);
        }

        }


    @Override
    public int getItemCount() {
        return books.size();
    }


    public void setBook(ArrayList<Book> book) {
        this.books = book;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private CardView parent;
        private TextView txtBook;
        private ImageView bookImg;
        private ImageView upArrow,downArrow;
        private TextView txtAuthor,txtShortdesc;
        private RelativeLayout expandedRelayout;
        private TextView btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent1);
            txtBook = itemView.findViewById(R.id.bookName);
            bookImg = itemView.findViewById(R.id.bookImage);

            upArrow = itemView.findViewById(R.id.up_arrow);
            downArrow= itemView.findViewById(R.id.down_arrow);
            txtAuthor = itemView.findViewById(R.id.txtauthor);
            txtShortdesc = itemView.findViewById(R.id.txtShortDesc);
            expandedRelayout = itemView.findViewById(R.id.expandedRecycler);
            btnDelete = itemView.findViewById(R.id.btnDeletee);

            downArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Book book = books.get(getAdapterPosition());
                    book.setExpanded(!book.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });
            upArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Book book = books.get(getAdapterPosition());
                    book.setExpanded(!book.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });

        }
    }
}
