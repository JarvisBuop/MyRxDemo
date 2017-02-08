package com.zjy.zlibrary.dialog;

import android.support.annotation.NonNull;

import com.afollestad.materialdialogs.MaterialDialog;

public class ProgressDegate implements Progress {
    private MaterialDialog mDialog;

    public ProgressDegate(@NonNull MaterialDialog dialog) {
        mDialog = dialog;
    }

    @Override
    public void show() {
        mDialog.show();
    }

    @Override
    public void hide() {
        mDialog.hide();
    }
}
