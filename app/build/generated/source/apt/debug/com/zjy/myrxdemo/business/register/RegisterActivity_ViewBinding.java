// Generated code from Butter Knife. Do not modify!
package com.zjy.myrxdemo.business.register;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.zjy.myrxdemo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RegisterActivity_ViewBinding<T extends RegisterActivity> implements Unbinder {
  protected T target;

  @UiThread
  public RegisterActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.fab = Utils.findRequiredViewAsType(source, R.id.fab, "field 'fab'", FloatingActionButton.class);
    target.cvAdd = Utils.findRequiredViewAsType(source, R.id.cv_add, "field 'cvAdd'", CardView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.fab = null;
    target.cvAdd = null;

    this.target = null;
  }
}
