package com.zjy.cash.business.orders;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zjy.cash.R;
import com.zjy.zlibrary.fragment.BaseFragment;

import butterknife.ButterKnife;

public class OrdersFragment extends BaseFragment {
    public static final String TAG=OrdersFragment.class.getSimpleName();

    public static OrdersFragment newInstance() {
        Bundle args = new Bundle();
        OrdersFragment fragment = new OrdersFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_orders, container, false);
        ButterKnife.bind(this,root);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initView(view);
    }

    private void initView(View view) {

    }
}
