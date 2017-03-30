package com.zjy.zlibrary.component.traffics.data.source;

import com.zjy.zlibrary.component.traffics.data.source.save.AppSave;
import com.zjy.zlibrary.component.traffics.data.source.save.DeviceSave;

public class TrafficRepository implements ITrafficSave {
    private static TrafficRepository INSTANCE = new TrafficRepository();

    private TrafficRepository(){}


    public static TrafficRepository getInstance(){
     return INSTANCE;
    }

    private ITrafficSave appSave =new AppSave();
    private ITrafficSave deviceSave =new DeviceSave();

    public void saveTraffic(){
        saveRxBytes();
        saveTxBytes();
    }

    public void setNetType(int type){
        appSave=new AppSave(type,android.os.Process.myUid());
        deviceSave=new DeviceSave(type);
    }


    @Override
    public boolean saveRxBytes() {
        deviceSave.saveRxBytes();
        return appSave.saveRxBytes();

    }

    @Override
    public boolean saveTxBytes() {
        deviceSave.saveTxBytes();
        return appSave.saveTxBytes();
    }
}
