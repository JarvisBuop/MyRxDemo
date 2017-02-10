package com.zjy.baselib.data.source.local;

import com.blankj.utilcode.utils.SPUtils;
import com.zjy.baselib.data.source.PersistenceContract;

public class PreferencesManager {
    private static SPUtils sSPUtils=new SPUtils(PersistenceContract.PreferencesEntry.SP_NAME);

    public static void saveSessionId(String sessionId){
        sSPUtils.putString(PersistenceContract.PreferencesEntry.SESSION_ID,sessionId);
    }

    public static String getSessionId(){
        return sSPUtils.getString(PersistenceContract.PreferencesEntry.SESSION_ID);
    }
}
