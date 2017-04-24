package com.zjy.cash.business.cash.third;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zjy.baselib.component.keyboard.KeyEntry;
import com.zjy.cash.R2;
import com.zjy.cash.business.Constants;
import com.zjy.cash.business.cash.CashContract;
import com.zjy.cash.business.cash.CashFragment;

import butterknife.BindView;

/**
 * Description:
 * Creator:zhou.junyou@puscene.com
 * Create by:Android Studio
 * Date:2017/4/21
 */
public class ThirdCashFragment extends CashFragment {
    @BindView(R2.id.tv_pay_table)
    TextView tvTableNo;

    public static final String TAG=ThirdCashFragment.class.getSimpleName();
    protected ThirdCashPresenter thirdCashPresenter;

    public static ThirdCashFragment newInstance(String thirdModleJson) {
        Bundle args = new Bundle();
        args.putString(Constants.ThirdCash.MODLE_JSON,thirdModleJson);
        ThirdCashFragment fragment = new ThirdCashFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initPresenter() {
        new ThirdCashPresenter(this);
        thirdCashPresenter.setThirdCashModel(getArguments().getString(Constants.ThirdCash.MODLE_JSON));
    }

    @Override
    public void setPresenter(CashContract.Presenter presenter) {
        thirdCashPresenter = (ThirdCashPresenter) presenter;
    }

    @Override
    protected boolean interceptKeyBoardClick(KeyEntry keyEntry) {
        showToast("请返回调用方修改金额");
        return true;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        initTableAndFee();
    }

    private void initTableAndFee() {
        tvTableNo.setVisibility(View.VISIBLE);
        tvTableNo.setText(thirdCashPresenter.getTableNo());
    }


}
