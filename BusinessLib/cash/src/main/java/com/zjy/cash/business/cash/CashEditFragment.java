package com.zjy.cash.business.cash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
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

import java.text.NumberFormat;

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
        mAppendKeyEntry = new Period2Append("");
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
    //         EditText监听键盘拼接字符串 目前分为 1无小数点  2小数点后两位
    ///////////////////////////////////////////////////////////////////////////
    public static abstract class AbsMoneyEditAppend implements AppendKeyEntry {
        protected String source;
        protected String result;
        public OnAppend mOnAppend;

        abstract String getDefaultText();

        abstract String appendNum(String keyName);

        abstract String appendPeriod(String keyName);

        abstract String appendDel();

        public AbsMoneyEditAppend(String source) {
            this.source = source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        @Override
        public String appendKeyEntry(KeyEntry keyEntry) {
            switch (keyEntry.getKeyCode()) {
                case KeyEvent.KEYCODE_0:
                case KeyEvent.KEYCODE_1:
                case KeyEvent.KEYCODE_2:
                case KeyEvent.KEYCODE_3:
                case KeyEvent.KEYCODE_4:
                case KeyEvent.KEYCODE_5:
                case KeyEvent.KEYCODE_6:
                case KeyEvent.KEYCODE_7:
                case KeyEvent.KEYCODE_8:
                case KeyEvent.KEYCODE_9:
                    result = appendNum(keyEntry.getKeyName());
                    break;
                case KeyEvent.KEYCODE_PERIOD:
                    result = appendPeriod(keyEntry.getKeyName());
                    break;
                case KeyEvent.KEYCODE_DEL:
                    result = appendDel();
                    break;
            }
            if(mOnAppend!=null&&!mOnAppend.onAppend(result)){
                return source;
            }
            return result;
        }

        public interface OnAppend{
            boolean onAppend(String result);
        }

        public void setOnAppend(OnAppend onAppend) {
            mOnAppend = onAppend;
        }
    }

    public static class Period2Append extends AbsMoneyEditAppend {
        public Period2Append(String source) {
            super(source);
        }

        protected String appendNum(String key) {
            String cleanString = (source + key).replaceAll("[¥,.]", "");
            double parsed = Double.parseDouble(cleanString);
            if (parsed > 9999999999.99) {
                return source;
            }
            String formatted = NumberFormat.getNumberInstance().format((parsed / 100));
            return "¥" + formatted.replaceAll(",","");
        }

        @Override
        String appendPeriod(String keyName) {
            return source;
        }

        @Override
        String appendDel() {
            String cleanString = source.substring(0, source.length() - 1).replaceAll("[¥,.]", "");
            double parsed = Double.parseDouble(cleanString);
            if (parsed == 0) {
                return getDefaultText();
            }
            String formatted = NumberFormat.getNumberInstance().format((parsed / 100));
            return "¥" + formatted.replaceAll(",","");
        }

        @Override
        public String getDefaultText() {
            return "¥0.00";
        }
    }

    public static class Period0Append extends AbsMoneyEditAppend {
        @Override
        String getDefaultText() {
            return "¥0";
        }

        @Override
        String appendNum(String keyName) {
            return null;
        }

        @Override
        String appendPeriod(String keyName) {
            return null;
        }

        @Override
        String appendDel() {
            return null;
        }

        public Period0Append(String source) {
            super(source);
        }
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
