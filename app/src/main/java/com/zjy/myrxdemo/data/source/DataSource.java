package com.zjy.myrxdemo.data.source;

import com.zjy.baselib.data.model.NetWorkResponse;
import com.zjy.baselib.data.model.bean.SessionModel;
import com.zjy.baselib.data.model.bean.User;
import com.zjy.myrxdemo.data.model.login.ShopInfo;
import com.zjy.myrxdemo.data.model.login.bean.AdvModel;
import com.zjy.myrxdemo.data.model.login.bean.ConfigQRModel;
import com.zjy.myrxdemo.data.model.login.bean.LoginResponse;
import com.zjy.myrxdemo.data.model.login.bean.PayConfigModel;
import com.zjy.myrxdemo.data.model.login.bean.UnionConfigModel;

import java.util.List;

import io.reactivex.Observable;


public interface DataSource {
    void saveSessionId(String sessionId);
    String getSessionId();
    Observable<User> getUser();
    Observable<Boolean> saveUser(User user);
    Observable<ShopInfo> getShopInfo();
    Observable<Boolean> saveShopInfo(ShopInfo shopInfo);
    Observable<LoginResponse> login(String UserName,String password,String deviceId,String V,String AP,String apiVersion);
    Observable<NetWorkResponse<PayConfigModel>> getPayConfig(String token, String apiVersion);
    Observable<NetWorkResponse<UnionConfigModel>> getUnionConfig(String token, String apiVersion);
    Observable<NetWorkResponse<AdvModel>> getAdvUrl(String token,int businessId,String dimension,String apiVersion);
    Observable<NetWorkResponse<List<ConfigQRModel>>> getConfigQR(String token,String apiVersion);
    Observable<NetWorkResponse<SessionModel>> refreshSessionId(String UserName,String Password,String DeviceID);
}
