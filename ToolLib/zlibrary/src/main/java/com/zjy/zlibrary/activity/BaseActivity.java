package com.zjy.zlibrary.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.trello.rxlifecycle.RxLifecycle;
import com.trello.rxlifecycle.android.ActivityEvent;
import com.trello.rxlifecycle.android.RxLifecycleAndroid;

import rx.Observable;
import rx.subjects.BehaviorSubject;
import timber.log.Timber;

public class BaseActivity extends AppCompatActivity {
    private final BehaviorSubject<ActivityEvent> lifecycle = BehaviorSubject.create();

    /**
     * Returns an observable of the activity's lifecycle events.
     */
    public final Observable<ActivityEvent> lifecycle() {
        return lifecycle.asObservable();
    }

    /**
     * Completes an observable when an {@link ActivityEvent} occurs in the activity's lifecycle.
     */
    public final <T> Observable.Transformer<T, T> bindUntilEvent(final ActivityEvent event) {
        return RxLifecycle.bindUntilEvent(lifecycle, event);
    }

    /**
     * Completes an observable when the lifecycle event opposing the current lifecyle event is emitted.
     * For example, if a subscription is made during {@link ActivityEvent#CREATE}, the observable will be completed
     * in {@link ActivityEvent#DESTROY}.
     */
    public final <T> Observable.Transformer<T, T> bindToLifecycle() {
        return RxLifecycleAndroid.bindActivity(lifecycle);
    }
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        Timber.d("onCreate %s", this.toString());
        lifecycle.onNext(ActivityEvent.CREATE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Timber.d("onStart %s", this.toString());
        lifecycle.onNext(ActivityEvent.START);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Timber.d("onResume %s", this.toString());
        lifecycle.onNext(ActivityEvent.RESUME);
    }

    @Override
    protected void onPause() {
        lifecycle.onNext(ActivityEvent.PAUSE);
        super.onPause();
        Timber.d("onPause %s", this.toString());
    }

    @Override
    protected void onStop() {
        lifecycle.onNext(ActivityEvent.STOP);
        super.onStop();
        Timber.d("onStop %s", this.toString());
    }

    @Override
    protected void onDestroy() {
        lifecycle.onNext(ActivityEvent.DESTROY);
        super.onDestroy();
        Timber.d("onDestroy %s", this.toString());
    }
}
