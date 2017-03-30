package com.zjy.zlibrary.component.traffics.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.zjy.zlibrary.component.traffics.Traffic;



public class NetStateReceiver extends BroadcastReceiver {
    public static final String TAG = NetStateReceiver.class.getSimpleName();
    private static int sLastType = -1;//解决状态改变多次收到广播

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        final int currentType = networkInfo != null ? networkInfo.getType() : -1;
        if (sLastType != currentType) {
            if (networkInfo != null && networkInfo.isAvailable()) {
                //说明网络是连接的
                int type = networkInfo.getType();
                switch (type) {
                    case ConnectivityManager.TYPE_MOBILE:  //移动网络
                        Log.i(TAG, "当前移动网络连接可用 ");
                        break;
                    case ConnectivityManager.TYPE_WIFI:  //wifi
                        Log.i(TAG, "当前WiFi连接可用 ");
                        break;
                }
                Traffic.getInstance().setNetType(type);
            } else {
                Log.i(TAG, "网络不可用");
                Traffic.getInstance().setNetType(-1);
            }
            sLastType=currentType;
        }

    }
}