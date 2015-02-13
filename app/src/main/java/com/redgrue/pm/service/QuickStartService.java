package com.redgrue.pm.service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.redgrue.pm.R;

/**
 * Created by rouge on 02.02.2015.
 */
public class QuickStartService extends Service {
    private static final String Log_TAG = QuickStartService.class.getSimpleName();

    public static final String EXTRA_KEY_COMMAND = "EXTRA_KEY_COMMAND";

    public static final int COMMAND_MAKE_FOREGROUND = 1;
    public static final int COMMAND_UNMAKE_FOREGROUND = 2;

    public static final int ID = 1337;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(final Intent intent, final int flags, final int startId) {
        Log.v(Log_TAG, "onStartCommand");
        if (intent != null && intent.getExtras() != null
                && intent.getExtras().containsKey(EXTRA_KEY_COMMAND)) {
            final int command = intent.getIntExtra(EXTRA_KEY_COMMAND, 0);
            switch (command) {
                case COMMAND_MAKE_FOREGROUND:
                    makeForeground();
                    break;
                case COMMAND_UNMAKE_FOREGROUND:
                    stopForeground(false);
                    break;
                default:
                    Log.w(Log_TAG, "Command not found!");
            }
        }

        return super.onStartCommand(intent, flags, startId);

    }

    private void makeForeground() {
        final Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.pust_lettre_m)
                .setContentTitle("Foreground service")
                .setContentText("Testing")
                .build();

        startForeground(ID, notification);
    }
}
