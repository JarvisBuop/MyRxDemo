// Generated code from Butter Knife. Do not modify!
package com.zjy.myrxdemo.business.login;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.zjy.myrxdemo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LoginActivity_ViewBinding<T extends LoginActivity> implements Unbinder {
  protected T target;

  private View view2131689599;

  private View view2131689600;

  @UiThread
  public LoginActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.etUserName = Utils.findRequiredViewAsType(source, R.id.et_username, "field 'etUserName'", EditText.class);
    target.etPassWord = Utils.findRequiredViewAsType(source, R.id.et_password, "field 'etPassWord'", EditText.class);
    view = Utils.findRequiredView(source, R.id.bt_go, "field 'btGo' and method 'login'");
    target.btGo = Utils.castView(view, R.id.bt_go, "field 'btGo'", Button.class);
    view2131689599 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.login(p0);
      }
    });
    target.cv = Utils.findRequiredViewAsType(source, R.id.cv, "field 'cv'", CardView.class);
    view = Utils.findRequiredView(source, R.id.fab, "field 'fab' and method 'onClick'");
    target.fab = Utils.castView(view, R.id.fab, "field 'fab'", FloatingActionButton.class);
    view2131689600 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.etLayoutUserName = Utils.findRequiredViewAsType(source, R.id.et_layout_username, "field 'etLayoutUserName'", TextInputLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.etUserName = null;
    target.etPassWord = null;
    target.btGo = null;
    target.cv = null;
    target.fab = null;
    target.etLayoutUserName = null;

    view2131689599.setOnClickListener(null);
    view2131689599 = null;
    view2131689600.setOnClickListener(null);
    view2131689600 = null;

    this.target = null;
  }
}
