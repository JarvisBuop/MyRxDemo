package com.zjy.cash.data.source.remote;

import com.zjy.baselib.data.source.remote.RetrofitHelper;
import com.zjy.cash.data.model.order.OrdersResponse;
import com.zjy.cash.data.source.CashDataSource;

import rx.Observable;

/**
 * Created with android studio
 * Creator :zhoujunyou
 * Date:2017/2/25
 * Time:16:21
 * Desc:
 */

public class CashRemoteDataSource implements CashDataSource{
    private static CashRemoteDataSource instance = null;
    protected final CashOrdersService mCashService ;
    public static CashRemoteDataSource getInstance() {
        synchronized (CashRemoteDataSource.class) {
            if (instance == null) {
                instance = new CashRemoteDataSource();
            }
        }
        return instance;
    }

    private CashRemoteDataSource(){
        mCashService = RetrofitHelper.getInstance().getApiService (CashOrdersService.HOST,CashOrdersService.class,null);
    }



    ///////////////////////////////////////////////////////////////////////////
//
///////////////////////////////////////////////////////////////////////////

    @Override
    public Observable<OrdersResponse> getOrders(String token, int type, String all, int page, String mobile, String apiVersion) {
        return null;
    }

    @Override
    public String getSessionId() {
        return null;
    }
}
