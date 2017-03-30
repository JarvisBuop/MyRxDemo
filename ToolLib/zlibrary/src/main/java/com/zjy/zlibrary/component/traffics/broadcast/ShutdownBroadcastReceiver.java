package com.zjy.zlibrary.component.traffics.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.zjy.zlibrary.component.traffics.Traffic;

/** 
 * 类名：BootBroadcastReceiver  
 * 功能描述：启动时系统发出的广播的接收器 
 * #<uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" /> 
 */
public class ShutdownBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "ShutdownBroadcastReceiver";

    private static final String ACTION_SHUTDOWN = "android.intent.action.ACTION_SHUTDOWN";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("shutdown", "Shut down this system, onReceive()");

        if (intent.getAction().equals(ACTION_SHUTDOWN)) {

            Log.i("shutdown", "Shut down Do thing!====>" );
            Traffic.getInstance().reboot();
        }
    }


}