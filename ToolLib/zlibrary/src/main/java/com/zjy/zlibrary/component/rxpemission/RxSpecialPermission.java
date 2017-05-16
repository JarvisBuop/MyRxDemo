package com.zjy.zlibrary.component.rxpemission;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.subjects.ReplaySubject;

/**
 * Description:
 * Creator:zhou.junyou@puscene.com
 * Create by:Android Studio
 * Date:2017/4/19
 */
public abstract class RxSpecialPermission {

    protected Map<String, ReplaySubject<Permission>> mSpecialSubjects = new HashMap<>();

    public abstract boolean includePermission(String permission);


    @RequiresApi(api = Build.VERSION_CODES.M)
    public abstract void requestSpecialPermission(Fragment fragment, Permission permission);


    @RequiresApi(api = Build.VERSION_CODES.M)
    public abstract void onRequestPermissionResult(int requestCode, Context context);


    protected void emitCheckResult(String permissions, boolean granted) {
        ReplaySubject<Permission> subject = mSpecialSubjects.get(permissions);
        if (subject == null) {
            // No subject found
            Log.e(RxPermissions.TAG, "RxPermissions.onRequestPermissionsResult invoked but didn't find the corresponding permission request.");
            return;
        }
        mSpecialSubjects.remove(permissions);
        subject.onNext(new Permission(permissions, granted, false));
        subject.onComplete();
    }

    public ReplaySubject<Permission> getSpecialSubject(String permission) {
        return mSpecialSubjects.get(permission);
    }

    public void setSpecialSubject(String permission, ReplaySubject<Permission> subject) {
        mSpecialSubjects.put(permission, subject);
    }
}
