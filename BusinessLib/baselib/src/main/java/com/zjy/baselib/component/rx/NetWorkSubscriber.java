package com.zjy.baselib.component.rx;

import android.support.annotation.NonNull;

import com.zjy.baselib.data.model.NetWorkResponse;
import com.zjy.zlibrary.dialog.Progress;

import rx.Subscriber;
import timber.log.Timber;


public abstract class NetWorkSubscriber<T extends NetWorkResponse,E> extends Subscriber<T> {
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
        mProgress.hide();
        if(e instanceof ServiceException){
            Timber.e(e);
            onError(((ServiceException) e).errorMsg);
        }

    }



    @Override
    public void onStart() {
        super.onStart();
        mProgress.show();
    }

    public abstract void  onError(String message);
}
