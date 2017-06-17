package com.example.zeth32.mylibrary01.main.loginned_view;

import android.app.AlarmManager;
import android.app.FragmentManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zeth32.mylibrary01.R;
import com.example.zeth32.mylibrary01.main.Logined;
import com.example.zeth32.mylibrary01.main.StartApp;
import com.example.zeth32.mylibrary01.main.alarmNotificationReceiver;
import com.example.zeth32.mylibrary01.main.entity.TemporaryUser;
import com.example.zeth32.mylibrary01.main.entity.User;
import com.example.zeth32.mylibrary01.main.home_fragment.customClass;
import com.example.zeth32.mylibrary01.main.session.LoginSession;
import com.example.zeth32.mylibrary01.main.startapp_view.Login;
import com.example.zeth32.mylibrary01.main.startapp_view.Register;
import com.example.zeth32.mylibrary01.main.startapp_view.StartRegisterInfoDialog;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by Zeth32 on 25/05/2017.
 */

public class profile extends Fragment {

    public static profile newInstance() {
        profile fragment = new profile();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View _view = inflater.inflate(R.layout.profile_activity, container, false);
        init(_view);
        User logined = LoginSession.with(getContext()).getuserloggedin();
        setText(logined);
        return _view;
    }

    private TextView emailProfile;
    private TextInputLayout namaProfileContainer;
    private EditText namaProfile;
    private EditText genderProfile;
    private TextInputLayout alamatProfileContainer;
    private EditText alamatProfile;
    private TextInputLayout phoneNumberProfileContainer;
    private EditText phoneNumberProfile;
    private EditText favBookProfile;

    private void init(View view){
        emailProfile = (TextView) view.findViewById(R.id.emailProfile);

        namaProfileContainer = (TextInputLayout) view.findViewById(R.id.namaProfileContainer);
        namaProfile = (EditText) view.findViewById(R.id.namaProfile);
        namaProfile.setKeyListener(null);

        genderProfile= (EditText) view.findViewById(R.id.genderProfile);
        genderProfile.setKeyListener(null);

        alamatProfileContainer = (TextInputLayout) view.findViewById(R.id.alamatProfileContainer);
        alamatProfile = (EditText) view.findViewById(R.id.alamatProfile);
        alamatProfile.setKeyListener(null);

        phoneNumberProfileContainer = (TextInputLayout) view.findViewById(R.id.phoneNumberProfileContainer);
        phoneNumberProfile = (EditText) view.findViewById(R.id.phoneNumberProfile);
        phoneNumberProfile.setKeyListener(null);

        favBookProfile = (EditText) view.findViewById(R.id.favBookProfile);
        favBookProfile.setKeyListener(null);
    }

    private void setText(User logined){
        logined = LoginSession.with(getContext()).getuserloggedin();
        emailProfile.setText(logined.getEmail());
        namaProfile.setText(logined.getName());
        genderProfile.setText(logined.getGender());
        alamatProfile.setText(logined.getAlamat());
        phoneNumberProfile.setText(logined.getPhoneNumber());
        String favBook = "";
        for(int i =0; i<logined.getFavCategory().size(); i++){
            favBook += logined.getFavCategory().get(i);
            if(i != logined.getFavCategory().size()-1) {
                favBook += ", ";
            }
        }
        favBookProfile.setText(favBook);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.profileActivityToolbar);
        toolbar.setTitle("My Profile");
        toolbar.setTitle("Library Online");
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Do something that differs the Activity's menu here
        inflater.inflate(R.menu.menu_profile, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case R.id.logout:
                LoginSession.with(getContext()).clearsession();
                Intent intent = new Intent(getActivity(), StartApp.class);
                startActivity(intent);
                getActivity().finish();
                customClass.email = "kosong";
                //Hilangkan Notifikasi
                AlarmManager am = (AlarmManager) getActivity().getSystemService(ALARM_SERVICE);

                PendingIntent pendingIntent = PendingIntent.getService(
                        getActivity(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                am.cancel(pendingIntent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
