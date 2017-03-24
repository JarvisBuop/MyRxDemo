package com.zjy.cash.data.source;

import com.zjy.cash.data.model.order.OrdersResponse;

import io.reactivex.Observable;


/**
 * Created with android studio
 * Creator :zhoujunyou
 * Date:2017/2/25
 * Time:16:25
 * Desc:
 */

public interface CashDataSource {
    Observable<OrdersResponse> getOrders(String token, int type, String all, int page, String mobile, String apiVersion);
    String getSessionId();
}
