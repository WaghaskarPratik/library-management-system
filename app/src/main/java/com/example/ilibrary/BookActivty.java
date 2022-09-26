package com.example.ilibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivty extends AppCompatActivity {

    public static final String BOOK_ID_KEY = "bookId";
    private TextView BklongDesc, bkName, bkAuthor, bkPage;
    private Button btnCurrentlyRead, btnAddToFav, btnWantRead, btnAlRead;
    private ImageView BkImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_activty);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.teal_700)));
        initViews();

        Intent intent = getIntent();
        if (null != intent){
            int bookId = intent.getIntExtra(BOOK_ID_KEY, -1);
            if (bookId != -1){
                Book incomingBook = Utils.getInstance().getBookById(bookId);
                if (null != incomingBook){
                    setData(incomingBook);
                    handleAlreadyRead(incomingBook);
                    handleWantToReadBook(incomingBook);
                    handleCurretlyReadBook(incomingBook);
                    handleAddToFav(incomingBook);
                }
            }
        }
    }

    private void handleCurretlyReadBook(Book book) {
        ArrayList<Book> currentRead = Utils.getInstance().getCurrentlyReadingBook();

        boolean existInCurrentlyReadBooks  = false;
        for (Book b: currentRead){
            if (b.getId() == book.getId()){
                existInCurrentlyReadBooks = true;
            }
        }

        if (existInCurrentlyReadBooks){
            btnCurrentlyRead.setEnabled(false);
        }else {
            btnCurrentlyRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Utils.getInstance().addToCurrentRead(book)){
                        Toast.makeText(BookActivty.this, "Added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivty.this, CurrentlyReadingBooks.class);
                        startActivity(intent);
                    }
                }
            });
        }
    }



    private void handleWantToReadBook(final Book book) {
        ArrayList<Book> wantToRead = Utils.getInstance().getWantToReadBooks();

        boolean existInWantRead  = false;
        for (Book b: wantToRead){
            if (b.getId() == book.getId()){
                existInWantRead = true;
            }
        }

        if (existInWantRead){
            btnWantRead.setEnabled(false);
        } else {
            btnWantRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Utils.getInstance().addToWishlist(book)){
                        Toast.makeText(BookActivty.this, "Added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivty.this, WishList.class);
                        startActivity(intent);
                    }
                }
            });
        }
    }

    private void handleAddToFav(final Book book) {
        ArrayList<Book> favBooks = Utils.getInstance().getFavoriteBooks();

        boolean existInFavBooks  = false;
        for (Book b: favBooks){
            if (b.getId() == book.getId()){
                existInFavBooks = true;
            }
        }

        if (existInFavBooks){
            btnAddToFav.setEnabled(false);
        }else {
            btnAddToFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Utils.getInstance().addToFav(book)){
                        Toast.makeText(BookActivty.this, "Added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivty.this, Favorites.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(BookActivty.this, "Something Wrong...", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleAlreadyRead(final Book book) {
        ArrayList<Book> alredyReadbooks = Utils.getInstance().getAlreadyReadBooks();

        boolean existInAlreadyReadBooks  = false;
        for (Book b: alredyReadbooks){
            if (b.getId() == book.getId()){
                existInAlreadyReadBooks = true;
            }
        }

        if (existInAlreadyReadBooks){
            btnAlRead.setEnabled(false);
        }else {
            btnAlRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Utils.getInstance().addToAlreadyRead(book)){
                        Toast.makeText(BookActivty.this, "Added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivty.this, AlreadyReadBook.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(BookActivty.this, "Something Wrong...", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    private void setData(Book book) {
        bkName.setText(book.getName());
        bkAuthor.setText(book.getAuthor());
        bkPage.setText(String.valueOf(book.getPages()));
        BklongDesc.setText(book.getLongDesc());
        Glide.with(this)
                .asBitmap()
                .load(book.getImageUrl())
                .into(BkImage);
    }

    private void initViews() {

        BklongDesc=findViewById(R.id.BkLongDesc);
        bkName = findViewById(R.id.bkName);
        bkAuthor = findViewById(R.id.bkAuthor);
        bkPage =findViewById(R.id.bkPage);
        btnCurrentlyRead = findViewById(R.id.currentlyRead);
        btnWantRead = findViewById(R.id.btnWantRead);
        btnAlRead = findViewById(R.id.btnAlRead);
        btnAddToFav = findViewById(R.id.btnAdFav);
        BkImage = findViewById(R.id.BKImage);
    }
}