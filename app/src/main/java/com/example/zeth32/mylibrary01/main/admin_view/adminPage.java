package com.example.zeth32.mylibrary01.main.admin_view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.example.zeth32.mylibrary01.R;
import com.example.zeth32.mylibrary01.main.Logined;
import com.example.zeth32.mylibrary01.main.entity.User;
import com.example.zeth32.mylibrary01.main.home_fragment.customClass;
import com.example.zeth32.mylibrary01.main.session.LoginSession;
import com.example.zeth32.mylibrary01.main.startapp_view.Login;

/**
 * Created by Zeth32 on 11/06/2017.
 */

public class adminPage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_page);
        // Tampilan Pertama
        Fragment firstSelectedFragment = adminHomePage.newInstance();
        FragmentTransaction firstTransaction = getSupportFragmentManager().beginTransaction();
        firstTransaction.replace(R.id.adminFramePage, firstSelectedFragment);
        firstTransaction.commit();
    }
}