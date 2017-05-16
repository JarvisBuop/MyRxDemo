package com.zjy.cash.business.cash.moneyedit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.zjy.baselib.component.keyboard.AppendKeyEntry;
import com.zjy.baselib.component.keyboard.KeyEntry;
import com.zjy.cash.R;
import com.zjy.cash.R2;
import com.zjy.cash.component.widget.MoneyEdit;
import com.zjy.zlibrary.fragment.fragmentation.SupportFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTouch;
import es.dmoral.toasty.Toasty;
import io.reactivex.Observable;

/**
 * Description:
 * Creator:zhou.junyou@puscene.com
 * Create by:Android Studio
 * Date:2017/4/14
 */
public class CashEditFragment extends SupportFragment implements AppendKeyEntry {
    public static final String TAG = CashEditFragment.class.getSimpleName();
    @BindView(R2.id.rl_money)
    RelativeLayout rlPayMoney;
    @BindView(R2.id.rl_discount)
    RelativeLayout rlDiscount;
    @BindView(R2.id.tv_money)
    MoneyEdit etPayTotalMoney;
    @BindView(R2.id.et_pay_discount)
    MoneyEdit etDiscount;
    private AbsMoneyEditAppend mAppendKeyEntry;
    private MoneyEditHelper mMoneyEditHelper;
    private boolean resetMoneyAble;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cash_edit, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    private void init() {
        mAppendKeyEntry = new Period2Append("");
        mMoneyEditHelper = new PayEditHelper(etPayTotalMoney);
        mMoneyEditHelper.onEditSelect(getView());
        etPayTotalMoney.setText(mAppendKeyEntry.getDefaultText());
        etDiscount.setText(mAppendKeyEntry.getDefaultText());
        Observable.combineLatest(
                RxTextView.textChanges(etPayTotalMoney),
                RxTextView.textChanges(etDiscount),
                (payMoney, discountMoney) -> {
                    Double newPayMoney = Double.parseDouble(payMoney.toString().replace("¥", ""));
                    Double newDiscountMoney = Double.parseDouble(discountMoney.toString().replace("¥", ""));
                    if(newDiscountMoney>newPayMoney){
                        Toasty.warning(getContext(),"打折金额不能大于总金额").show();
                        showDiscountMoney(newPayMoney);
                    }
                    return newPayMoney == 0 && newDiscountMoney == 0;
                }
        ).compose(RxLifecycle.bindUntilEvent(lifecycleSubject, FragmentEvent.DESTROY))
                .subscribe(a -> resetMoneyAble = !a);
    }

    public void showPayMoney(double payMoney) {
        etPayTotalMoney.setText(mAppendKeyEntry.replaceText(payMoney));
    }

    public void showDiscountMoney(double discountMoney){
        etDiscount.setText(mAppendKeyEntry.replaceText(discountMoney));
    }




    @OnTouch({R2.id.rl_money, R2.id.rl_discount})
    public boolean submit(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int id = v.getId();
            if (id == R.id.rl_money) {
                mMoneyEditHelper = new PayEditHelper(etPayTotalMoney);
            } else if (id == R.id.rl_discount) {
                mMoneyEditHelper = new DiscountEditHelper(etDiscount);
            }
            mMoneyEditHelper.onEditSelect(getView());
        }
        return true;
    }

    @Override
    public boolean onBackPressedSupport() {
        if (resetMoneyAble) {
            etPayTotalMoney.setText(mAppendKeyEntry.getDefaultText());
            etDiscount.setText(mAppendKeyEntry.getDefaultText());
            return true;
        }
        return false;

    }

    @Override
    public String appendKeyEntry(KeyEntry keyEntry) {
        mAppendKeyEntry.setSource(mMoneyEditHelper.getText());
        String result = mAppendKeyEntry.appendKeyEntry(keyEntry);
        mMoneyEditHelper.setText(result);
        return result;
    }


    ///////////////////////////////////////////////////////////////////////////
    //      用与区分当前为哪个的EditText
    ///////////////////////////////////////////////////////////////////////////
    public interface MoneyEditHelper {
        void onEditSelect(View view);

        String getText();

        void setText(String text);

    }


    public static class PayEditHelper implements MoneyEditHelper {
        protected EditText mEditText;
        public PayEditHelper(EditText editText) {
            mEditText=editText;
        }

        @Override
        public void onEditSelect(View view) {
            view.findViewById(R.id.rl_money).setSelected(true);
            view.findViewById(R.id.rl_discount).setSelected(false);
        }

        @Override
        public String getText() {
            return mEditText.getText().toString();
        }

        @Override
        public void setText(String text) {
            mEditText.setText(text);
        }


    }

    public static class DiscountEditHelper implements MoneyEditHelper {
        protected EditText mEditText;

        public DiscountEditHelper(EditText editText) {
            mEditText=editText;
        }

        @Override
        public void onEditSelect(View view) {
            view.findViewById(R.id.rl_money).setSelected(false);
            view.findViewById(R.id.rl_discount).setSelected(true);
        }

        @Override
        public String getText() {
            return mEditText.getText().toString();
        }

        @Override
        public void setText(String text) {
            mEditText.setText(text);
        }


    }


}
