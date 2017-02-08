package com.zjy.myrxdemo.business.login;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.transition.Explode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zjy.myrxdemo.MainActivity;
import com.zjy.myrxdemo.R;
import com.zjy.myrxdemo.business.register.RegisterActivity;
import com.zjy.myrxdemo.component.injection.Injection;
import com.zjy.myrxdemo.data.model.User;
import com.zjy.zlibrary.activity.BaseActivity;
import com.zjy.zlibrary.dialog.DialogUtil;
import com.zjy.zlibrary.dialog.Progress;
import com.zjy.zlibrary.dialog.ProgressDegate;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class LoginActivity extends BaseActivity implements LoginContract.View{
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
    private Progress progress ;
    private LoginContract.Presenter mLoginPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mLoginPresenter=new LoginPresenter(Injection.provideRepository(),this);
        progress = new ProgressDegate(DialogUtil.showIndeterminateProgressDialog(this, false,"","正在登录,请等待..."));
    }


    @OnClick(R.id.bt_go)
    public void login(View view) {
      mLoginPresenter.login(etUserName.getText().toString().trim(), etPassWord.getText().toString().trim(),progress);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLoginPresenter.subscribe();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mLoginPresenter.unsubscribe();
    }

    @OnClick(R.id.fab)
    public void onClick(View view) {
        getWindow().setExitTransition(null);
        getWindow().setEnterTransition(null);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options =
                    ActivityOptions.makeSceneTransitionAnimation(this, fab, fab.getTransitionName());
            startActivity(new Intent(this, RegisterActivity.class), options.toBundle());
        } else {
            startActivity(new Intent(this, RegisterActivity.class));
        }

    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {

    }

    @Override
    public void toastError(String errorMsg) {
        Toasty.error(this,errorMsg).show();
    }

    @Override
    public void loginSuccess() {
        Explode explode = new Explode();
        explode.setDuration(500);
        getWindow().setExitTransition(explode);
        getWindow().setEnterTransition(explode);
        ActivityOptionsCompat oc2 = ActivityOptionsCompat.makeSceneTransitionAnimation(LoginActivity.this);
        Intent i2 = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(i2, oc2.toBundle());
    }

    @Override
    public void showUser(User user) {
        etUserName.setText(user.userName);
        etPassWord.setText(user.password);
    }

}
