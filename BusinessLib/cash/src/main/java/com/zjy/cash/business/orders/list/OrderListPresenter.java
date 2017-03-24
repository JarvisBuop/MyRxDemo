package com.zjy.cash.business.orders.list;


import com.zjy.baselib.framework.HttpConstants;
import com.zjy.cash.data.model.order.OrdersResponse;
import com.zjy.cash.data.source.CashRepository;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public class OrderListPresenter implements OrderListContract.Presenter {
    private final CashRepository mRepository;
    private final OrderListContract.View mOrdersView;
    private CompositeDisposable mCompositeDisposable;


    public OrderListPresenter(CashRepository repository, OrderListContract.View loginView) {
        mRepository = repository;
        mOrdersView = loginView;
        mCompositeDisposable = new CompositeDisposable();
        mOrdersView.setPresenter(this);
    }


    /**
     * 当前页数
     */
    public int currentPage = 0;
    /**
     * 下一页的查询游标
     */
    private int nextPageIndex = 0;
    /**
     * 是否还有更多订单
     */
    public boolean hasMoreOrders = false;

    /**
     * 查询的手机号
     */
    public String phoneNum = "";


    /**
     * 订单类型
     */
    public int orderType=0;



    /**
     * 进行查询
     *
     * @param cursor int
     */
    @Override
    public void loadData(final int cursor) {
        currentPage=cursor;
        mRepository.getOrders(mRepository.getSessionId(),orderType,"1",cursor,phoneNum, HttpConstants.getbApiVersionValue())
               .subscribe(new Observer<OrdersResponse>() {

                   @Override
                   public void onError(Throwable e) {
                      mOrdersView.showError(e.getMessage());
                   }

                   @Override
                   public void onComplete() {

                   }

                   @Override
                   public void onSubscribe(Disposable d) {

                   }

                   @Override
                   public void onNext(OrdersResponse response) {
                       nextPageIndex = response.nextPage;
                       hasMoreOrders = nextPageIndex > 0;
                       mOrdersView.showOrders(currentPage,response.orders);
                   }
               });

    }

    @Override
    public boolean hasMoreData() {
        return hasMoreOrders;
    }
    @Override
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
    @Override
    public void setOrderType(int type){
        this.orderType=type;
    }


    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }
}
