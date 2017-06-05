package com.murach.reminder;

import android.app.Service;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by michelle on 04/06/17.
 */

public class ReminderService extends Service {

    private static final String TAG = "NotificationService";
    private static final Integer INTERVAL = 1 * 1000;
    private Timer notificationTimer;
    private String notificationMessage = "Look into the distance. It’s good for your eyes!";

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "Service starting");
        if(notificationTimer != null) {
            notificationTimer.cancel();
        } else {
            notificationTimer = new Timer();
        }
        notificationTimer.schedule(new reminderTask(), INTERVAL, INTERVAL);
        return super.onStartCommand(intent,flags,startId);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "Service stopping");
        notificationTimer.cancel();
    }

    public void sendNotification() {
        Notification.Builder mBuilder =
                new Notification.Builder(this)
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle(notificationMessage)
                        .setContentText(notificationMessage);

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
        mNotificationManager.notify(1, mBuilder.build());
    }

    public class reminderTask extends TimerTask {
        public void run() {
            Log.i(TAG, notificationMessage);
            sendNotification();
        }
    }


}
