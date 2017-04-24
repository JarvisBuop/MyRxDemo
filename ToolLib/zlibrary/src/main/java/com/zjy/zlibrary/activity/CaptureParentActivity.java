package com.zjy.zlibrary.activity;

import android.Manifest;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ViewGroup;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.zjy.zlibrary.R;
import com.zjy.zlibrary.component.rxpemission.RxPermissions;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Description:
 * Creator:zhou.junyou@puscene.com
 * Create by:Android Studio
 * Date:2017/4/24
 */
public abstract class CaptureParentActivity extends BaseActivity implements QRCodeReaderView.OnQRCodeReadListener {
    private ViewGroup mainLayout;
    protected QRCodeReaderView qrCodeReaderView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decoder);
        mainLayout = (ViewGroup) findViewById(R.id.main_layout);
        requestCameraPermission();
    }

    private void requestCameraPermission() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.CAMERA)
                .compose(RxLifecycle.bindUntilEvent(lifecycleSubject, ActivityEvent.DESTROY))
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(@NonNull Boolean granted) throws Exception {
                        if (!granted) {
                            onPermissionDenied();
                        } else {
                            initQRCodeReaderView();
                        }
                    }
                });
    }

    protected abstract void onPermissionDenied();

    private void initQRCodeReaderView() {
        qrCodeReaderView = new QRCodeReaderView(this);
        qrCodeReaderView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mainLayout.addView(qrCodeReaderView, 0, qrCodeReaderView.getLayoutParams());
        addOverlayView(mainLayout);
        qrCodeReaderView.setAutofocusInterval(2000L);
        qrCodeReaderView.setOnQRCodeReadListener(this);
        qrCodeReaderView.setBackCamera();
    }

    protected abstract void addOverlayView(ViewGroup parent);

    public abstract void onQRCodeRead(String text, PointF[] points);

    @Override protected void onResume() {
        super.onResume();

        if (qrCodeReaderView != null) {
            qrCodeReaderView.startCamera();
        }
    }

    @Override protected void onPause() {
        super.onPause();

        if (qrCodeReaderView != null) {
            qrCodeReaderView.stopCamera();
        }
    }


}
