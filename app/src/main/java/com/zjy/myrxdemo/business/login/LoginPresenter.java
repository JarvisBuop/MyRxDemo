package com.zjy.myrxdemo.business.login;


import android.Manifest;
import android.os.Build;
import android.text.TextUtils;

import com.blankj.utilcode.utils.AppUtils;
import com.blankj.utilcode.utils.DeviceUtils;
import com.zjy.baselib.component.Injection.Injection;
import com.zjy.baselib.component.rx.ServiceException;
import com.zjy.baselib.component.util.DeviceInfoUtil;
import com.zjy.baselib.component.util.MD5Utility;
import com.zjy.baselib.component.util.WifiUtil;
import com.zjy.baselib.data.model.NetWorkResponse;
import com.zjy.baselib.data.model.bean.User;
import com.zjy.baselib.framework.HttpConstants;
import com.zjy.myrxdemo.data.model.login.ShopInfo;
import com.zjy.myrxdemo.data.model.login.bean.LoginResponse;
import com.zjy.myrxdemo.data.model.login.bean.PayConfigModel;
import com.zjy.myrxdemo.data.model.login.bean.UnionConfigModel;
import com.zjy.myrxdemo.data.source.Repository;
import com.zjy.zlibrary.activity.BaseActivity;
import com.zjy.zlibrary.component.rxpemission.RxPermissions;
import com.zjy.zlibrary.rx.transform.Transformers;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import timber.log.Timber;

;

public class LoginPresenter implements LoginContract.Presenter {
    public static final String TAG = LoginPresenter.class.getSimpleName();
    private final Repository mRepository;
    private final LoginContract.View mLoginView;
    private CompositeDisposable mCompositeDisposable;


    public LoginPresenter(Repository repository, LoginContract.View loginView) {
        mRepository = repository;
        mLoginView = loginView;
        mCompositeDisposable = new CompositeDisposable();
        mLoginView.setPresenter(this);
    }

    @Override
    public void subscribe() {
        mRepository.getUser()
                .filter(s -> s != null)
                .compose(Transformers.rxNetWork())
                .subscribe(user -> {
                    mLoginView.showUser(user);
                    if (user.hasLogin) {

                    }
                });
    }

    @Override
    public void unsubscribe() {
        mCompositeDisposable.dispose();
    }

    @Override
    public void login(final String userName, final String password) {
        if (!localCheckOK(userName, password)) {
            return;
        }
        String ePassword = MD5Utility.md5("SMARTSCENE" + password);
        String DeviceID = TextUtils.isEmpty(Build.SERIAL) ? DeviceUtils.getMacAddress() : Build.SERIAL;
        String V = String.valueOf(AppUtils.getAppVersionCode(Injection.provideContext()));
        String AP = WifiUtil.getSSID(Injection.provideContext())
                + DeviceInfoUtil.getPrintType() + String.format("(%s)", android.os.Build.MODEL);

        mLoginView.showProgress();
        DisposableObserver<NetWorkResponse<UnionConfigModel>> observer = mRepository.login(userName, ePassword, DeviceID, V, AP, HttpConstants.getbApiVersionValue())
                .flatMap(new Function<LoginResponse, ObservableSource<ShopInfo>>() {
                    @Override
                    public ObservableSource<ShopInfo> apply(@NonNull LoginResponse loginResponse) throws Exception {
                        if (loginResponse.errno != 0) {
                            return Observable.error(new ServiceException(loginResponse.errno, loginResponse.errmsg));
                        } else {
                            User user = new User();
                            user.userName = userName;
                            user.password = password;
                            user.hasLogin = true;
                            mRepository.saveUser(user).subscribe();
                            ShopInfo shopInfo = LoginConfig.buildShopInfoFromLoginResponse(loginResponse);
                            mRepository.saveShopInfo(shopInfo).subscribe();
                            mRepository.saveSessionId(shopInfo.sessionId);
                            return Observable.just(shopInfo);
                        }
                    }
                })
                .flatMap(new Function<ShopInfo, ObservableSource<NetWorkResponse<PayConfigModel>>>() {
                    @Override
                    public ObservableSource<NetWorkResponse<PayConfigModel>> apply(@NonNull ShopInfo shopInfo) throws Exception {
                        LoginConfig.getAdvUrl(mRepository).subscribe();
                        LoginConfig.getConfigQR(mRepository).subscribe();
                        if (!shopInfo.bCashEn) {
                            Timber.d("不支持收银");
                            return Observable.empty();
                        } else {
                            return mRepository.getPayConfig(shopInfo.sessionId, HttpConstants.getbApiVersionValue());
                        }
                    }
                })

                .flatMap(new Function<NetWorkResponse<PayConfigModel>, ObservableSource<NetWorkResponse<UnionConfigModel>>>() {
                    @Override
                    public ObservableSource<NetWorkResponse<UnionConfigModel>> apply(@NonNull NetWorkResponse<PayConfigModel> payConfigModelNetWorkResponse) throws Exception {
                        if (DeviceInfoUtil.isWizarPOS() || DeviceInfoUtil.isLianDiA8()) {
                            return mRepository.getUnionConfig(mRepository.getSessionId(), HttpConstants.getbApiVersionValue());
                        }
                        Timber.d("不支持银联收款");
                        return Observable.empty();
                    }

                })
                .compose(Transformers.rxNetWork())
                .subscribeWith(new DisposableObserver<NetWorkResponse<UnionConfigModel>>() {

                    @Override
                    public void onNext(NetWorkResponse<UnionConfigModel> unionConfigModelNetWorkResponse) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mLoginView.hideProgress();
                        mLoginView.toastError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        mLoginView.hideProgress();
                        mLoginView.loginSuccess();
                    }
                });
        mCompositeDisposable.add(observer);


    }

    @Override
    public void requestPermission(BaseActivity activity) {
        RxPermissions rxPermissions = new RxPermissions(activity);
        Disposable disposable = rxPermissions.requestEach(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.SYSTEM_ALERT_WINDOW)
                .subscribe(permission -> {
                    if (permission.granted) {
                        // `permission.name` is granted !
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        // Denied permission without ask never again
                    } else {
                        if(permission.name.contains("SYSTEM_ALERT_WINDOW")){

                        }
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    private boolean localCheckOK(String userName, String password) {
        if (userName.length() < 1) {
            mLoginView.toastError("用户名不能为空");
            return false;
        } else if (password.length() < 1) {
            mLoginView.toastError("用户密码不能为空");
            return false;
        }
        return true;
    }


}
