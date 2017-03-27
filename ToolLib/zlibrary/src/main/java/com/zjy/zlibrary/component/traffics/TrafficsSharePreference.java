package com.zjy.zlibrary.component.traffics;

import com.blankj.utilcode.utils.SPUtils;

public class TrafficsSharePreference {
    private static SPUtils sSPUtils=new SPUtils("traffics");
    public static void setMobileTotalTraffics(float traffics){
        sSPUtils.putFloat("traffics_count",traffics);
    }

    public static float getMobileTotalTraffics(){
        return sSPUtils.getFloat("traffics_count",0);
    }

    public static void setStartMobileTraffics(float traffics){
        sSPUtils.putFloat("start_traffics_count",traffics);
    }

    public static float getStartMobileTraffics(){
        return sSPUtils.getFloat("start_traffics_count",0);
    }

}
