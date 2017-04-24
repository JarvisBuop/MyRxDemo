package com.zjy.baselib.component.activity;

import android.graphics.PointF;
import android.os.Bundle;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zjy.zlibrary.activity.CaptureParentActivity;

import es.dmoral.toasty.Toasty;
@Route(path = "/basebusy/scan_activity")
public class CommonScanActivity extends CaptureParentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void addOverlayView(ViewGroup parent) {

    }

    @Override
    public void onQRCodeRead(String text, PointF[] points) {
        Toasty.info(this,text).show();
        finish();
    }

    @Override
    protected void onPermissionDenied() {
        Toasty.info(this,"请先设置相机权限").show();
    }
}
