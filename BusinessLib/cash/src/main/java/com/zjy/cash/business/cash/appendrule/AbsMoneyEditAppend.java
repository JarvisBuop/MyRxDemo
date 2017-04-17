package com.zjy.cash.business.cash.appendrule;

import android.view.KeyEvent;

import com.zjy.baselib.component.keyboard.AppendKeyEntry;
import com.zjy.baselib.component.keyboard.KeyEntry;

public  abstract class AbsMoneyEditAppend implements AppendKeyEntry {
        protected String source;
        protected String result;
        public OnAppend mOnAppend;

        public abstract String getDefaultText();

        public abstract String appendNum(String keyName);

        public abstract String appendPeriod(String keyName);

        public  abstract String appendDel();

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