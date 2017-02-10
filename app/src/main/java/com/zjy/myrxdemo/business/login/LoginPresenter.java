package com.zjy.myrxdemo.business.login;


import android.os.Build;
import android.text.TextUtils;

import com.blankj.utilcode.utils.AppUtils;
import com.blankj.utilcode.utils.DeviceUtils;
import com.zjy.baselib.component.Injection.Injection;
import com.zjy.baselib.component.rx.NetWorkSubscriber;
import com.zjy.baselib.component.util.DeviceInfoUtil;
import com.zjy.baselib.component.util.MD5Utility;
import com.zjy.baselib.component.util.WifiUtil;
import com.zjy.baselib.data.model.NetWorkResponse;
import com.zjy.baselib.framework.ConfigConstants;
import com.zjy.myrxdemo.data.model.login.ShopInfo;
import com.zjy.myrxdemo.data.model.login.User;
import com.zjy.myrxdemo.data.model.login.bean.LoginResponse;
import com.zjy.myrxdemo.data.model.login.bean.PayConfigModel;
import com.zjy.myrxdemo.data.model.login.bean.UnionConfigModel;
import com.zjy.myrxdemo.data.source.Repository;
import com.zjy.zlibrary.dialog.Progress;
import com.zjy.zlibrary.rx.transform.Transformers;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.subjects.PublishSubject;
import rx.subscriptions.CompositeSubscription;

public class LoginPresenter implements LoginContract.Presenter {
    public static final String TAG = LoginPresenter.class.getSimpleName();
    private final Repository mRepository;
    private final LoginContract.View mLoginView;
    private CompositeSubscription mSubscriptions;

    private PublishSubject<LoginResponse> lrSubject = PublishSubject.create();

    public LoginPresenter(Repository repository, LoginContract.View loginView) {
        mRepository = repository;
        mLoginView = loginView;
        mSubscriptions = new CompositeSubscription();
        mLoginView.setPresenter(this);
    }

    @Override
    public void subscribe() {
        mRepository.getUser()
                .filter(new Func1<User, Boolean>() {
                    @Override
                    public Boolean call(User s) {
                        return s != null;
                    }
                })
                .compose(Transformers.<User>rxNetWork())
                .subscribe(new Action1<User>() {
                    @Override
                    public void call(User user) {
                        mLoginView.showUser(user);
                        if (user.hasLogin) {

                        }
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


        Subscription subscription = mRepository.login(userName, ePassword, DeviceID, V, AP, ConfigConstants.getbApiVersionValue())
                .flatMap(new Func1<LoginResponse, Observable<ShopInfo>>() {
                    @Override
                    public Observable<ShopInfo> call(LoginResponse loginResponse) {
                     if(loginResponse.errno!=0){
                         mLoginView.toastError(loginResponse.errmsg);
                         return Observable.empty();
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
                        mSubscriptions.add(LoginConfig.getAdvUrl(mRepository).subscribe());
                        mSubscriptions.add(LoginConfig.getConfigQR(mRepository).subscribe());
                        if(!shopInfo.bCashEn){
                           return Observable.empty();
                        }else {
                            return mRepository.getPayConfig(shopInfo.sessionId,ConfigConstants.getbApiVersionValue());
                        }

                    }
                })

                .flatMap(new Func1<NetWorkResponse<PayConfigModel>, Observable<NetWorkResponse<UnionConfigModel>>>() {
                    @Override
                    public  Observable<NetWorkResponse<UnionConfigModel>> call(NetWorkResponse<PayConfigModel> payConfigResponse) {
                        return mRepository.getUnionConfig(mRepository.getSessionId(),ConfigConstants.getbApiVersionValue());
                    }
                })
                .compose(Transformers.<NetWorkResponse<UnionConfigModel>>rxNetWork())
                .subscribe(new NetWorkSubscriber<NetWorkResponse<UnionConfigModel>,UnionConfigModel>(progress) {
                    @Override
                    public void onSuccess(UnionConfigModel o) {
                        mLoginView.loginSuccess();
                    }

                    @Override
                    public void onFailed(String message) {

                    }
                });
        mSubscriptions.add(subscription);


    }

    private boolean localCheckOK(String userName, String password) {
        if (userName.length() < 1) {
            mLoginView.toastError("用户名不能为空");
            return false;
        } else if (password.length() < 6) {
            mLoginView.toastError("用户密码不能为空");
            return false;
        }
        return true;
    }





}
