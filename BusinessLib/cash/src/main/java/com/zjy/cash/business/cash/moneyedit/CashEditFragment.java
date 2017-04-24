package com.zjy.cash.business.cash.moneyedit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.zjy.baselib.component.Injection.Injection;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cash_edit, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        init();
    }

    @Override
    public void onResume() {
        super.onResume();
        // mAppendKeyEntry = new Period2Append(etPayTotalMoney.getText().toString());
    }


    private void init() {
        mAppendKeyEntry = new Period0Append("");
        mMoneyEditHelper=new PayEditHelper(etPayTotalMoney,etDiscount,mAppendKeyEntry);
        mMoneyEditHelper.onEditSelect(getView());
        etPayTotalMoney.setText(mAppendKeyEntry.getDefaultText());
        etDiscount.setText(mAppendKeyEntry.getDefaultText());
    }


    @OnTouch({R2.id.rl_money, R2.id.rl_discount})
    public boolean submit(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int id = v.getId();
            if (id == R.id.rl_money) {
                mMoneyEditHelper=new PayEditHelper(etPayTotalMoney,etDiscount,mAppendKeyEntry);
            } else if (id == R.id.rl_discount) {
                mMoneyEditHelper=new DiscountEditHelper(etDiscount,etPayTotalMoney,mAppendKeyEntry);
            }
            mMoneyEditHelper.onEditSelect(getView());
        }
        return true;
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

    public static abstract class AbsMoneyEditHelper implements MoneyEditHelper{
        protected EditText mEditText;
        protected AbsMoneyEditAppend mAbsMoneyEditAppend;

        public AbsMoneyEditHelper(EditText editText, AbsMoneyEditAppend absMoneyEditAppend) {
            mEditText = editText;
            mAbsMoneyEditAppend = absMoneyEditAppend;
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

    public static  class PayEditHelper extends AbsMoneyEditHelper{
        protected final EditText discountEdit;

        public PayEditHelper(EditText editText, EditText discountEdit, AbsMoneyEditAppend absMoneyEditAppend) {
            super(editText, absMoneyEditAppend);
            this.discountEdit = discountEdit;
        }

        @Override
        public void onEditSelect(View view) {
            view.findViewById(R.id.rl_money).setSelected(true);
            view.findViewById(R.id.rl_discount).setSelected(false);
            mAbsMoneyEditAppend.setOnAppend(result -> {
                if(Double.parseDouble(discountEdit.getText().toString().replace("¥",""))>Double.parseDouble(result.replace("¥",""))){
                    discountEdit.setText(result);
                }
                return true;
            });
        }


    }

    public static class DiscountEditHelper extends AbsMoneyEditHelper{

        protected final EditText payEdit;

        public DiscountEditHelper(EditText editText, EditText payEdit, AbsMoneyEditAppend absMoneyEditAppend) {
            super(editText, absMoneyEditAppend);
            this.payEdit = payEdit;
        }

        @Override
        public void onEditSelect(View view) {
            view.findViewById(R.id.rl_money).setSelected(false);
            view.findViewById(R.id.rl_discount).setSelected(true);
            mAbsMoneyEditAppend.setOnAppend(result -> {
                if(Double.parseDouble(payEdit.getText().toString().replace("¥",""))>=Double.parseDouble(result.replace("¥",""))){
                    return true;
                }
                Toasty.info(Injection.provideContext(),"打折金额不能大于总金额").show();
                return false;
            });
        }



    }


}
