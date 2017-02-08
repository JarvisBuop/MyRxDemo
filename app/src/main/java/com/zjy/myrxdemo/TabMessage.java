package com.zjy.myrxdemo;

/**
 * Created by iiro on 7.6.2016.
 */
public class TabMessage {
    public static String get(int menuItemId, boolean isReselection) {
        String message = "Content for ";

        switch (menuItemId) {
            case R.id.tab_cash:
                message += "cash";
                break;
            case R.id.tab_coupon:
                message += "coupon";
                break;
            case R.id.tab_member:
                message += "member";
                break;
            case R.id.tab_set:
                message += "set";
                break;
        }

        if (isReselection) {
            message += " WAS RESELECTED! YAY!";
        }

        return message;
    }
}
