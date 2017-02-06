package com.zjy.zlibrary.rx;

import android.support.annotation.NonNull;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class Transformers {
    public static @NonNull <T> ObserveForUITransformer<T> observeForUI(){
        return new ObserveForUITransformer<>();
    }

    public static @NonNull <T> Observable.Transformer<T,T> rxNetWork(){
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
