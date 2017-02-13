package com.zjy.myrxdemo.data.source.remote;

import com.zjy.baselib.data.model.NetWorkResponse;
import com.zjy.baselib.data.model.bean.SessionModel;
import com.zjy.baselib.data.model.bean.User;
import com.zjy.baselib.data.source.remote.RetrofitHelper;
import com.zjy.myrxdemo.data.model.login.ShopInfo;
import com.zjy.myrxdemo.data.model.login.bean.AdvModel;
import com.zjy.myrxdemo.data.model.login.bean.ConfigQRModel;
import com.zjy.myrxdemo.data.model.login.bean.LoginResponse;
import com.zjy.myrxdemo.data.model.login.bean.PayConfigModel;
import com.zjy.myrxdemo.data.model.login.bean.UnionConfigModel;
import com.zjy.myrxdemo.data.source.DataSource;

import java.util.List;

import rx.Observable;

public class RemoteDataSource implements DataSource {
    private static RemoteDataSource instance = null;
    protected final MwService mMwService;
    public static RemoteDataSource getInstance() {
        synchronized (RemoteDataSource.class) {
            if (instance == null) {
                instance = new RemoteDataSource();
            }
        }
        return instance;
    }

    private RemoteDataSource(){
        mMwService = RetrofitHelper.getInstance().getApiService (MwService.HOST,MwService.class,null);
    }



    ///////////////////////////////////////////////////////////////////////////
    //        remote use
    ///////////////////////////////////////////////////////////////////////////

    @Override
    public Observable<LoginResponse> login(String UserName, String password, String deviceId, String V, String AP, String apiVersion) {
        return mMwService.getLoginResponse(UserName,password,deviceId,V,AP,apiVersion);
    }

    @Override
    public Observable<NetWorkResponse<PayConfigModel>> getPayConfig(String token, String apiVersion) {
        return mMwService.getPayConfigResponse(token,apiVersion);
    }

    @Override
    public Observable<NetWorkResponse<UnionConfigModel>> getUnionConfig(String token, String apiVersion) {
        return mMwService.getUnionConfigResponse(token,apiVersion);
    }

    @Override
    public Observable<NetWorkResponse<AdvModel>> getAdvUrl(String token, int businessId, String dimension, String apiVersion) {
        return mMwService.getAdvResopnse(token,businessId,dimension,apiVersion);
    }

    @Override
    public Observable<NetWorkResponse<List<ConfigQRModel>>> getConfigQR(String token, String apiVersion) {
        return mMwService.getQRResponse(token,apiVersion);
    }

    @Override
    public Observable<NetWorkResponse<SessionModel>> refreshSessionId(String UserName, String Password, String DeviceID) {
        return null;
    }

    ///////////////////////////////////////////////////////////////////////////
    //                 local use
    ///////////////////////////////////////////////////////////////////////////
    @Override
    public Observable<ShopInfo> getShopInfo() {
        return null;
    }

    @Override
    public Observable<Boolean> saveShopInfo(ShopInfo shopInfo) {
        return null;
    }


    @Override
    public void saveSessionId(String sessionId) {

    }

    @Override
    public String getSessionId() {
        return null;
    }

    @Override
    public Observable<User> getUser() {
        return null;
    }

    @Override
    public Observable<Boolean> saveUser(User user) {
        return null;
    }

}
