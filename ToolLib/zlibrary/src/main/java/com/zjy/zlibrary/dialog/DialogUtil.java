package com.zjy.zlibrary.dialog;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;

public class DialogUtil {
    public  static MaterialDialog showIndeterminateProgressDialog(Context context){
        return showIndeterminateProgressDialog(context,false);
    }

    public static MaterialDialog showIndeterminateProgressDialog(Context context,boolean horizontal) {
        return showIndeterminateProgressDialog(context,horizontal,"Progress Dialog","Please wait…");
    }
    public static MaterialDialog showIndeterminateProgressDialog(Context context,boolean horizontal, int title, int content) {
        return showIndeterminateProgressDialog(context,horizontal,context.getString(title),context.getString(content));
    }

    public static MaterialDialog showIndeterminateProgressDialog(Context context,boolean horizontal, String title, String content) {
        return new MaterialDialog.Builder(context)
                .title(title)
                .content(content)
                .progress(true, 0)
                .progressIndeterminateStyle(horizontal).build();

    }
}
