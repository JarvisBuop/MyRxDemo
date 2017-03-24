package com.zjy.zlibrary.rx.transform;

import android.support.annotation.Nullable;

import java.util.function.Consumer;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;

public final class NeverErrorTransformer<T> implements ObservableTransformer<T, T> {
  private final @Nullable
  Consumer errorAction;

  protected NeverErrorTransformer() {
    this.errorAction = null;
  }

  protected NeverErrorTransformer(final Consumer<Throwable> errorAction) {
    this.errorAction = errorAction;
  }


  @Override
  public ObservableSource<T> apply(Observable<T> upstream) {
    return upstream
            .doOnError(e -> {
              if (errorAction != null) {
                errorAction.accept(e);
              }
            })
            .onErrorResumeNext(Observable.empty());
  }
}