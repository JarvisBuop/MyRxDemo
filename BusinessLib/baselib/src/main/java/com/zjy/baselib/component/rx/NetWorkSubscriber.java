package com.zjy.baselib.component.rx;

import android.support.annotation.NonNull;

import com.zjy.baselib.data.model.NetWorkResponse;
import com.zjy.zlibrary.dialog.Progress;

import rx.Subscriber;
import timber.log.Timber;


public  class NetWorkSubscriber<T extends NetWorkResponse> extends Subscriber<T> {
    public static final String TAG=NetWorkSubscriber.class.getSimpleName();
    private Progress mProgress;

    public NetWorkSubscriber(@NonNull Progress progress) {
        mProgress = progress;
    }

    @Override
    public void onCompleted() {
        mProgress.hide();
    }

    @Override
    public void onError(Throwable e) {
        Timber.d(e);
        mProgress.hide();
    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onStart() {
        super.onStart();
        mProgress.show();
    }

}
