package com.zjy.myrxdemo.data.source.remote;

import com.zjy.myrxdemo.data.model.login.LoginResponse;
import com.zjy.myrxdemo.framework.ConfigConstants;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface MwService {
    String HOST = ConfigConstants.getPDUrlRoot();
    @GET("shop/login.php")
    Observable<LoginResponse> getLoginResponse(@Query("UserName")String UserName,
                                               @Query("Password")String password,
                                               @Query("DeviceID")String deviceId,
                                               @Query("V")String V,
                                               @Query("AP")String AP,
                                               @Query("apiVersion")String apiVersion);
}
