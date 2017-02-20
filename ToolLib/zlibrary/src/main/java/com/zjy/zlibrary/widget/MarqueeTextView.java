package com.zjy.zlibrary.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created with android studio
 * Creator:zhou.junyou@puscene.com
 * DATE:16/11/21
 * TIME:上午11:29
 * DESC:
 */

public class MarqueeTextView extends TextView {
    public MarqueeTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public MarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MarqueeTextView(Context context) {
        super(context);
    }

    public boolean isFocused() {
        return true;
    }
}