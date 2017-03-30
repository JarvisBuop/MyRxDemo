package com.zjy.zlibrary.component.traffics.data.source.local;

import com.zjy.zlibrary.component.traffics.Traffic;

public class TrafficLocalDataSource {
    private static TrafficLocalDataSource instance = null;

    private DaoSession mDaoSession;
    private TrafficLocalDataSource() {
        mDaoSession = new DaoMaster(new TrafficDbOpenHelper(Traffic.getContext(),TraPersistenceContract.TrafficDBEntry.DB_NAME).getWritableDatabase()).newSession();
    }

    public static TrafficLocalDataSource getInstance() {
        synchronized (TrafficLocalDataSource.class) {
            if (instance == null) {
                instance = new TrafficLocalDataSource();
            }
        }
        return instance;
    }

    public boolean insert(TrafficEntity traffic){
        return mDaoSession.getTrafficEntityDao().insert(traffic)>0;
    }
}
