package com.zjy.zlibrary.component.traffics;

import com.blankj.utilcode.utils.SPUtils;

public class TrafficsSharePreference {
    private static SPUtils sSPUtils=new SPUtils("traffics");
    public static void saveTotalTraffics(float traffics){
        sSPUtils.putFloat("traffics_count",traffics);
    }

    public static float getTotalTraffics(){
        return sSPUtils.getFloat("traffics_count",0);
    }

    public static void saveTempTraffics(float traffics){
        sSPUtils.putFloat("temp_traffics_count",traffics);
    }

    public static float getTempTraffics(){
        return sSPUtils.getFloat("temp_traffics_count",0);
    }

}
