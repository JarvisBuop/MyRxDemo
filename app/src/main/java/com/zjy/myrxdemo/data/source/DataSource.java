package com.zjy.myrxdemo.data.source;

import com.zjy.myrxdemo.data.model.BaseResponse;
import com.zjy.myrxdemo.data.model.login.ShopInfo;
import com.zjy.myrxdemo.data.model.login.User;
import com.zjy.myrxdemo.data.model.login.bean.LoginResponse;
import com.zjy.myrxdemo.data.model.login.bean.PayConfigModel;
import com.zjy.myrxdemo.data.model.login.bean.UnionConfigModel;

import rx.Observable;

public interface DataSource {
    Observable<Boolean> saveSessionId(String sessionId);
    Observable<String> getSessionId();
    Observable<User> getUser();
    Observable<Boolean> saveUser(User user);
    Observable<ShopInfo> getShopInfo();
    Observable<Boolean> saveShopInfo(ShopInfo shopInfo);
    Observable<LoginResponse> login(String UserName,String password,String deviceId,String V,String AP,String apiVersion);
    Observable<BaseResponse<PayConfigModel>> getPayConfig(String token, String apiVersion);
    Observable<BaseResponse<UnionConfigModel>> getUnionConfig(String token, String apiVersion);
}
