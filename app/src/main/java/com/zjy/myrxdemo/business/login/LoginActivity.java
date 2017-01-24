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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

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
        if(!localCheckOK()||!remoteCheck()){
            return;
        }
        Explode explode = new Explode();
        explode.setDuration(500);
        getWindow().setExitTransition(explode);
        getWindow().setEnterTransition(explode);
        ActivityOptionsCompat oc2 = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
        Intent i2 = new Intent(this, MainActivity.class);
        startActivity(i2, oc2.toBundle());
    }



    private boolean localCheckOK() {
        if(etUserName.getEditableText().length()<6){
            Toasty.error(this,"用户名长度必须大于6").show();
            return false;
        }else if(etPassWord.getEditableText().length()<6){
            Toasty.error(this,"用户密码长度必须大于6").show();
            return false;
        }
        return true;
    }

    private boolean remoteCheck() {
        if(!TextUtils.equals(etUserName.getText(),"zjyzjy")){
            Toasty.error(this,"不存在该用户名").show();
            return false;
        }else if(!TextUtils.equals(etPassWord.getText(),"123123")){
            Toasty.error(this,"密码错误").show();
            return false;
        }
        return true;
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
}
