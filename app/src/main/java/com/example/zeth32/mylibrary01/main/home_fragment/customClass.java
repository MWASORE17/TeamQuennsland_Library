package com.example.zeth32.mylibrary01.main.home_fragment;

import com.example.zeth32.mylibrary01.main.entity.Book;

import java.util.List;

/**
 * Created by Zeth32 on 05/06/2017.
 */

public class customClass {
    public customClass(){};
    public static int indexDeskripsiBuku = 0;
    public static Book detailBuku;

    public void setDeskripsiBuku(int indexDeskripsiBuku){
        this.indexDeskripsiBuku = indexDeskripsiBuku;
    }
    public int getIndexDeskripsiBuku(){return indexDeskripsiBuku;}

    public static List<String> favCategory;
    public static String email;
}
