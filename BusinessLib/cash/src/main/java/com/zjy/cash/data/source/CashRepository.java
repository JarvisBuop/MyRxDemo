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
 * Time:16:28
 * Desc:
 */

public class CashRepository implements CashDataSource {
    private static CashRepository INSTANCE = null;
    private final CashDataSource mLocalDataSource;
    private final CashDataSource mRemoteDataSource;

    public static CashRepository getInstance(CashDataSource localDataSource,CashDataSource remoteDataSource) {
        synchronized (CashRepository.class) {
            if (INSTANCE == null) {
                INSTANCE = new CashRepository(localDataSource,remoteDataSource);
            }
        }
        return INSTANCE;
    }

    private CashRepository(CashDataSource localDataSource, CashDataSource remoteDataSource) {
        mLocalDataSource = localDataSource;
        mRemoteDataSource = remoteDataSource;
    }


    @Override
    public Observable<OrdersResponse> getOrders() {
        return null;
    }
}
