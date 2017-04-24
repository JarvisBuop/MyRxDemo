package com.zjy.cash.business.cash;

public class CashPresenter implements CashContract.Presenter {
    protected CashContract.View mCashView;
    public CashPresenter(CashContract.View cashView) {
        mCashView = cashView;
        mCashView.setPresenter(this);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }


}
