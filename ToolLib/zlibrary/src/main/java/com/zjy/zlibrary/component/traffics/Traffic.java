package com.zjy.zlibrary.component.traffics;

import android.content.Context;
import android.content.IntentFilter;

import com.zjy.zlibrary.component.traffics.broadcast.NetStateReceiver;
import com.zjy.zlibrary.component.traffics.data.source.TrafficRepository;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.subscribers.DisposableSubscriber;

public class Traffic {


    private static Traffic instance = null;

    private static boolean initialized = false;

    private DisposableSubscriber<Long> mDisposableSubscriber;
    protected NetStateReceiver mNetStateReceiver;


    private Traffic() {
        mRepository = TrafficRepository.getInstance();
    }

    public static Traffic getInstance() {
        synchronized (Traffic.class) {
            if (instance == null) {
                instance = new Traffic();
            }
        }
        return instance;
    }

    protected static Context sContext;

    private TrafficRepository mRepository;


    public static void init(Context context) {
        if (initialized) {
            throw new IllegalArgumentException("Traffic already init");
        }
        initialized = true;
        sContext = context;
        getInstance().registBroadCast();
        getInstance().start();
    }




    public static Context getContext() {
        return sContext;
    }

    public static void exit() {
        getInstance().end();
    }

    public void reboot() {
        mRepository.saveTraffic();
    }

    public void setNetType(int type) {
        mRepository.saveTraffic();
        mRepository.setNetType(type);
    }

    /**
     *Android N以上不支持静态注册网络静态广播 这里动态注册一下
     */
    private void registBroadCast() {
        mNetStateReceiver = new NetStateReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        sContext.registerReceiver(mNetStateReceiver, intentFilter);

    }


    private void start() {
        mDisposableSubscriber = Flowable.interval(5, 20, TimeUnit.SECONDS)
                .subscribeWith(new DisposableSubscriber<Long>() {
                    @Override
                    public void onNext(Long aLong) {
                        mRepository.saveTraffic();
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void end() {
        if (mNetStateReceiver != null) {
            sContext.unregisterReceiver(mNetStateReceiver);
        }
        if (mDisposableSubscriber != null && !mDisposableSubscriber.isDisposed()) {
            mDisposableSubscriber.dispose();
        }
    }


}
