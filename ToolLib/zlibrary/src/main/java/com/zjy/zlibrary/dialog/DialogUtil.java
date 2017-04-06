package com.zjy.zlibrary.dialog;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;

public class DialogUtil {

    public static MaterialDialog showIndeterminateProgressDialog(Context context,boolean horizontal, String title, String content) {
        return new MaterialDialog.Builder(context)
                .title(title)
                .content(content)
                .progress(true, 0)
                .progressIndeterminateStyle(horizontal)
                .build();

    }
}
