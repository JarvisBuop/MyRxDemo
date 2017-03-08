package com.zjy.cash.component;

import com.zjy.cash.data.source.CashRepository;
import com.zjy.cash.data.source.local.CashLocalDataSource;
import com.zjy.cash.data.source.remote.CashRemoteDataSource;

public class CashInjecttion {
    public static CashRepository provideRepository(){
        return CashRepository.getInstance(CashLocalDataSource.getInstance(), CashRemoteDataSource.getInstance());
    }
}
