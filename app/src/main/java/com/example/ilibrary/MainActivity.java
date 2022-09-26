package com.example.ilibrary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    Button btnAllBook, btnReadingBook, btnAllreadyReadBook, btnWishlst, btnFavourite, btnAbout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        initView();
        btnAllBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AllBooks.class);
                startActivity(intent);
            }
        });
        btnAllreadyReadBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AlreadyReadBook.class);
                startActivity(intent);
            }
        });
        btnWishlst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WishList.class);
                startActivity(intent);
            }
        });
        btnFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Favorites.class);
                startActivity(intent);
            }
        });
        btnReadingBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CurrentlyReadingBooks.class);
                startActivity(intent);
            }
        });
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(getString(R.string.app_name));
                builder.setMessage("Designed and Developed by Pratik Waghaskar");
                builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                    }
                });
                builder.create().show();
            }
        });
        Utils.getInstance();
        
    }

    private void initView() {
        btnAllBook = findViewById(R.id.btnAllBooks);
        btnReadingBook = findViewById(R.id.btnReadingBook);
        btnAllreadyReadBook = findViewById(R.id.btnAlredyReadBook);
        btnWishlst = findViewById(R.id.btnWhishlist);
        btnFavourite = findViewById(R.id.btnFavorites);
        btnAbout= findViewById(R.id.btnAbout);

    }
}