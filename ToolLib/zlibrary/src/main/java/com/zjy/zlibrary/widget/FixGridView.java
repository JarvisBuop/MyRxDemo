package com.zjy.zlibrary.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.GridView;

/**
 * Created by zhoujunyou on 16/4/24.
 */
public class FixGridView extends GridView {
    public FixGridView(Context context) {
        super(context);
    }

    public FixGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FixGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
        ViewGroup.LayoutParams params = getLayoutParams();
        params.height=getMeasuredHeight();
    }



}