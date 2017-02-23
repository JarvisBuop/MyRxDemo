package com.zjy.baselib.component.rx;

import android.support.annotation.NonNull;

import com.zjy.baselib.data.model.NetWorkResponse;

import rx.Observable;
import rx.Subscriber;

public final class ApiErrorOperator<T extends NetWorkResponse> implements Observable.Operator<T, T> {

  public ApiErrorOperator() {

  }

  @Override
  public Subscriber<? super T> call(final @NonNull Subscriber<? super T> subscriber) {
    return new Subscriber<T>() {
      @Override
      public void onCompleted() {
        if (!subscriber.isUnsubscribed()) {
          subscriber.onCompleted();
        }
      }

      @Override
      public void onError(final @NonNull Throwable e) {
        if (!subscriber.isUnsubscribed()) {
          subscriber.onError(e);
        }
      }

      @Override
      public void onNext(T response) {
        if (subscriber.isUnsubscribed()) {
          return;
        }

        if (response.errno!=0) {
          subscriber.onError(new ServiceException(response));
        }

        subscriber.onNext(response);
      }
    };
  }
}