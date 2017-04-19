package com.zjy.baselib.component.rx;

import android.support.annotation.NonNull;

import com.zjy.baselib.data.model.NetWorkResponse;

import io.reactivex.ObservableOperator;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public final class ApiErrorOperator<T extends NetWorkResponse> implements ObservableOperator<T, T> {

  public ApiErrorOperator() {

  }

  /*@Override
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
          subscriber.onError(new ServiceException(response.errno,response.errmsg));
        }

        subscriber.onNext(response);
      }
    };
  }*/

  @Override
  public Observer<? super T> apply(final Observer<? super T> observer) throws Exception {
    return new Observer<T>() {
      @Override
      public void onComplete() {
        observer.onComplete();
      }

      @Override
      public void onError(final @NonNull Throwable e) {
          observer.onError(e);
      }

      @Override
      public void onSubscribe(Disposable d) {
        observer.onSubscribe(d);
      }

      @Override
      public void onNext(T response) {

        if (response.errno!=0) {
          observer.onError(new ServiceException(response.errno,response.errmsg));
        }

        observer.onNext(response);
      }
    };
  }
}