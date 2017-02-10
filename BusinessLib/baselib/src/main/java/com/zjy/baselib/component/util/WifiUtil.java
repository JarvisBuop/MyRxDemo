package com.zjy.baselib.component.util;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

public class WifiUtil {
    public static String getSSID(Context context){
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        if (wifiManager != null && wifiManager.isWifiEnabled()) {
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            if (null != wifiInfo && wifiInfo.getNetworkId() >= 0) {
                String SSID = wifiInfo.getSSID();
                if (SSID != null) {
                        String strApInfo = SSID.replace(' ', '_');// 简单替换，否则要用urlencode
                        String strBssId = wifiInfo.getBSSID();
                        if (strBssId != null) {
                            strApInfo += String.format("[%s]", strBssId);
                        }
                        return strApInfo;
                }
            }
        }
        return "";
    }


}
