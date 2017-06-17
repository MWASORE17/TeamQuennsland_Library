package com.example.zeth32.mylibrary01.main;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.zeth32.mylibrary01.R;
import com.example.zeth32.mylibrary01.main.entity.Book;
import com.example.zeth32.mylibrary01.main.entity.TemporaryUser;
import com.example.zeth32.mylibrary01.main.entity.User;
import com.example.zeth32.mylibrary01.main.home_fragment.customClass;
import com.example.zeth32.mylibrary01.main.session.LoginSession;
import com.example.zeth32.mylibrary01.main.startapp_view.Login;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Zeth32 on 02/06/2017.
 */

public class StartApp extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_app);
        if (LoginSession.with(getApplicationContext()).isuserlogin() == true) {
            // Tampilan Pertama
//            setAlarm();
            Intent intent = new Intent(this, Logined.class);
            startActivity(intent);
            User _User = LoginSession.with(this).getuserloggedin();
            customClass.favCategory = _User.getFavCategory();
            customClass.email = _User.getEmail();
            Toast.makeText(this, "Welcome "+_User.getName(),Toast.LENGTH_SHORT).show();
            this.finish();
        }
        else{
            // Tampilan Pertama
            Fragment firstSelectedFragment = Login.newInstance();
            FragmentTransaction firstTransaction = getSupportFragmentManager().beginTransaction();
            firstTransaction.replace(R.id.frameLoginRegister, firstSelectedFragment);
            firstTransaction.commit();
        }

    }
//    private void setAlarm(){
//        AlarmManager manager = (AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
//        Intent myIntent;
//        PendingIntent pendingIntent;
//        myIntent = new Intent(this,alarmNotificationReceiver.class);
//        pendingIntent = PendingIntent.getBroadcast(this,0,myIntent,0);
//
//        // Set Waktu
//        Calendar time = Calendar.getInstance();
//        time.setTimeInMillis(System.currentTimeMillis());
//
//        time.set(Calendar.HOUR_OF_DAY, 12);
//        time.set(Calendar.MINUTE, 0);
//        time.set(Calendar.SECOND, 0);
//
//        manager.setRepeating(AlarmManager.RTC_WAKEUP, time.getTimeInMillis(),6*1000,pendingIntent);
//    }
}
