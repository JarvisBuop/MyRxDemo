package com.zjy.myrxdemo.business.login;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.zjy.baselib.data.model.bean.User;
import com.zjy.myrxdemo.R;
import com.zjy.myrxdemo.component.AppInjection;
import com.zjy.zlibrary.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class LoginActivity extends BaseActivity implements LoginContract.View {
    @BindView(R.id.et_username)
    EditText etUserName;
    @BindView(R.id.et_password)
    EditText etPassWord;
    @BindView(R.id.bt_go)
    Button btGo;
    @BindView(R.id.cv)
    CardView cv;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.et_layout_username)
    TextInputLayout etLayoutUserName;
    @BindView(R.id.fab_dev)
    TextView tvDev;
    private LoginContract.Presenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mLoginPresenter = new LoginPresenter(AppInjection.provideRepository(), this);
        mLoginPresenter.subscribe();

    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        mLoginPresenter.requestPermission(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLoginPresenter.unsubscribe();
    }




    @OnClick(R.id.bt_go)
    public void login(View view) {
        mLoginPresenter.login(etUserName.getText().toString().trim(), etPassWord.getText().toString().trim());
    }

    @OnClick(R.id.fab)
    public void onClick(View view) {
        ARouter.getInstance().build("/myapp/register_activity").navigation(this);
    }

    @OnClick(R.id.fab_dev)
    public void develop(View view) {
        loginSuccess();
    }


    @Override
    public void setPresenter(LoginContract.Presenter presenter) {

    }

    @Override
    public void toastError(String errorMsg) {
        Toasty.safeError(this, errorMsg);
    }

    @Override
    public void loginSuccess() {
        ARouter.getInstance().build("/myapp/main_activity").navigation(this);
    }

    @Override
    public void showUser(User user) {
        etUserName.setText(user.userName);
        etPassWord.setText(user.password);
    }
}
