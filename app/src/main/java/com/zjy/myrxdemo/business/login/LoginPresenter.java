package com.zjy.myrxdemo.business.login;


import android.os.Build;
import android.text.TextUtils;

import com.blankj.utilcode.utils.AppUtils;
import com.blankj.utilcode.utils.DeviceUtils;
import com.zjy.baselib.component.Injection.Injection;
import com.zjy.baselib.component.rx.NetWorkSubscriber;
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
import com.zjy.zlibrary.dialog.Progress;
import com.zjy.zlibrary.rx.transform.Transformers;

import rx.Observable;
import rx.Subscription;
import rx.functions.Func1;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

public class LoginPresenter implements LoginContract.Presenter {
    public static final String TAG = LoginPresenter.class.getSimpleName();
    private final Repository mRepository;
    private final LoginContract.View mLoginView;
    private CompositeSubscription mSubscriptions;


    public LoginPresenter(Repository repository, LoginContract.View loginView) {
        mRepository = repository;
        mLoginView = loginView;
        mSubscriptions = new CompositeSubscription();
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
        mSubscriptions.clear();
    }

    @Override
    public void login(final String userName, final String password, final Progress progress) {
        if (!localCheckOK(userName, password)) {
            return;
        }
        String ePassword = MD5Utility.md5("SMARTSCENE" + password);
        String DeviceID = TextUtils.isEmpty(Build.SERIAL) ? DeviceUtils.getMacAddress() : Build.SERIAL;
        String V = String.valueOf(AppUtils.getAppVersionCode(Injection.provideContext()));
        String AP = WifiUtil.getSSID(Injection.provideContext())
                + DeviceInfoUtil.getPrintType() + String.format("(%s)", android.os.Build.MODEL);

        mLoginView.showProgress();
        Subscription subscription = mRepository.login(userName, ePassword, DeviceID, V, AP, HttpConstants.getbApiVersionValue())
                .flatMap(new Func1<LoginResponse, Observable<ShopInfo>>() {
                    @Override
                    public Observable<ShopInfo> call(LoginResponse loginResponse) {
                     if(loginResponse.errno!=0){
                         return Observable.error(new ServiceException(loginResponse.errno,loginResponse.errmsg));
                     }else {
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
                .flatMap(new Func1<ShopInfo, Observable<NetWorkResponse<PayConfigModel>>>() {
                    @Override
                    public Observable<NetWorkResponse<PayConfigModel>> call(ShopInfo shopInfo) {
                        LoginConfig.getAdvUrl(mRepository).subscribe();
                        LoginConfig.getConfigQR(mRepository).subscribe();
                        if(!shopInfo.bCashEn){
                            Timber.d("不支持收银");
                           //return Observable.error(new ServiceException(ServiceException.TRANSFORM_TO_FAILED,"不支持收银"));
                            return Observable.empty();
                        }else {
                            return mRepository.getPayConfig(shopInfo.sessionId, HttpConstants.getbApiVersionValue());
                        }

                    }
                })

                .flatMap(new Func1<NetWorkResponse<PayConfigModel>, Observable<NetWorkResponse<UnionConfigModel>>>() {
                    @Override
                    public  Observable<NetWorkResponse<UnionConfigModel>> call(NetWorkResponse<PayConfigModel> payConfigResponse) {
                       if(DeviceInfoUtil.isWizarPOS() || DeviceInfoUtil.isLianDiA8()){
                           return mRepository.getUnionConfig(mRepository.getSessionId(), HttpConstants.getbApiVersionValue());
                        }
                        Timber.d("不支持银联收款");
                        //return Observable.error(new ServiceException(ServiceException.TRANSFORM_TO_FAILED,"不支持银联收款"));
                        return Observable.empty();

                    }
                })
                .compose(Transformers.rxNetWork())
                .subscribe(new NetWorkSubscriber<NetWorkResponse<UnionConfigModel>>() {

                    @Override
                    public void onNext(NetWorkResponse<UnionConfigModel> unionConfigModelNetWorkResponse) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mLoginView.hideProgress();
                        mLoginView.toastError(e.getMessage());
                    }

                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                        mLoginView.hideProgress();
                        mLoginView.loginSuccess();
                    }
                });
        mSubscriptions.add(subscription);


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
