package com.zjy.zlibrary.rx.transform;

import android.support.annotation.NonNull;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class Transformers {
    public static @NonNull <T> ObserveForUITransformer<T> observeForUI(){
        return new ObserveForUITransformer<>();
    }

    public static @NonNull <T> ObservableTransformer<T,T> rxNetWork(){
        return observable -> observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> NeverErrorTransformer<T> neverError() {
        return new NeverErrorTransformer<>();
    }
}
