package com.zjy.myrxdemo.component.rx;

import android.support.annotation.NonNull;

import com.zjy.myrxdemo.data.model.BaseResponse;
import com.zjy.zlibrary.dialog.Progress;

import rx.Subscriber;
import timber.log.Timber;


public abstract class NetWorkSubscriber<T extends BaseResponse,E> extends Subscriber<T> {
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
        Timber.e(e);
    }

    @Override
    public void onNext(T o) {
        mProgress.hide();
        if(o.errno==0){
            onSuccess((E) o.getData());
        }else {
            onFailed(o.errmsg);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mProgress.show();
    }

    public abstract void  onSuccess(E data);
    public abstract void  onFailed(String message);
}
