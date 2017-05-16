package com.zjy.myrxdemo;

import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.utils.Utils;
import com.facebook.stetho.Stetho;
import com.zjy.baselib.component.Injection.Injection;
import com.zjy.baselib.framework.Config;
import com.zjy.baselib.framework.Environment;

import timber.log.Timber;

public class MyRxApplication extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        Utils.init(this);
        Injection.init(this);



        if(BuildConfig.DEBUG){
           // Bugly.init(getApplicationContext(),getString(R.string.meta_bugly_id), BuildConfig.DEBUG);
            Timber.plant(new Timber.DebugTree());
            Config.setENV(Environment.DEV);
            ARouter.openDebug();
            ARouter.openLog();
            Stetho.initializeWithDefaults(this);//数据库调试
        }
        ARouter.init(this);
    }

}
