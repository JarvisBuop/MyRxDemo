package com.zjy.myrxdemo.business.login;

import android.app.ActivityOptions;
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
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.zjy.baselib.data.model.bean.User;
import com.zjy.myrxdemo.R;
import com.zjy.myrxdemo.component.AppInjection;
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
    @BindView(R.id.fab_dev)
    TextView tvDev;
    private Progress progress ;
    private LoginContract.Presenter mLoginPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mLoginPresenter=new LoginPresenter(AppInjection.provideRepository(),this);
        progress = new ProgressDegate(DialogUtil.showIndeterminateProgressDialog(this, false,"","正在登录,请等待..."));
    }


    @OnClick(R.id.bt_go)
    public void login(View view) {
        mLoginPresenter.login(etUserName.getText().toString().trim(), etPassWord.getText().toString().trim());
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
            //startActivity(new Intent(this, RegisterActivity.class), options.toBundle());
            ARouter.getInstance().build("/myapp/register_activity").with(options.toBundle()).navigation(this);
        } else {
            //startActivity(new Intent(this, RegisterActivity.class));
            ARouter.getInstance().build("/myapp/register_activity").navigation(this);
        }

    }
    @OnClick(R.id.fab_dev)
    public void develop(View view){
        loginSuccess();
    }


    @Override
    public void setPresenter(LoginContract.Presenter presenter) {

    }

    @Override
    public void toastError(String errorMsg) {
        Toasty.safeError(this,errorMsg);
    }

    @Override
    public void loginSuccess() {
        Explode explode = new Explode();
        explode.setDuration(500);
        getWindow().setExitTransition(explode);
        getWindow().setEnterTransition(explode);
        ActivityOptionsCompat oc2 = ActivityOptionsCompat.makeSceneTransitionAnimation(LoginActivity.this);
        //Intent i2 = new Intent(LoginActivity.this, MainActivity.class);
        //startActivity(i2, oc2.toBundle());
        ARouter.getInstance().build("/myapp/main_activity").with(oc2.toBundle()).navigation(this);
    }


    @Override
    public void showUser(User user) {
        etUserName.setText(user.userName);
        etPassWord.setText(user.password);
    }

    @Override
    public void hideProgress() {
        progress.hide();
    }

    @Override
    public void showProgress() {
        progress.show();
    }

}
