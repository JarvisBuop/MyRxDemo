package com.zjy.zlibrary.activity;

import android.support.v7.app.AppCompatActivity;

import com.zjy.zlibrary.dialog.DialogUtil;
import com.zjy.zlibrary.dialog.Progress;
import com.zjy.zlibrary.dialog.ProgressDegate;

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
}
