package com.zjy.cash.business.cash.moneyedit;

import android.text.TextUtils;
import android.view.KeyEvent;

import com.zjy.baselib.component.keyboard.AppendKeyEntry;
import com.zjy.baselib.component.keyboard.KeyEntry;

import java.text.DecimalFormat;

public abstract class AbsMoneyEditAppend implements AppendKeyEntry {
    protected String source;
    protected String result;

    public abstract String getDefaultText();

    public abstract String appendNum(String keyName);

    public abstract String appendPeriod(String keyName);

    public abstract String appendDel();

    public AbsMoneyEditAppend(String source) {
        this.source = source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String replaceText(double money) {
        String source = getDefaultText();
        String formatMoney = formatShowMoney(money);
        for (int i = 0; i < formatMoney.length(); i++) {
            String s = Character.valueOf(formatMoney.charAt(i)).toString();
            setSource(source);
            if (TextUtils.equals(s, ".")) {
                source = appendPeriod(s);
            } else if (!TextUtils.isEmpty(s)) {
                source = appendNum(s);
            }
        }
        return source;
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

        return result;
    }




    public static String formatShowMoney(double payMoney) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String format = decimalFormat.format(payMoney);
        while (format.endsWith("0")||format.endsWith(".")) {
            boolean b = format.endsWith(".");
            format = format.substring(0, format.length() - 1);
            if (b) break;
        }

        return format;
    }
}