package com.example.zeth32.mylibrary01.main.admin_view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zeth32.mylibrary01.R;
import com.example.zeth32.mylibrary01.main.StartApp;

/**
 * Created by Zeth32 on 11/06/2017.
 */

public class adminHomePage  extends Fragment {

    public static adminHomePage newInstance() {
        adminHomePage fragment = new adminHomePage();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View _view = inflater.inflate(R.layout.admin_homepage, container, false);
        init(_view);
        event();
        return _view;
    }

    private Button btnAdminNewPinjam;
    private Button btnAdminConfirmBook;
    private Button btnAdminConfirmKembalikan;
    private Button btnAdminLogOut;

    private void init(View v) {
        btnAdminNewPinjam = (Button) v.findViewById(R.id.btnAdminNewPinjam);
        btnAdminConfirmBook = (Button) v.findViewById(R.id.btnAdminConfirmBook);
        btnAdminConfirmKembalikan = (Button) v.findViewById(R.id.btnAdminConfimKembalikan);
        btnAdminLogOut = (Button) v.findViewById(R.id.btnAdminLogOut);
    }

    private void event() {
        btnAdminLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StartApp.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        btnAdminNewPinjam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment firstSelectedFragment = adminUserPinjamBuku.newInstance();
                FragmentTransaction firstTransaction = getFragmentManager().beginTransaction();
                firstTransaction.replace(R.id.adminFramePage, firstSelectedFragment);
                firstTransaction.commit();
            }
        });
        btnAdminConfirmBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment firstSelectedFragment = adminConfirmBooked.newInstance();
                FragmentTransaction firstTransaction = getFragmentManager().beginTransaction();
                firstTransaction.replace(R.id.adminFramePage, firstSelectedFragment);
                firstTransaction.commit();
            }
        });
        btnAdminConfirmKembalikan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment firstSelectedFragment = adminUserKembalikanBuku.newInstance();
                FragmentTransaction firstTransaction = getFragmentManager().beginTransaction();
                firstTransaction.replace(R.id.adminFramePage, firstSelectedFragment);
                firstTransaction.commit();
            }
        });
    }
}