package com.zjy.myrxdemo.component;

import com.zjy.myrxdemo.data.source.Repository;
import com.zjy.myrxdemo.data.source.local.LocalDataSource;
import com.zjy.myrxdemo.data.source.remote.RemoteDataSource;

public class AppInjection {
    public static Repository provideRepository(){
        return Repository.getInstance(LocalDataSource.getInstance(), RemoteDataSource.getInstance());
    }

}
