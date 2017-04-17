package com.zjy.cash.component.widget;

import android.content.Context;
import android.text.InputFilter;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.util.AttributeSet;

/**
 * Created with android studio
 * Creator:zhou.junyou@puscene.com
 * DATE:16/12/5
 * TIME:下午2:03
 * DESC:
 */

public class MoneyEdit extends android.support.v7.widget.AppCompatEditText {
    public MoneyEdit(Context context) {
        this(context, null);
    }

    public MoneyEdit(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MoneyEdit(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFilters(new InputFilter[]{new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                SpannableStringBuilder ss = new SpannableStringBuilder(source);
                if (source.length()>0&&source.charAt(0) == '￥') {
                    ss.setSpan(new RelativeSizeSpan(0.8f), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
                int dotIndex = source.toString().indexOf(".");
                if(dotIndex>0){
                    ss.setSpan(new RelativeSizeSpan(0.8f), dotIndex, source.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
                return ss;
            }
        }});
    }


}