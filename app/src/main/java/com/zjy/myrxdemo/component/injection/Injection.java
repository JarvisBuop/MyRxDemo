package com.zjy.myrxdemo.component.injection;

import android.content.Context;

import com.zjy.myrxdemo.MyRxApplication;
import com.zjy.myrxdemo.data.source.Repository;
import com.zjy.myrxdemo.data.source.local.LocalDataSource;
import com.zjy.myrxdemo.data.source.remote.RemoteDataSource;

public class Injection {
    public static Repository provideRepository(){
        return Repository.getInstance(LocalDataSource.getInstance(), RemoteDataSource.getInstance());
    }

    public static Context provideContext() {
        return MyRxApplication.getInstance();
    }
}
