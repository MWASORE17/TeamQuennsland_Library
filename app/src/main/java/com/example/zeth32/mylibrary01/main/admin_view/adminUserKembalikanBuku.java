package com.example.zeth32.mylibrary01.main.admin_view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.zeth32.mylibrary01.R;
import com.example.zeth32.mylibrary01.main.admin_view.adapter.kembalikanBukuRecycleAdapter;

/**
 * Created by Zeth32 on 11/06/2017.
 */

public class adminUserKembalikanBuku  extends Fragment {
    RecyclerView mRecycleView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;

    public static adminUserKembalikanBuku newInstance() {
        adminUserKembalikanBuku fragment = new adminUserKembalikanBuku();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View _view = inflater.inflate(R.layout.admin_user_kembalikan_buku, container, false);
        init(_view);
        event();

        mRecycleView = (RecyclerView) _view.findViewById(R.id.kembalikanBukuRecycleView);
        mRecycleView.setHasFixedSize(true);

        mLayoutManager = new GridLayoutManager(getActivity(),1);
        mRecycleView.setLayoutManager(mLayoutManager);

        mAdapter = new kembalikanBukuRecycleAdapter();
        mRecycleView.setAdapter(mAdapter);

        return _view;
    }

    private Button back;
    public void init(View view){
        back = (Button) view.findViewById(R.id.backAdminUserKembalikanBuku);
    }
    public void event(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment firstSelectedFragment = adminHomePage.newInstance();
                FragmentTransaction firstTransaction = getFragmentManager().beginTransaction();
                firstTransaction.replace(R.id.adminFramePage, firstSelectedFragment);
                firstTransaction.commit();
            }
        });
    }
}
