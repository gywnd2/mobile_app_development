package com.example.hyojunglee20172979;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }

    private static final String TAG = "ServiceExample";

    @Override
    public void onCreate() {
        Log.i(TAG, "Service onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "Hyo Jung Lee 20172979 " + startId);

        Runnable runnable = () -> {
            int i = 0;
            while (i < 3) {
                try {
                    i++;
                    Thread.sleep(10000);
                } catch (Exception e) {

                }
                Log.i(TAG, "Service running");
            }
        };
        Thread serviceThread=new Thread(runnable);
        serviceThread.start();
        return Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.i(TAG, "Service onBind");
        return null;
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "Service onDestroy");
    }
}