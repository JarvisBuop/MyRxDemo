package com.zjy.table.data.source.remote;

import com.zjy.table.data.source.TableDataSource;

/**
 * Description:
 * Creator:zhou.junyou@puscene.com
 * Create by:Android Studio
 * Date:2017/4/26
 */
public class TableRemoteDataSource implements TableDataSource{
    private static TableRemoteDataSource INSTANCE = null;

    private TableRemoteDataSource(){
    }

    public static TableRemoteDataSource getInstance() {
        synchronized (TableRemoteDataSource.class) {
            if (INSTANCE == null) {
                INSTANCE = new TableRemoteDataSource();
            }
        }
        return INSTANCE;
    }
}
