package com.example.zeth32.mylibrary01.main.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zeth32 on 04/06/2017.
 */

public class User {
    private String email;
    private String password;
    private String gender;
    private String name;
    private String alamat;
    private String phoneNumber;
    private List<String> favCategory;

    public static int _id = 1;

    public static ArrayList<User> users = new ArrayList<>();

    public User() {}

    public User(String name, String email, String password, String gender, String alamat, String phoneNumber,List<String> favCategory) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.alamat = alamat;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.favCategory = favCategory;
        _id++;
    }

    public int getId() {
        return _id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


    public List<String> getFavCategory() {
        return favCategory;
    }
    public void setFavCategory(List<String> favCategory) {
        this.favCategory = favCategory;
    }


    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getAlamat(){ return alamat; }
    public void setAlamat(String alamat){ this.alamat = alamat; }

    public String getGender(){ return gender; }
    public void setGender(String gender){ this.gender = gender; }

    public String getPhoneNumber(){ return phoneNumber; }
    public void setPhoneNumber(String phoneNumber){ this.phoneNumber = phoneNumber; }
}
