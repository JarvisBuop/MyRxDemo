package com.zjy.zlibrary.component.traffics;

import android.net.TrafficStats;

public class TrafficsManager {

    public static float getMobileTraffics(){
        return TrafficsSharePreference.getMobileTotalTraffics();
    }

    public static void queryAndSetMobileTraffics(){
        float start  =TrafficsSharePreference.getStartMobileTraffics();
        float rb = (float) TrafficStats.getMobileRxBytes() / (1024 * 1024);
        float tb = (float) TrafficStats.getMobileTxBytes() / (1024 * 1024);
        TrafficsSharePreference.setMobileTotalTraffics(start+rb+tb);
    }

    public static void setStartMobileTraffics(float traffics){
        TrafficsSharePreference.setStartMobileTraffics(traffics);
    }
}
