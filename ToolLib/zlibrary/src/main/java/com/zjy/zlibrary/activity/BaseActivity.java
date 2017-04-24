package com.zjy.zlibrary.activity;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.trello.rxlifecycle2.android.ActivityEvent;
import com.zjy.zlibrary.dialog.DialogUtil;
import com.zjy.zlibrary.dialog.Progress;
import com.zjy.zlibrary.dialog.ProgressDegate;

import io.reactivex.subjects.BehaviorSubject;

public  class BaseActivity extends AppCompatActivity implements Progress{
    protected Progress progress;
    @Override
    public void hideProgress() {
        if (progress != null)
            progress.hideProgress();
    }

    @Override
    public void showProgress() {
        if (progress == null)
            progress = getProgress();
        progress.showProgress();
    }

    public Progress getProgress() {
        progress = new ProgressDegate(DialogUtil.showIndeterminateProgressDialog(this, false, "", "加载中..."));
        return progress;
    }

    protected final BehaviorSubject<ActivityEvent> lifecycleSubject = BehaviorSubject.create();

    @Override
    @CallSuper
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lifecycleSubject.onNext(ActivityEvent.CREATE);
    }

    @Override
    @CallSuper
    protected void onStart() {
        super.onStart();
        lifecycleSubject.onNext(ActivityEvent.START);
    }

    @Override
    @CallSuper
    protected void onResume() {
        super.onResume();
        lifecycleSubject.onNext(ActivityEvent.RESUME);
    }

    @Override
    @CallSuper
    protected void onPause() {
        lifecycleSubject.onNext(ActivityEvent.PAUSE);
        super.onPause();
    }

    @Override
    @CallSuper
    protected void onStop() {
        lifecycleSubject.onNext(ActivityEvent.STOP);
        super.onStop();
    }

    @Override
    @CallSuper
    protected void onDestroy() {
        lifecycleSubject.onNext(ActivityEvent.DESTROY);
        super.onDestroy();
    }
}
