package com.example.zeth32.mylibrary01.main;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.example.zeth32.mylibrary01.R;
import com.example.zeth32.mylibrary01.main.entity.Book;
import com.example.zeth32.mylibrary01.main.loginned_view.books;
import com.example.zeth32.mylibrary01.main.loginned_view.home;
import com.example.zeth32.mylibrary01.main.loginned_view.notification;
import com.example.zeth32.mylibrary01.main.loginned_view.profile;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class Logined extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logined);
        String i = "1";
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navBot);

        // Tampilan Pertama
        Fragment firstSelectedFragment = home.newInstance();
        FragmentTransaction firstTransaction = getSupportFragmentManager().beginTransaction();
        firstTransaction.replace(R.id.container, firstSelectedFragment);
        firstTransaction.commit();

        //Listener pergantian Halaman
        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.action_home:
                                // set tampilan Home
                                selectedFragment = home.newInstance();
                                break;
                            case R.id.action_Books:
                                selectedFragment = books.newInstance();
                                break;
//                            case R.id.action_Notification:
//                                selectedFragment = notification.newInstance();
//                                break;
                            case R.id.action_Profile:
                                selectedFragment = profile.newInstance();
                                break;
                            default:
                                selectedFragment = home.newInstance();
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.container, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });
    }
}
