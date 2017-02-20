package com.zjy.myrxdemo;

import android.support.v4.app.Fragment;

import com.zjy.baselib.component.Injection.Injection;
import com.zjy.cash.business.cash.CashFragment;
import com.zjy.coupon.business.check.CheckCouponFragment;
import com.zjy.member.business.member.MemberFragment;
import com.zjy.myrxdemo.business.set.SetFragment;

import es.dmoral.toasty.Toasty;

/**
 * Created by iiro on 7.6.2016.
 */
public class TabMessage {
    public static Fragment get(int menuItemId, boolean isReselection) {
        Fragment fragment=null;

        switch (menuItemId) {
            case R.id.tab_cash:
                fragment = CashFragment.newInstance();
                break;
            case R.id.tab_coupon:
                fragment = CheckCouponFragment.newInstance();
                break;
            case R.id.tab_member:
                fragment = MemberFragment.newInstance();
                break;
            case R.id.tab_set:
                fragment = SetFragment.newInstance();
                break;
        }

        if (isReselection) {
            Toasty.warning(Injection.provideContext(),"WAS RESELECTED! YAY!").show();
        }

        return fragment;
    }
}
