package com.example.zeth32.mylibrary01.main;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

import com.example.zeth32.mylibrary01.R;
import com.example.zeth32.mylibrary01.main.entity.PinjamBuku;
import com.example.zeth32.mylibrary01.main.home_fragment.customClass;
import com.example.zeth32.mylibrary01.main.session.LoginSession;

/**
 * Created by Zeth32 on 11/06/2017.
 */

public class alarmNotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (LoginSession.with(context).isuserlogin() == true) {
            for (int i = 0; i < PinjamBuku.pinjamBukuList.size(); i++) {
                if (PinjamBuku.pinjamBukuList.get(i).getEmail().toLowerCase().equals(customClass.email)) {
                    if (PinjamBuku.pinjamBukuList.get(i).getStatus().toLowerCase().equals("pinjam")) {
                        if (PinjamBuku.pinjamBukuList.get(i).hitungDenda() > 0) {

                            // Send Notif
                            NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
                            builder.setAutoCancel(true)
                                    .setDefaults(Notification.DEFAULT_ALL)
                                    .setWhen(System.currentTimeMillis())
                                    .setSmallIcon(R.mipmap.ic_launcher)
                                    .setContentTitle("Check Your Book!")
                                    .setContentText("Ada Buku Yang Belum dikembalikan")
                                    .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND)
                                    .setContentInfo("Pinjam Buku");
                            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                            notificationManager.notify(1, builder.build());
                            break;
                        }
                    }
                }
            }
        }

    }
}
