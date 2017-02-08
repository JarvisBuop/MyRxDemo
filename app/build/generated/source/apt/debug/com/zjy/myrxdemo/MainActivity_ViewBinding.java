// Generated code from Butter Knife. Do not modify!
package com.zjy.myrxdemo;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.roughike.bottombar.BottomBar;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding<T extends MainActivity> implements Unbinder {
  protected T target;

  @UiThread
  public MainActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.messageView = Utils.findRequiredViewAsType(source, R.id.messageView, "field 'messageView'", TextView.class);
    target.mBottomBar = Utils.findRequiredViewAsType(source, R.id.bottomBar, "field 'mBottomBar'", BottomBar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.messageView = null;
    target.mBottomBar = null;

    this.target = null;
  }
}
