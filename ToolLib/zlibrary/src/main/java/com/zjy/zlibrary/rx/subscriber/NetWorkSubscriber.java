package com.zjy.zlibrary.rx.subscriber;

import android.support.annotation.NonNull;

import com.zjy.zlibrary.dialog.Progress;

import rx.Subscriber;

public class NetWorkSubscriber<T> extends Subscriber<T> {
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
    }

    @Override
    public void onNext(T o) {
        mProgress.hide();
    }

    @Override
    public void onStart() {
        super.onStart();
        mProgress.show();
    }
}
