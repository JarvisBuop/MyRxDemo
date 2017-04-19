package com.zjy.myrxdemo.business.login;

import com.zjy.baselib.data.model.bean.User;
import com.zjy.zlibrary.activity.BaseActivity;
import com.zjy.zlibrary.base.BasePresenter;
import com.zjy.zlibrary.base.BaseView;

public interface LoginContract {
    interface View extends BaseView<Presenter>{
        void toastError(String errorMsg);
        void loginSuccess();
        void showUser(User user);

        void hideProgress();
        void showProgress();
    }
    interface Presenter extends BasePresenter{
        void login(String userName, String password);

        void requestPermission(BaseActivity loginActivity);
    }


}
