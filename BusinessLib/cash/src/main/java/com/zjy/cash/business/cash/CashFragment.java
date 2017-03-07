package com.zjy.cash.business.cash;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zjy.cash.R;
import com.zjy.zlibrary.fragment.fragmentation.SupportFragment;
import com.zjy.zlibrary.widget.TitleBar;

import butterknife.ButterKnife;

public class CashFragment extends SupportFragment implements CashContract.View {
    public static final String TAG = CashFragment.class.getSimpleName();
    public TitleBar titleBar;

    public static CashFragment newInstance() {

        Bundle args = new Bundle();

        CashFragment fragment = new CashFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setPresenter(CashContract.Presenter presenter) {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cash, container, false);
        ButterKnife.bind(this,root);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpTitleBar(view);
    }

    private void setUpTitleBar(View v) {
        titleBar = (TitleBar) v.findViewById(R.id.titleBar);
        titleBar.setVisibility(View.VISIBLE);
        titleBar.addAction(new TitleBar.ImageAction(R.drawable.icon_cash_orders) {
            @Override
            public void performAction(View view) {

            }
        });
        titleBar.setLeftImageResource(R.drawable.icon_search);
        titleBar.setTitle("体验店");

        titleBar.setLeftTextColor(Color.WHITE);
        titleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        titleBar.setTitleColor(Color.WHITE);
    }
}
