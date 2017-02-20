package com.zjy.cash.business.cash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zjy.cash.R;
import com.zjy.zlibrary.fragment.BaseFragment;

public class CashFragment extends BaseFragment implements CashContract.View {
    public static final String TAG = CashFragment.class.getSimpleName();

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
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
