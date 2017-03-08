package com.zjy.cash.data.source.local;

import com.zjy.baselib.component.Injection.Injection;
import com.zjy.baselib.data.source.local.DaoSession;
import com.zjy.baselib.data.source.local.PreferencesManager;
import com.zjy.cash.data.model.order.OrdersResponse;
import com.zjy.cash.data.source.CashDataSource;

import rx.Observable;

public class CashLocalDataSource implements CashDataSource {
    private static CashLocalDataSource instance = null;
    private final DaoSession mDaoSession;
    private CashLocalDataSource(){
        mDaoSession= Injection.provideDaoSession();
    }

    public static CashLocalDataSource getInstance() {
        synchronized (CashLocalDataSource.class) {
            if (instance == null) {
                instance = new CashLocalDataSource();
            }
        }
        return instance;
    }


    @Override
    public Observable<OrdersResponse> getOrders(String token, int type, String all, int page, String mobile, String apiVersion) {
        return null;
    }

    @Override
    public String getSessionId() {
        return PreferencesManager.getSessionId();
    }
}
