package com.zjy.zlibrary.component.rxpemission;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.Arrays;
import java.util.Map;

import io.reactivex.subjects.PublishSubject;

/**
 * Description:
 * Creator:zhou.junyou@puscene.com
 * Create by:Android Studio
 * Date:2017/4/19
 */
public class RxSettingPermission {
    public String[] pemissions= new String[]{Manifest.permission.SYSTEM_ALERT_WINDOW};

    public static final int OVERLAY_PERMISSION_REQ_CODE = 1234;

    public boolean includePemission(String permission){
        return Arrays.asList(pemissions).contains(permission);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void requestPermissions(String[] permissions, Fragment fragment) {
        for (int i = 0; i <permissions.length ; i++) {
           if(permissions[i]==Manifest.permission.SYSTEM_ALERT_WINDOW){
               requestOverDraw(fragment);
           }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestOverDraw(Fragment fragment) {
        if (!Settings.canDrawOverlays(fragment.getContext())) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + fragment.getContext().getPackageName()));
            fragment.getActivity().startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
        } else {
            // Already hold the SYSTEM_ALERT_WINDOW permission, do addview or something.
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onRequestPermissionResult(int requestCode, Map<String, PublishSubject<Permission>> subjects, Fragment fragment) {
        if (requestCode == OVERLAY_PERMISSION_REQ_CODE){
            String permissions = Manifest.permission.SYSTEM_ALERT_WINDOW;
            boolean granted= Settings.canDrawOverlays(fragment.getContext());
            PublishSubject<Permission> subject = subjects.get(permissions);
            if (subject == null) {
                // No subject found
                Log.e(RxPermissions.TAG, "RxPermissions.onRequestPermissionsResult invoked but didn't find the corresponding permission request.");
                return;
            }
            subjects.remove(permissions);
            subject.onNext(new Permission(permissions, granted,false));
            subject.onComplete();
        }
    }
}
