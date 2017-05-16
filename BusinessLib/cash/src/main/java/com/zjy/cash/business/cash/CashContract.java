package com.zjy.cash.business.cash;

import com.zjy.zlibrary.base.BasePresenter;
import com.zjy.zlibrary.base.BaseView;

public interface CashContract {
    interface View extends BaseView<CashContract.Presenter> {


        void showToast(String msg);

        void showPayMoney(Double fee);
    }

    interface Presenter extends BasePresenter {

        void setOrderid(String orderid);

        void setThirdId(String thirdId);
    }
}
