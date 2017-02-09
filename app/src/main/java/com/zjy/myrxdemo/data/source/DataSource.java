package com.zjy.myrxdemo.data.source;

import com.zjy.myrxdemo.data.model.login.LoginResponse;
import com.zjy.myrxdemo.data.model.login.User;

import rx.Observable;

public interface DataSource {
    Observable<User> getUser();
    Observable<Boolean> saveUser(User user);
    Observable<LoginResponse> login(String UserName,String password,String deviceId,String V,String AP,String apiVersion);
}
