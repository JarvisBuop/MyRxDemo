package com.zjy.myrxdemo.business.login;

import com.zjy.myrxdemo.data.model.User;
import com.zjy.zlibrary.base.BasePresenter;
import com.zjy.zlibrary.base.BaseView;
import com.zjy.zlibrary.dialog.Progress;

public interface LoginContract {
    interface View extends BaseView<Presenter>{
        void toastError(String errorMsg);
        void loginSuccess();
        void showUser(User user);
    }
    interface Presenter extends BasePresenter{
        void login(String userName, String password, Progress progress);
    }


}