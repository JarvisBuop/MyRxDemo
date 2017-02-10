package com.zjy.myrxdemo.data.source;

import com.zjy.myrxdemo.data.model.BaseResponse;
import com.zjy.myrxdemo.data.model.login.ShopInfo;
import com.zjy.myrxdemo.data.model.login.User;
import com.zjy.myrxdemo.data.model.login.bean.LoginResponse;
import com.zjy.myrxdemo.data.model.login.bean.PayConfigModel;
import com.zjy.myrxdemo.data.model.login.bean.UnionConfigModel;

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
    public Observable<Boolean> saveSessionId(String sessionId) {
        return mLocalDataSource.saveSessionId(sessionId);
    }

    @Override
    public Observable<String> getSessionId() {
        return mLocalDataSource.getSessionId();
    }

    @Override
    public Observable<User> getUser() {
        return mLocalDataSource.getUser();
    }

    @Override
    public Observable<Boolean> saveUser(User user) {
        return mLocalDataSource.saveUser(user);
    }

    @Override
    public Observable<ShopInfo> getShopInfo() {
        return mLocalDataSource.getShopInfo();
    }

    @Override
    public Observable<Boolean> saveShopInfo(ShopInfo shopInfo) {
        return mLocalDataSource.saveShopInfo(shopInfo);
    }

    @Override
    public Observable<LoginResponse> login(String UserName, String password, String deviceId, String V, String AP, String apiVersion) {
        return mRemoteDataSource.login(UserName,password,deviceId,V,AP,apiVersion);
    }

    @Override
    public Observable<BaseResponse<PayConfigModel>> getPayConfig(String token, String apiVersion) {
        return mRemoteDataSource.getPayConfig(token,apiVersion);
    }

    @Override
    public Observable<BaseResponse<UnionConfigModel>> getUnionConfig(String token, String apiVersion) {
        return mRemoteDataSource.getUnionConfig(token,apiVersion);
    }
}
