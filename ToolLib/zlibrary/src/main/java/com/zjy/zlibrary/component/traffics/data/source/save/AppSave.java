package com.zjy.zlibrary.component.traffics.data.source.save;

import android.net.TrafficStats;

import com.blankj.utilcode.utils.TimeUtils;
import com.zjy.zlibrary.component.traffics.data.source.ITrafficSave;
import com.zjy.zlibrary.component.traffics.data.source.local.TraPersistenceContract;
import com.zjy.zlibrary.component.traffics.data.source.local.TrafficEntity;
import com.zjy.zlibrary.component.traffics.data.source.local.TrafficLocalDataSource;

public class AppSave implements ITrafficSave {
    protected final int type;
    protected TrafficEntity entity = new TrafficEntity();
    protected final TrafficLocalDataSource localData;
    protected long startRxBytes;
    protected long startTxBytes;
    protected int uid;

    public AppSave() {
        this(-1, -1);
    }

    public AppSave(int type) {
        this(type, -1);
    }

    public AppSave(int type, int uid) {
        this.type = type;
        this.uid = uid;
        entity.setTraffic_type(type);
        entity.setUid(uid + "");
        localData = TrafficLocalDataSource.getInstance();
        startRxBytes = TrafficStats.getUidRxPackets(uid);
        startTxBytes = TrafficStats.getUidTxBytes(uid);
    }


    @Override
    public boolean saveRxBytes() {
        if (type == -1) {
            return false;
        }
        entity.setTime_stamp(System.currentTimeMillis());
        entity.setDate(TimeUtils.millis2String(entity.getTime_stamp()));
        entity.setTraffic_direction(TraPersistenceContract.TrafficValue.DIRECTION_RX);
        long endRxBytes = TrafficStats.getUidRxBytes(uid);
        long traffics = endRxBytes - startRxBytes;
        startRxBytes=endRxBytes;
        entity.setTraffics(traffics);
        return localData.insert(entity);
    }

    @Override
    public boolean saveTxBytes() {
        if (type == -1) {
            return false;
        }
        entity.setTime_stamp(System.currentTimeMillis());
        entity.setDate(TimeUtils.millis2String(entity.getTime_stamp()));
        entity.setTraffic_direction(TraPersistenceContract.TrafficValue.DIRECTION_TX);
        long endTxBytes = TrafficStats.getUidTxBytes(uid);
        long traffics = endTxBytes - startTxBytes;
        startTxBytes=endTxBytes;
        entity.setTraffics(traffics);
        return localData.insert(entity);
    }
}
