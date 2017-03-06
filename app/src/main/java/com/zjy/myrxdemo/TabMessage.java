package com.zjy.myrxdemo;

import com.zjy.baselib.component.Injection.Injection;

import es.dmoral.toasty.Toasty;

/**
 * Created by iiro on 7.6.2016.
 */
public class TabMessage {
    public static int get(int menuItemId, boolean isReselection) {
        int  index=0;

        switch (menuItemId) {
            case R.id.tab_cash:
                index=0;
                break;
            case R.id.tab_coupon:
                index=1;
                break;
            case R.id.tab_member:
                index=2;
                break;
            case R.id.tab_set:
                index=3;
                break;
        }

        if (isReselection) {
            Toasty.warning(Injection.provideContext(),"WAS RESELECTED! YAY!").show();
        }

        return index;
    }
}
