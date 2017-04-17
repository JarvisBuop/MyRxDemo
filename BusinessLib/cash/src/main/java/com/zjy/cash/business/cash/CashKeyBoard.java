package com.zjy.cash.business.cash;

import android.view.KeyEvent;

import com.zjy.baselib.component.keyboard.KeyBoardFragment;
import com.zjy.baselib.component.keyboard.KeyEntry;

import java.util.List;

/**
 * Description:
 * Creator:zhou.junyou@puscene.com
 * Create by:Android Studio
 * Date:2017/4/14
 */
public class CashKeyBoard extends KeyBoardFragment {

    @Override
    protected void initKeyBoardItems(List<KeyEntry> items) {
        items.add(new KeyEntry("1", KeyEvent.KEYCODE_1));
        items.add(new KeyEntry("2", KeyEvent.KEYCODE_2));
        items.add(new KeyEntry("3", KeyEvent.KEYCODE_3));
        items.add(new KeyEntry("4", KeyEvent.KEYCODE_4));
        items.add(new KeyEntry("5", KeyEvent.KEYCODE_5));
        items.add(new KeyEntry("6", KeyEvent.KEYCODE_6));
        items.add(new KeyEntry("7", KeyEvent.KEYCODE_7));
        items.add(new KeyEntry("8", KeyEvent.KEYCODE_8));
        items.add(new KeyEntry("9", KeyEvent.KEYCODE_9));
        items.add(new KeyEntry(".", KeyEvent.KEYCODE_PERIOD));
        items.add(new KeyEntry("0", KeyEvent.KEYCODE_0));
        items.add(new KeyEntry("删除", KeyEvent.KEYCODE_DEL));
    }
}
