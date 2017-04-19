package com.zjy.myrxdemo.data.source;

import android.graphics.Bitmap;

import com.zjy.baselib.component.rx.ApiErrorOperator;
import com.zjy.baselib.data.model.NetWorkResponse;
import com.zjy.baselib.data.model.bean.SessionModel;
import com.zjy.baselib.data.model.bean.User;
import com.zjy.myrxdemo.data.model.login.ShopInfo;
import com.zjy.myrxdemo.data.model.login.bean.ConfigQRModel;
import com.zjy.myrxdemo.data.model.login.bean.LoginResponse;
import com.zjy.myrxdemo.data.model.login.bean.PayConfigModel;
import com.zjy.myrxdemo.data.model.login.bean.UnionConfigModel;
import com.zjy.zlibrary.rx.transform.Transformers;

import java.util.List;

import io.reactivex.Observable;


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
    public void saveSessionId(String sessionId) {
        mLocalDataSource.saveSessionId(sessionId);
    }

    @Override
    public String getSessionId() {
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
        return mRemoteDataSource.login(UserName,password,deviceId,V,AP,apiVersion)
                .lift(new ApiErrorOperator<>());
    }

    @Override
    public Observable<NetWorkResponse<PayConfigModel>> getPayConfig(String token, String apiVersion) {
        return mRemoteDataSource.getPayConfig(token,apiVersion)
                .lift(new ApiErrorOperator<>());

    }

    @Override
    public Observable<NetWorkResponse<UnionConfigModel>> getUnionConfig(String token, String apiVersion) {
        return mRemoteDataSource.getUnionConfig(token,apiVersion)
                .lift(new ApiErrorOperator<>())
                .compose(Transformers.neverError());
    }

    @Override
    public Observable<Bitmap> getAdvBitmap(String token, int businessId, String dimension, String apiVersion) {
        return mRemoteDataSource.getAdvBitmap(token,businessId,dimension,apiVersion)
                .compose(Transformers.neverError());
    }

    @Override
    public Observable<NetWorkResponse<List<ConfigQRModel>>> getConfigQR(String token, String apiVersion) {
        return mRemoteDataSource.getConfigQR(token,apiVersion)
                .lift(new ApiErrorOperator<>())
                .compose(Transformers.neverError());
    }

    @Override
    public Observable<NetWorkResponse<SessionModel>> refreshSessionId(String UserName, String Password, String DeviceID) {
        return mRemoteDataSource.refreshSessionId(UserName,Password,DeviceID);

    }
}
