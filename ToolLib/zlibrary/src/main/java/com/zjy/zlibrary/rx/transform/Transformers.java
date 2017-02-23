package com.zjy.zlibrary.rx.transform;

import android.support.annotation.NonNull;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class Transformers {
    public static @NonNull <T> ObserveForUITransformer<T> observeForUI(){
        return new ObserveForUITransformer<>();
    }

    public static @NonNull <T> Observable.Transformer<T,T> rxNetWork(){
        return observable -> observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> NeverErrorTransformer<T> neverError() {
        return new NeverErrorTransformer<>();
    }
}
