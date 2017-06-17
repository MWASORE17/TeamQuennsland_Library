package com.example.zeth32.mylibrary01.main.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zeth32 on 04/06/2017.
 */

public class TemporaryUser{
    private int id;
    private String email;
    private String password;
    private List<String> favCategory;

    public static int _id = 1;

    public static ArrayList<TemporaryUser> temporaryUsers = new ArrayList<>();


    public TemporaryUser() {}

    public TemporaryUser(String email, String password) {
        this.id = _id;
        this.email = email;
        this.password = password;
        _id++;
    }
    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getFavCategory() {
        return favCategory;
    }
    public void setFavCategory(List<String> favCategory) {
        this.favCategory = favCategory;
    }


}
