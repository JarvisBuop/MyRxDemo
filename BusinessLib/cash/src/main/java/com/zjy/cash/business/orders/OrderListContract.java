package com.zjy.cash.business.orders;

import com.zjy.cash.data.model.order.PayOrder;
import com.zjy.zlibrary.base.BasePresenter;
import com.zjy.zlibrary.base.BaseView;

import java.util.List;

public interface OrderListContract {
    interface View extends BaseView<OrderListContract.Presenter>{

        void showOrders(int page ,List<PayOrder> orders);

        void showError(String message);
    }
    interface Presenter extends BasePresenter{

        void loadData(int pageIndex);

        boolean hasMoreData();
        void setPhoneNum(String phoneNum);
        void setOrderType(int type);
    }
}
