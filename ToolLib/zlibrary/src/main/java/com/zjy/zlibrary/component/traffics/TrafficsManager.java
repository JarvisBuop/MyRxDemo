package com.zjy.zlibrary.component.traffics;

import android.net.TrafficStats;

public class TrafficsManager {

    public static float getMobileTraffics(){
        return TrafficsSharePreference.getTotalTraffics();
    }

    public static void saveMobileTraffics(){
        float temp  =TrafficsSharePreference.getTempTraffics();
        float rb = (float) TrafficStats.getMobileRxBytes() / (1024 * 1024);
        float tb = (float) TrafficStats.getMobileTxBytes() / (1024 * 1024);
        float now = rb + tb;
        float dv= now -temp;
        if(dv>0){
            TrafficsSharePreference.saveTotalTraffics(dv+ getMobileTraffics());
        }else{
            TrafficsSharePreference.saveTotalTraffics(rb+tb+ getMobileTraffics());
        }
        TrafficsSharePreference.saveTempTraffics(now);

    }
}
