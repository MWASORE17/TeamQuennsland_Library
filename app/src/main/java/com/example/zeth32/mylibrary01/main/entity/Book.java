package com.example.zeth32.mylibrary01.main.entity;

import java.util.ArrayList;

/**
 * Created by Zeth32 on 04/06/2017.
 */

public class Book {
    public static int _id = 1;
    private String nama;
    private int stock;
    private String deskripsi;
    private String author;
    private String genre;
    private int rating;
    private String barcode;

    public static ArrayList<Book> books = new ArrayList<>();

    public Book(){
        super();
    }

    public Book(String barcode, String nama, int stock, String deskripsi, String author, String genre, int rating){
        this.barcode = barcode;
        this.nama = nama;
        this.stock = stock;
        this.deskripsi = deskripsi;
        this.author = author;
        this.genre = genre;
        this.rating = rating;
        _id++;
    }

    public int getId() {
        return _id;
    }

    public String getNama(){return nama;}
    public void setNama(String nama){this.nama = nama;}

    public int getStock(){return stock;}
    public void setStock(int stock){this.stock = stock;}

    public String getDeskripsi(){return deskripsi;}
    public void setDeskripsi(String deskripsi){this.deskripsi=deskripsi;}

    public String getAuthor(){return author;}
    public void setAuthor(String author){this.author=author;}

    public String getGenre(){return genre;}
    public void setGenre(String genre){this.genre=genre;}

    public int getRating(){return rating;}
    public void setRating(int rating){this.rating=rating;}

    public void setBarcode(String barcode){this.barcode = barcode;}
    public String getBarcode(){return barcode;}
}
