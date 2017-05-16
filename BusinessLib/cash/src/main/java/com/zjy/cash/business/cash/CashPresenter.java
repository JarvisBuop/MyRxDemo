package com.zjy.cash.business.cash;

public class CashPresenter implements CashContract.Presenter {
    protected CashContract.View mCashView;
    private String requestFrom;
    private Double fee;
    private String orderid;
    private Double discount;
    private String thirdId;
    private String thirdType;

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


    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public void setThirdId(String thirdId) {
        this.thirdId = thirdId;
    }
}
