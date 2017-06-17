package com.example.zeth32.mylibrary01.main.loginned_view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.zeth32.mylibrary01.R;
/**
 * Created by Zeth32 on 25/05/2017.
 */

public class notification extends Fragment {

    public static notification newInstance() {
        notification fragment = new notification();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.notification_activity, container, false);
    }
}