package com.example.ilibrary;

import java.util.ArrayList;

public class Utils {
    public static Utils instance;
    private static ArrayList<Book> allBooks;
    private static ArrayList<Book> alreadyReadBooks;
    private static ArrayList<Book> wantToReadBooks;
    private static ArrayList<Book> currentlyReadingBook;
    private static ArrayList<Book> favoriteBooks;

    public Utils() {
        if (null == allBooks){
            allBooks = new ArrayList<>();
            initData();
        }
        if (null == alreadyReadBooks){
            alreadyReadBooks = new ArrayList<>();
        }
        if (null == wantToReadBooks){
            wantToReadBooks = new ArrayList<>();
        }

        if (null == currentlyReadingBook){
            currentlyReadingBook = new ArrayList<>();
        }

        if (null == favoriteBooks){
            favoriteBooks = new ArrayList<>();
        }
    }

    private void initData() {

        allBooks.add(new Book(1,"Rich Dad Poor Dad", "Robert T. Kiyosaki", 100, "https://images-na.ssl-images-amazon.com/images/I/81bsw6fnUiL.jpg", "Rich Dad Poor Dad",
                "long description"));
        allBooks.add(new Book(2, "Wings of Fire", "APJ Abdul Kalam", 150, "https://images-na.ssl-images-amazon.com/images/I/51wbVQTpTgL._SX339_BO1,204,203,200_.jpg",
                "Autobiography of apj Abdul Kalam", "long Description"));
    }

    public static ArrayList<Book> getAllBooks() {
        return allBooks;
    }

    public static ArrayList<Book> getAlreadyReadBooks() {
        return alreadyReadBooks;
    }

    public static ArrayList<Book> getWantToReadBooks() {
        return wantToReadBooks;
    }

    public static ArrayList<Book> getCurrentlyReadingBook() {
        return currentlyReadingBook;
    }

    public static ArrayList<Book> getFavoriteBooks() {
        return favoriteBooks;
    }

    public static Utils getInstance() {
        if (null != instance){
            return instance;
        }else {
            instance = new Utils();
            return instance;
        }
    }
    public Book getBookById(int id){
        for (Book b: allBooks){
            if (b.getId() == id){
                return b;
            }
        }
        return null;
    }


    public boolean addToAlreadyRead(Book book) {
        return alreadyReadBooks.add(book);
    }

    public boolean addToWishlist(Book book){
        return wantToReadBooks.add(book);
    }
    public boolean addToFav(Book book){
        return favoriteBooks.add(book);
    }
    public boolean addToCurrentRead(Book book){
        return currentlyReadingBook.add(book);
    }

    public boolean removeFromAlreadyReadBooks(Book book){
        return alreadyReadBooks.remove(book);
    }
    public boolean removeFromWishList(Book book){
        return wantToReadBooks.remove(book);
    }
    public boolean removeFromCureentRead(Book book){
        return currentlyReadingBook.remove(book);
    }
    public boolean removeFromFavBooks(Book book){
        return favoriteBooks.remove(book);
    }
}
