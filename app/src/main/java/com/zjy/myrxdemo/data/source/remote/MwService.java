package com.zjy.myrxdemo.data.source.remote;

import com.zjy.myrxdemo.data.model.BaseResponse;
import com.zjy.myrxdemo.data.model.login.bean.LoginResponse;
import com.zjy.myrxdemo.data.model.login.bean.PayConfigModel;
import com.zjy.myrxdemo.data.model.login.bean.UnionConfigModel;
import com.zjy.myrxdemo.framework.ConfigConstants;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
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
    @FormUrlEncoded
    @POST("b/api/config.getpayconfig")
    Observable<BaseResponse<PayConfigModel>> getPayConfigResponse(@Field("token") String token,
                                                                  @Field("apiVersion") String apiVersion);
    @FormUrlEncoded
    @POST("b/api/config.getunionshopconfig")
    Observable<BaseResponse<UnionConfigModel>> getUnionConfigResponse(@Field("token") String token,
                                                                      @Field("apiVersion") String apiVersion);

}
