package com.zjy.zlibrary.component.traffics.data.source.save;

import android.net.TrafficStats;

import com.blankj.utilcode.utils.TimeUtils;
import com.zjy.zlibrary.component.traffics.data.source.ITrafficSave;
import com.zjy.zlibrary.component.traffics.data.source.local.TraPersistenceContract;
import com.zjy.zlibrary.component.traffics.data.source.local.TrafficEntity;
import com.zjy.zlibrary.component.traffics.data.source.local.TrafficLocalDataSource;

public class DeviceSave implements ITrafficSave {
    protected final int type;
    protected TrafficEntity entity = new TrafficEntity();
    protected final TrafficLocalDataSource localData;
    protected long startRxBytes;
    protected long startTxBytes;

    public DeviceSave() {
        this(-1);
    }

    public DeviceSave(int type) {
        this.type = type;
        entity.setTraffic_type(type);
        entity.setUid(0+"");
        localData = TrafficLocalDataSource.getInstance();
        startRxBytes = TrafficStats.getTotalRxBytes();
        startTxBytes = TrafficStats.getTotalTxBytes();
    }

    @Override
    public boolean saveRxBytes() {
        if (type == -1) {
            return false;
        }
        entity.setTime_stamp(System.currentTimeMillis());
        entity.setDate(TimeUtils.millis2String(entity.getTime_stamp()));
        entity.setTraffic_direction(TraPersistenceContract.TrafficValue.DIRECTION_RX);
        long endRxBytes = TrafficStats.getTotalRxBytes();
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
        long endTxBytes = TrafficStats.getTotalTxBytes();
        long traffics = endTxBytes - startTxBytes;
        startTxBytes=endTxBytes;
        entity.setTraffics(traffics);
        return localData.insert(entity);
    }
}
