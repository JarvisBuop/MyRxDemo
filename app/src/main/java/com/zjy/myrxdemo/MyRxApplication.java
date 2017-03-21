package com.zjy.myrxdemo;

import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.utils.Utils;
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
        //Bugly.init(getApplicationContext(),getString(R.string.meta_bugly_id), BuildConfig.DEBUG);

        if(BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
            Config.setENV(Environment.DEV);
            ARouter.openDebug();
            ARouter.openLog();
        }
        ARouter.init(this);
    }

}
