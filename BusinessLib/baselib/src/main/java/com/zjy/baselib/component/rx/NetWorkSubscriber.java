package com.zjy.baselib.component.rx;

import com.zjy.baselib.data.model.NetWorkResponse;

import rx.Subscriber;
import timber.log.Timber;


public  class NetWorkSubscriber<T extends NetWorkResponse> extends Subscriber<T> {
    public static final String TAG=NetWorkSubscriber.class.getSimpleName();

    public NetWorkSubscriber() {
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        Timber.d(e);
    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onStart() {
        super.onStart();
    }

}
