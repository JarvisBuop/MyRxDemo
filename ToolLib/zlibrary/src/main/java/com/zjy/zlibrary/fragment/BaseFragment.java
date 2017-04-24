package com.zjy.zlibrary.fragment;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.trello.rxlifecycle2.android.FragmentEvent;
import com.zjy.zlibrary.dialog.DialogUtil;
import com.zjy.zlibrary.dialog.Progress;
import com.zjy.zlibrary.dialog.ProgressDegate;

import io.reactivex.subjects.BehaviorSubject;

public class BaseFragment extends Fragment implements Progress{
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
        progress = new ProgressDegate(DialogUtil.showIndeterminateProgressDialog(getContext(), false, "", "加载中..."));
        return progress;
    }

    protected final BehaviorSubject<FragmentEvent> lifecycleSubject = BehaviorSubject.create();


    @Override
    @CallSuper
    public void onAttach(android.app.Activity activity) {
        super.onAttach(activity);
        lifecycleSubject.onNext(FragmentEvent.ATTACH);
    }

    @Override
    @CallSuper
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lifecycleSubject.onNext(FragmentEvent.CREATE);
    }

    @Override
    @CallSuper
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lifecycleSubject.onNext(FragmentEvent.CREATE_VIEW);
    }

    @Override
    @CallSuper
    public void onStart() {
        super.onStart();
        lifecycleSubject.onNext(FragmentEvent.START);
    }

    @Override
    @CallSuper
    public void onResume() {
        super.onResume();
        lifecycleSubject.onNext(FragmentEvent.RESUME);
    }

    @Override
    @CallSuper
    public void onPause() {
        lifecycleSubject.onNext(FragmentEvent.PAUSE);
        super.onPause();
    }

    @Override
    @CallSuper
    public void onStop() {
        lifecycleSubject.onNext(FragmentEvent.STOP);
        super.onStop();
    }

    @Override
    @CallSuper
    public void onDestroyView() {
        lifecycleSubject.onNext(FragmentEvent.DESTROY_VIEW);
        super.onDestroyView();
    }

    @Override
    @CallSuper
    public void onDestroy() {
        lifecycleSubject.onNext(FragmentEvent.DESTROY);
        super.onDestroy();
    }

    @Override
    @CallSuper
    public void onDetach() {
        lifecycleSubject.onNext(FragmentEvent.DETACH);
        super.onDetach();
    }
}
