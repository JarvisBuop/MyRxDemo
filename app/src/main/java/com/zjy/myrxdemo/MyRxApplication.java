package com.zjy.myrxdemo;

import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import timber.log.Timber;

public class MyRxApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        if(BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
        }

    }
}
