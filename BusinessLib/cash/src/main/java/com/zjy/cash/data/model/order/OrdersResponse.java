package com.zjy.cash.data.model.order;

import com.zjy.baselib.data.model.NetWorkResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with android studio
 * Creator :zhoujunyou
 * Date:2017/2/25
 * Time:16:51
 * Desc:
 */

public class OrdersResponse extends NetWorkResponse {
    public int nextPage = 0;
    public int ordertype = 0;
    public List<PayOrder> orders = new ArrayList<>();

    public OrdersResponse() {

    }
}
