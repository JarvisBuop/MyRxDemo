package com.zjy.cash.business.cash;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.zjy.baselib.component.keyboard.AppendKeyEntry;
import com.zjy.baselib.component.keyboard.KeyBoardFragment;
import com.zjy.baselib.component.keyboard.KeyBoardListener;
import com.zjy.baselib.component.keyboard.KeyEntry;
import com.zjy.cash.R;
import com.zjy.cash.R2;
import com.zjy.zlibrary.fragment.fragmentation.SupportFragment;
import com.zjy.zlibrary.widget.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class CashFragment extends SupportFragment implements CashContract.View {
    public static final String TAG = CashFragment.class.getSimpleName();
    @BindView(R2.id.titleBar)
    public TitleBar titleBar;
//    @BindView(R2.id.rl_money)
//    RelativeLayout rlPayMoney;
//    @BindView(R2.id.rl_discount)
//    RelativeLayout rlDiscount;
//    @BindView(R2.id.tv_money)
//    MoneyEdit etPayTotalMoney;
//    @BindView(R2.id.et_pay_discount)
//    MoneyEdit etDiscount;

    protected CashContract.Presenter mPresenter;


    public static CashFragment newInstance() {
        CashFragment fragment = new CashFragment();
        return fragment;
    }

//    @OnTouch({R2.id.rl_money, R2.id.rl_discount})
//    public boolean submit(View v, MotionEvent event) {
//        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//            int id = v.getId();
//            if (id == R.id.rl_money) {
//                rlPayMoney.setSelected(true);
//                rlDiscount.setSelected(false);
//                mPresenter.setSelectedEdit(CashContract.Presenter.EDIT_MONEY);
//            } else if (id == R.id.rl_discount) {
//                rlDiscount.setSelected(true);
//                rlPayMoney.setSelected(false);
//                mPresenter.setSelectedEdit(CashContract.Presenter.EDIT_DISCOUNT);
//            }
//        }
//        return true;
//    }

    @Override
    public void setPresenter(CashContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    private void initView(View view) {
        setUpTitleBar();
        initVirtualKeyBoard();
        initCashEdit();
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

    private void initVirtualKeyBoard() {
        KeyBoardFragment cashKeyBoard = new CashKeyBoard();
        loadRootFragment(R.id.num_soft_keyboard, cashKeyBoard);
        cashKeyBoard.setKeyBoardClickListener(new KeyBoardListener() {
            @Override
            public void onVirtualClick(KeyEntry keyEntry) {
                AppendKeyEntry childFragment = findChildFragment(CashEditFragment.class);
                if(childFragment!=null){
                    childFragment.appendKeyEntry(keyEntry);
                }
            }
        });
    }

    private void initCashEdit() {
       CashEditFragment cashEditFragment = new CashEditFragment();
        loadRootFragment(R.id.cash_edit,cashEditFragment);
    }


    @Override
    public void showToast(String msg) {
        Toasty.warning(getContext(),msg).show();
    }
}
