package com.zjy.cash.data.source;

import com.zjy.baselib.data.model.NetWorkResponse;
import com.zjy.cash.data.model.order.OrdersResponse;
import com.zjy.cash.data.model.order.PayOrder;

import java.util.ArrayList;

import rx.Observable;


/**
 * Created with android studio
 * Creator :zhoujunyou
 * Date:2017/2/25
 * Time:16:25
 * Desc:
 */

public interface CashDataSource {
    Observable<OrdersResponse> getOrders();
}
