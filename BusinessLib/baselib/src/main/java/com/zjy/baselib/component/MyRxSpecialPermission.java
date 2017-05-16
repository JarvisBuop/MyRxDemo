package com.zjy.baselib.component;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.Fragment;

import com.zjy.zlibrary.component.rxpemission.Permission;
import com.zjy.zlibrary.component.rxpemission.RxSpecialPermission;

import java.util.Arrays;

/**
 * Description:
 * Creator:zhou.junyou@puscene.com
 * Create by:Android Studio
 * Date:2017/4/27
 */
public class MyRxSpecialPermission extends RxSpecialPermission {
    public String[] permissions = new String[]{Manifest.permission.SYSTEM_ALERT_WINDOW};
    public static final String TAG = MyRxSpecialPermission.class.getSimpleName();
    public static final int OVERLAY_PERMISSION_REQ_CODE = 1234;

    public boolean includePermission(String permission) {
        return Arrays.asList(permissions).contains(permission);
    }

    @Override
    public void requestSpecialPermission(Fragment fragment, Permission permission) {
        if (permission.name.contains(Manifest.permission.SYSTEM_ALERT_WINDOW)) {
            requestOverDraw(fragment, permission);
        }
    }

    @Override
    public void onRequestPermissionResult(int requestCode, Context context) {
        if (requestCode == OVERLAY_PERMISSION_REQ_CODE) {
            String permission = Manifest.permission.SYSTEM_ALERT_WINDOW;
            boolean granted = Settings.canDrawOverlays(context);
            emitCheckResult(permission, granted);
        }
    }

    protected void requestOverDraw(Fragment fragment, Permission permission) {
        if(Settings.canDrawOverlays(fragment.getContext())){
            emitCheckResult(permission.name,true);
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(fragment.getContext());
        builder.setTitle("帮助");
        builder.setMessage("sssss");
        builder.setPositiveButton("设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + fragment.getContext().getPackageName()));
                fragment.startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
            }
        });
        builder.show();
    }
}
