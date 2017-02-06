package com.zjy.myrxdemo.business.login;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.transition.Explode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zjy.myrxdemo.MainActivity;
import com.zjy.myrxdemo.R;
import com.zjy.zlibrary.activity.BaseActivity;
import com.zjy.zlibrary.dialog.DialogUtil;
import com.zjy.zlibrary.dialog.Progress;
import com.zjy.zlibrary.dialog.ProgressDegate;
import com.zjy.zlibrary.rx.Transformers;
import com.zjy.zlibrary.rx.subscriber.NetWorkSubscriber;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import rx.Observable;
import rx.functions.Func1;

public class LoginActivity extends BaseActivity {
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

    private User mUser = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setUpWidget();

    }

    private void setUpWidget() {

    }

    @OnClick(R.id.bt_go)
    public void login(View view) {
        mUser.userName = etUserName.getText();
        mUser.passWord = etPassWord.getText();
        if (!localCheckOK()) {
            return;
        }
        remoteCheck();

    }


    private boolean localCheckOK() {
        if (etUserName.getEditableText().length() < 6) {
            Toasty.error(this, "用户名长度必须大于6").show();
            return false;
        } else if (etPassWord.getEditableText().length() < 6) {
            Toasty.error(this, "用户密码长度必须大于6").show();
            return false;
        }
        return true;
    }

    private void remoteCheck() {
        Progress progress = new ProgressDegate(DialogUtil.showIndeterminateProgressDialog(LoginActivity.this, false,"","正在登录,请等待..."));
        Observable.just(mUser)
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
                        if (!TextUtils.equals(user.passWord, "123123")) {
                            return "密码错误";
                        }
                        return "success";
                    }
                })
                .compose(this.<String>bindToLifecycle())
                .compose(Transformers.<String>rxNetWork())
                .subscribe(new NetWorkSubscriber<String>(progress) {
                    @Override
                    public void onNext(String result) {
                        super.onNext(result);
                        if (TextUtils.equals(result, "success")) {
                            Explode explode = new Explode();
                            explode.setDuration(500);
                            getWindow().setExitTransition(explode);
                            getWindow().setEnterTransition(explode);
                            ActivityOptionsCompat oc2 = ActivityOptionsCompat.makeSceneTransitionAnimation(LoginActivity.this);
                            Intent i2 = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(i2, oc2.toBundle());
                        } else {
                            Toasty.error(LoginActivity.this, result).show();
                        }
                    }

                });

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

    public static final class User {
        public CharSequence userName;
        public CharSequence passWord;
    }
}
