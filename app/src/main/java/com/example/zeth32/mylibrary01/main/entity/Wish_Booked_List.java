package com.example.zeth32.mylibrary01.main.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zeth32 on 10/06/2017.
 */

public class Wish_Booked_List {
    private String email;
    private String barcode;
    public static int _id = 1;

    public static ArrayList<Wish_Booked_List> wishBookedList = new ArrayList<>();

    public Wish_Booked_List() {
    }

    public Wish_Booked_List(String email, String barcode) {
        this.email = email;
        this.barcode = barcode;
        _id++;
    }

    public int getId() {
        return _id;
    }

    public String getEmail() { return email; }
    public  void setEmail(String email) {this.email = email;}

    public String getBarcode(){return barcode;}
    public void setBarcode(String barcode){this.barcode = barcode;}
}
