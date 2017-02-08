package com.zjy.myrxdemo.data.source;

import com.zjy.myrxdemo.data.model.User;

import rx.Observable;

public class Repository implements DataSource {
    private static Repository INSTANCE = null;
    private final DataSource mLocalDataSource;
    private final DataSource mRemoteDataSource;

    public static Repository getInstance(DataSource localDataSource,DataSource remoteDataSource) {
        synchronized (Repository.class) {
            if (INSTANCE == null) {
                INSTANCE = new Repository(localDataSource,remoteDataSource);
            }
        }
        return INSTANCE;
    }

    private Repository(DataSource localDataSource, DataSource remoteDataSource) {
        mLocalDataSource = localDataSource;
        mRemoteDataSource = remoteDataSource;
    }




    @Override
    public Observable<User> getUser() {
        return mLocalDataSource.getUser();
    }

    @Override
    public Observable<Boolean> saveUser(User user) {
        return mLocalDataSource.saveUser(user);
    }
}
