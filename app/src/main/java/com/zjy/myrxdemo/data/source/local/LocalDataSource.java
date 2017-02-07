package com.zjy.myrxdemo.data.source.local;

import com.zjy.myrxdemo.data.model.User;
import com.zjy.myrxdemo.data.source.DataSource;

import rx.Observable;

public class LocalDataSource implements DataSource {
    private static LocalDataSource instance = null;

    private LocalDataSource(){
    }

    public static LocalDataSource getInstance() {
        synchronized (LocalDataSource.class) {
            if (instance == null) {
                instance = new LocalDataSource();
            }
        }
        return instance;
    }
    @Override
    public Observable<User> getUser() {
        return null;
    }
}
