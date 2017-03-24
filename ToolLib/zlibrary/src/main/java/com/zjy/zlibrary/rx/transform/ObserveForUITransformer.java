package com.zjy.zlibrary.rx.transform;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;


public final class ObserveForUITransformer<T> implements ObservableTransformer<T, T> {


    @Override
    public ObservableSource<T> apply(Observable<T> upstream) {
        return upstream.flatMap(new Function<T, ObservableSource<T>>() {
            @Override
            public ObservableSource<T> apply(@io.reactivex.annotations.NonNull T t) throws Exception {
                return Observable.just(t).observeOn(AndroidSchedulers.mainThread());
            }
        });
    }
}