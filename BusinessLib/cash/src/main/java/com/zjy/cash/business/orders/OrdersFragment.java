package com.zjy.cash.business.orders;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zjy.cash.data.model.order.PayOrder;
import com.zjy.zlibrary.fragment.list.AbsListFragment;

import java.util.ArrayList;
import java.util.List;

public class OrdersFragment extends AbsListFragment implements OrdersContract.View {
    public static final String ORDER_TYPE = "type";
    private OrdersContract.Presenter mPresenter;

    public static OrdersFragment newInstance(int type) {
        Bundle args = new Bundle();
        args.putInt(ORDER_TYPE, type);
        OrdersFragment fragment = new OrdersFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setPresenter(OrdersContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    protected void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }


    @Override
    protected BaseQuickAdapter getAdapter() {
        return new OrdersAdapterNew(((ArrayList<PayOrder>) getItems()));
    }

    /**
     * 满足加载数据条件时 会自动调用该方法
     * @param pageIndex
     */
    @Override
    public void loadData(int pageIndex) {
        mPresenter.loadData(pageIndex);
    }

    @Override
    public void showOrders(int page,List<PayOrder> orders) {
        onDataSuccessReceived(page,orders);
    }

    /*
       是否还有更多数据
     */
    @Override
    protected boolean hasMoreData() {
        return mPresenter.hasMoreData();
    }

    /**
     *
     * @param refresh       是否开启下拉刷新
     * @param loadMore      是否开启上拉加载
     * @param init          初始化时是否加载数据
     */
    @Override
    protected void controlLoadData(boolean refresh, boolean loadMore, boolean init) {
        super.controlLoadData(refresh, loadMore, init);
    }

    /**
     * 空列表时 定义自己的提示
     * @return
     */
    @NonNull
    @Override
    protected String getEmptyMsg() {
        return super.getEmptyMsg();
    }

    /**
     * 定义首页,有的是0，有的是1
     * @return
     */
    @Override
    protected int getInitPageIndex() {
        return super.getInitPageIndex();
    }
}
