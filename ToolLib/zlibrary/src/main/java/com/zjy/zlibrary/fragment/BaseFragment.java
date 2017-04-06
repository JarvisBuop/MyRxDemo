package com.zjy.zlibrary.fragment;

import android.support.v4.app.Fragment;

import com.zjy.zlibrary.dialog.DialogUtil;
import com.zjy.zlibrary.dialog.Progress;
import com.zjy.zlibrary.dialog.ProgressDegate;

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
}
