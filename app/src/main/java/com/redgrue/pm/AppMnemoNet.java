package com.redgrue.pm;

import android.app.Application;

import com.squareup.otto.Bus;

/**
 * Created by rouge on 26.01.2015.
 */
public class AppMnemoNet extends Application {

    private Bus mBus;

    private static AppMnemoNet sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        mBus = new Bus();
    }



    public static AppMnemoNet getInstance() {
        return sInstance;
    }

    public Bus getBus() {
        return mBus;
    }
}

