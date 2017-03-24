package com.zjy.baselib.component.rx;

import com.zjy.baselib.data.model.NetWorkResponse;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import timber.log.Timber;


public  class NetWorkSubscriber<T extends NetWorkResponse> implements Subscriber<T> {
    public static final String TAG=NetWorkSubscriber.class.getSimpleName();

    public NetWorkSubscriber() {
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {
        Timber.d(e);
    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onSubscribe(Subscription s) {

    }
}
