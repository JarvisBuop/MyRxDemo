package com.zjy.cash.data.source.remote;

import com.zjy.baselib.framework.HttpConstants;
import com.zjy.cash.data.model.order.OrdersResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created with android studio
 * Creator :zhoujunyou
 * Date:2017/2/25
 * Time:16:20
 * Desc:
 */

public interface CashOrdersService {
    String HOST = HttpConstants.getPDUrlRoot();

    @FormUrlEncoded
    @POST("b/api/order.get")
    Observable<OrdersResponse> getOrders(@Field("token") String token,
                                         @Field("type")int type,
                                         @Field("all")String all,
                                         @Field("page")int page,
                                         @Field("mobile")String mobile,
                                         @Field("apiVersion")String apiVersion);
}
