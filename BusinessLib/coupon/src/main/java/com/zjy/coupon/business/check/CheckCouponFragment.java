package com.zjy.coupon.business.check;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zjy.coupon.R;
import com.zjy.zlibrary.fragment.fragmentation.SupportFragment;

public class CheckCouponFragment extends SupportFragment implements CheckCouponContract.View {
    public static final String TAG=CheckCouponFragment.class.getSimpleName();

    public static CheckCouponFragment newInstance() {

        Bundle args = new Bundle();

        CheckCouponFragment fragment = new CheckCouponFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void setPresenter(CheckCouponContract.Presenter presenter) {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_check_coupon, container, false);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
