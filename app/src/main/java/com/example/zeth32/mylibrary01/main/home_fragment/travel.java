package com.example.zeth32.mylibrary01.main.home_fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zeth32.mylibrary01.R;
import com.example.zeth32.mylibrary01.main.home_fragment.adapter.travelRecycleAdapter;

/**
 * Created by Zeth32 on 25/05/2017.
 */

public class travel extends Fragment {
    RecyclerView mRecycleView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;

    public travel() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.home_travel_fragment, container, false);

        mRecycleView = (RecyclerView) view.findViewById(R.id.travelRecycleView);
        mRecycleView.setHasFixedSize(true);

        mLayoutManager = new GridLayoutManager(getActivity(),1);
        mRecycleView.setLayoutManager(mLayoutManager);

        mAdapter = new travelRecycleAdapter();
        mRecycleView.setAdapter(mAdapter);

        return view;
    }
}