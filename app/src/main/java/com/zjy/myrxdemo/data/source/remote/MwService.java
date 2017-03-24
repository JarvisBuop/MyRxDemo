package com.zjy.myrxdemo.data.source.remote;

import com.zjy.baselib.data.model.NetWorkResponse;
import com.zjy.baselib.framework.HttpConstants;
import com.zjy.myrxdemo.data.model.login.bean.AdvModel;
import com.zjy.myrxdemo.data.model.login.bean.ConfigQRModel;
import com.zjy.myrxdemo.data.model.login.bean.LoginResponse;
import com.zjy.myrxdemo.data.model.login.bean.PayConfigModel;
import com.zjy.myrxdemo.data.model.login.bean.UnionConfigModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MwService {
    String HOST = HttpConstants.getPDUrlRoot();
    @GET("shop/login.php")
    Observable<LoginResponse> getLoginResponse(@Query("UserName")String UserName,
                                               @Query("Password")String password,
                                               @Query("DeviceID")String deviceId,
                                               @Query("V")String V,
                                               @Query("AP")String AP,
                                               @Query("apiVersion")String apiVersion);
    @FormUrlEncoded
    @POST("b/api/config.getpayconfig")
    Observable<NetWorkResponse<PayConfigModel>> getPayConfigResponse(@Field("token") String token,
                                                                     @Field("apiVersion") String apiVersion);
    @FormUrlEncoded
    @POST("b/api/config.getunionshopconfig")
    Observable<NetWorkResponse<UnionConfigModel>> getUnionConfigResponse(@Field("token") String token,
                                                                         @Field("apiVersion") String apiVersion);
    @FormUrlEncoded
    @POST("b/api/softconfig.startupimage")
    Observable<NetWorkResponse<AdvModel>> getAdvResopnse(@Field("token")String token,
                                                         @Field("businessId")int businessId,
                                                         @Field("dimension")String dimension,
                                                         @Field("apiVersion")String apiVersion);

    @FormUrlEncoded
    @POST("b/api/config.qr")
    Observable<NetWorkResponse<List<ConfigQRModel>>> getQRResponse(@Field("token")String token,
                                                                   @Field("apiVersion")String apiVersion);

}
