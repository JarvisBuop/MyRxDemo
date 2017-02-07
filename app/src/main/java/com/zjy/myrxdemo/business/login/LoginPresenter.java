package com.zjy.myrxdemo.business.login;


import android.text.TextUtils;

import com.zjy.myrxdemo.data.model.User;
import com.zjy.myrxdemo.data.source.Repository;
import com.zjy.zlibrary.dialog.Progress;
import com.zjy.zlibrary.rx.subscriber.NetWorkSubscriber;
import com.zjy.zlibrary.rx.transform.Transformers;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.subscriptions.CompositeSubscription;

public class LoginPresenter implements LoginContract.Presenter {
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
        mRepository.getUser().subscribe(new Action1<User>() {
            @Override
            public void call(User user) {

            }
        });
    }

    @Override
    public void unsubscribe() {
        mSubscriptions.clear();
    }

    @Override
    public void login(String userName, String password,Progress progress) {
        if(!localCheckOK(userName,password)){
            return ;
        }
        remoteCheck(userName,password,progress);

    }

    private boolean localCheckOK(String userName,String password) {
        if (userName.length() < 6) {
            mLoginView.toastError("用户名长度必须大于6");
            return false;
        } else if (password.length() < 6) {
            mLoginView.toastError("用户密码长度必须大于6");
            return false;
        }
        return true;
    }

    private void remoteCheck(String userName, String password, Progress progress) {
        mUser.userName=userName;
        mUser.password=password;
        Subscription subscription = Observable.just(mUser)
                .map(new Func1<User, String>() {
                    @Override
                    public String call(User user) {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (!TextUtils.equals(user.userName, "zjyzjy")) {

                            return "不存在该用户名";
                        }
                        if (!TextUtils.equals(user.password, "123123")) {
                            return "密码错误";
                        }
                        return "success";
                    }
                })
                .compose(Transformers.<String>rxNetWork())
                .subscribe(new NetWorkSubscriber<String>(progress) {
                    @Override
                    public void onNext(String result) {
                        super.onNext(result);
                        if (TextUtils.equals(result, "success")) {
                            mLoginView.loginSuccess();
                        } else {
                            mLoginView.toastError(result);
                        }
                    }
                });
        mSubscriptions.add(subscription);

    }
}
