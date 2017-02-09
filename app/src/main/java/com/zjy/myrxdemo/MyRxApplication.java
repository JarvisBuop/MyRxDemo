package com.zjy.myrxdemo;

import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.blankj.utilcode.utils.Utils;
import com.zjy.myrxdemo.framework.Config;
import com.zjy.myrxdemo.framework.Environment;

import timber.log.Timber;

public class MyRxApplication extends MultiDexApplication {
    private static MyRxApplication INSTANCE;
    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE=this;
        MultiDex.install(this);
        Utils.init(this);
        if(BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
            Config.setENV(Environment.DEV);
        }
    }

    public static MyRxApplication getInstance(){
        return INSTANCE;
    }
}
