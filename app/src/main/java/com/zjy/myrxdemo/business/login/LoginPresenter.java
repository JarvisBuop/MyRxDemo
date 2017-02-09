package com.zjy.myrxdemo.business.login;


import android.os.Build;
import android.text.TextUtils;

import com.blankj.utilcode.utils.AppUtils;
import com.blankj.utilcode.utils.DeviceUtils;
import com.zjy.myrxdemo.component.injection.Injection;
import com.zjy.myrxdemo.component.rx.NetWorkSubscriber;
import com.zjy.myrxdemo.component.util.DeviceInfoUtil;
import com.zjy.myrxdemo.component.util.MD5Utility;
import com.zjy.myrxdemo.component.util.WifiUtil;
import com.zjy.myrxdemo.data.model.BaseResponse;
import com.zjy.myrxdemo.data.model.login.LoginResponse;
import com.zjy.myrxdemo.data.model.login.User;
import com.zjy.myrxdemo.data.source.Repository;
import com.zjy.myrxdemo.framework.ConfigConstants;
import com.zjy.zlibrary.dialog.Progress;
import com.zjy.zlibrary.rx.transform.Transformers;

import rx.functions.Action1;
import rx.functions.Func1;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

public class LoginPresenter implements LoginContract.Presenter {
    public static final String TAG=LoginPresenter.class.getSimpleName();
    private final Repository mRepository;
    private final LoginContract.View mLoginView;
    private CompositeSubscription mSubscriptions;
    private User mUser = new User();

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
                    }
                });
    }

    @Override
    public void unsubscribe() {
        mSubscriptions.clear();
    }

    @Override
    public void login(String userName, String password, Progress progress) {
        if (!localCheckOK(userName, password)) {
            return;
        }
        mUser.userName=userName;
        mUser.password=password;
        String ePassword=  MD5Utility.md5("SMARTSCENE" + password);
        String DeviceID= TextUtils.isEmpty(Build.SERIAL)?DeviceUtils.getMacAddress():Build.SERIAL;
        String V=String.valueOf(AppUtils.getAppVersionCode(Injection.provideContext()));
        String AP= WifiUtil.getSSID(Injection.provideContext())
                + DeviceInfoUtil.getPrintType()+ String.format("(%s)", android.os.Build.MODEL);
        mRepository.login(userName,ePassword,DeviceID,V,AP, ConfigConstants.getbApiVersionValue())
                .compose(Transformers.<LoginResponse>rxNetWork())
                .subscribe(new NetWorkSubscriber<LoginResponse>(progress) {
                    @Override
                    public void onSuccess(BaseResponse o) {
                        Timber.i(TAG,o);
                        mRepository.saveUser(mUser).subscribe();
                        mLoginView.loginSuccess();
                    }

                    @Override
                    public void onFailed(String message) {
                        mLoginView.toastError(message);
                    }
                });


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
