package com.zjy.cash.business.cash;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.alibaba.android.arouter.launcher.ARouter;
import com.jakewharton.rxbinding2.view.RxView;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.zjy.baselib.component.keyboard.KeyBoardFragment;
import com.zjy.baselib.component.keyboard.KeyBoardListener;
import com.zjy.baselib.component.keyboard.KeyEntry;
import com.zjy.cash.R;
import com.zjy.cash.R2;
import com.zjy.cash.business.cash.moneyedit.CashEditFragment;
import com.zjy.zlibrary.fragment.fragmentation.SupportFragment;
import com.zjy.zlibrary.widget.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

public class CashFragment extends SupportFragment implements CashContract.View {
    public static final String TAG = CashFragment.class.getSimpleName();
    @BindView(R2.id.titleBar)
    public TitleBar titleBar;
    @BindView(R2.id.btn_scan_pay)
    Button btnScanPay;
    protected CashContract.Presenter mPresenter;
    protected KeyBoardFragment cashKeyBoard;
    protected CashEditFragment mCashEditFragment;



    public static CashFragment newInstance() {
        CashFragment fragment = new CashFragment();
        return fragment;
    }

    @Override
    public void setPresenter(CashContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
    }

    protected void initPresenter() {
        new CashPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cash, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    protected void initView(View view) {
        setUpTitleBar();
        initVirtualKeyBoard();
        initCashEdit();
        initPayButton();
    }




    private void setUpTitleBar() {
        titleBar.setVisibility(View.VISIBLE);
        titleBar.addAction(new TitleBar.ImageAction(R.drawable.icon_cash_orders) {
            @Override
            public void performAction(View view) {
                ARouter.getInstance().build("/cash/orders_activity").navigation(getContext());
            }
        });
        titleBar.setLeftImageResource(R.drawable.icon_search);
        titleBar.setTitle("体验店");
        titleBar.setLeftTextColor(Color.WHITE);
        titleBar.setLeftClickListener(v1 -> {
        });
        titleBar.setTitleColor(Color.WHITE);
    }

    protected void initVirtualKeyBoard() {
        cashKeyBoard = new CashKeyBoard();
        loadRootFragment(R.id.num_soft_keyboard, cashKeyBoard);
        cashKeyBoard.setKeyBoardClickListener(new KeyBoardListener() {
            @Override
            public void onVirtualClick(KeyEntry keyEntry) {
                if(interceptKeyBoardClick(keyEntry)){
                    return;
                }
                if(mCashEditFragment!=null){
                    mCashEditFragment.appendKeyEntry(keyEntry);
                }
            }
        });
    }

    protected boolean interceptKeyBoardClick(KeyEntry keyEntry){
        return false;
    }


    private void initCashEdit() {
        mCashEditFragment = new CashEditFragment();
        loadRootFragment(R.id.cash_edit, mCashEditFragment);
    }

    protected void initPayButton() {
        RxView.clicks(btnScanPay)
                .compose(RxLifecycle.bindUntilEvent(lifecycleSubject,FragmentEvent.DESTROY))
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        ARouter.getInstance().build("/basebusy/scan_activity").navigation(getContext());
                    }
                });
    }


    @Override
    public void showToast(String msg) {
        Toasty.warning(getContext(),msg).show();
    }
}
