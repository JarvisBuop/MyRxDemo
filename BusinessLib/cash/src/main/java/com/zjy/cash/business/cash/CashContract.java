package com.zjy.cash.business.cash;

import com.zjy.zlibrary.base.BasePresenter;
import com.zjy.zlibrary.base.BaseView;

public interface CashContract {
    interface View extends BaseView<CashContract.Presenter> {


        void showToast(String msg);
    }

    interface Presenter extends BasePresenter {
        int EDIT_MONEY = 0;
        int EDIT_DISCOUNT = 1;

    }
}
