package com.zjy.myrxdemo.data.source.remote;

import com.zjy.myrxdemo.data.model.User;
import com.zjy.myrxdemo.data.source.DataSource;

import rx.Observable;

public class RemoteDataSource implements DataSource {
    private static RemoteDataSource instance = null;

    private RemoteDataSource(){
    }

    public static RemoteDataSource getInstance() {
        synchronized (RemoteDataSource.class) {
            if (instance == null) {
                instance = new RemoteDataSource();
            }
        }
        return instance;
    }
    @Override
    public Observable<User> getUser() {
        return null;
    }
}
