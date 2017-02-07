package com.zjy.myrxdemo;

import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import timber.log.Timber;

public class MyRxApplication extends MultiDexApplication {
    private static MyRxApplication INSTANCE;
    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE=this;
        MultiDex.install(this);
        if(BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
        }
    }

    public static MyRxApplication getInstance(){
        return INSTANCE;
    }
}
