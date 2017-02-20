package com.zjy.zlibrary.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import com.zjy.zlibrary.R;

public class NumSoftKeyboard extends View {
    private static final int NUM_COLUMNS = 3;
    private static final int NUM_ROWS = 4;

    private int mBgColor = Color.parseColor("#f9eded");//背景色
    private int mFontColor = Color.parseColor("#333333");//字体颜色
    private int mLineColor = Color.parseColor("#A9A9A9");//线条颜色
    private int mPressColor = Color.parseColor("#d53d3c");//按下颜色
    private int mBgColor2 = Color.parseColor("#595959");//指定背景色
    private int mPressColor2 = Color.parseColor("#3b3b3b");//指定按下背景色2

    private DisplayMetrics mDisplayMetrics;
    private int[][] numsString;
    private int mColumnSize, mRowSize;
    private Paint mPaint;
    private int mNumSize = 18;

    private int mSelectKey = -1;
    private boolean mIsPress;
    private NumClick mNumClick;

    public NumSoftKeyboard(Context context) {
        this(context, null);
    }

    public NumSoftKeyboard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public NumSoftKeyboard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mDisplayMetrics = getResources().getDisplayMetrics();
        mPaint = new Paint();
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NumSoftKeyboard);
            mBgColor = typedArray.getColor(R.styleable.NumSoftKeyboard_numBgColor, mBgColor);
            mBgColor2 = typedArray.getColor(R.styleable.NumSoftKeyboard_numBgColor2, mBgColor2);
            mFontColor = typedArray.getColor(R.styleable.NumSoftKeyboard_numFontColor, mFontColor);
            mLineColor = typedArray.getColor(R.styleable.NumSoftKeyboard_numLineColor, mLineColor);
            mPressColor = typedArray.getColor(R.styleable.NumSoftKeyboard_numPressColor, mPressColor);
            typedArray.recycle();

        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if (heightMode == MeasureSpec.AT_MOST) {
            heightSize = mDisplayMetrics.densityDpi * 200;
        }
        if (widthMode == MeasureSpec.AT_MOST) {
            widthSize = mDisplayMetrics.densityDpi * 300;
        }
        setMeasuredDimension(widthSize, heightSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        initSize();
        numsString = new int[4][3];
        mPaint.setColor(mBgColor);
        canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
        drawRectBg2(canvas, 9, 11);
        mPaint.setTextSize(mNumSize * mDisplayMetrics.scaledDensity);
        mPaint.setAntiAlias(true);
        String numString;
        for (int num = 0; num < NUM_COLUMNS * NUM_ROWS; num++) {
            numString = (num + 1) + "";
            int column = num % 3;
            int row = num / 3;
            numsString[row][column] = num + 1;
            int startRecX = mColumnSize * column;
            int startRecY = mRowSize * row;
            int endRecX = startRecX + mColumnSize;
            int endRecY = startRecY + mRowSize;


            if (numString.equals(mSelectKey + "") && mIsPress) {
                if (numString.equals(10 + "") || numString.equals(12 + "")) {
                    mPaint.setColor(mPressColor2);
                } else {
                    mPaint.setColor(mPressColor);
                }
                canvas.drawRect(startRecX, startRecY, endRecX, endRecY, mPaint);
            }
            if (numString.equals(10 + "")) {
                numString = ".";

            } else if (numString.equals(11 + "")) {
                numString = "0";
            } else if (numString.equals(12 + "")) {
                numString = "退格";
            }

            int startX = ((int) (mColumnSize * column + (mColumnSize - mPaint.measureText(numString)) / 2));
            int startY = (int) (mRowSize * row + mRowSize / 2 - (mPaint.ascent() + mPaint.descent()) / 2);
            mPaint.setColor(mFontColor);
            canvas.drawText(numString, startX, startY, mPaint);

        }
        for (int i = 0; i < NUM_ROWS + 2; i++) {
            mPaint.setColor(mLineColor);
            canvas.drawLine(0, mRowSize * i, getWidth(), mRowSize * i, mPaint);
        }
        for (int i = 0; i < NUM_COLUMNS - 1; i++) {
            canvas.drawLine(mColumnSize * (i + 1), 0, mColumnSize * (i + 1), getHeight(), mPaint);
        }
    }

    //指定 第n个框背景颜色
    private void drawRectBg2(Canvas canvas, int... array) {
        for (int num : array) {
            int column = num % 3;
            int row = num / 3;
            numsString[row][column] = num + 1;
            int startRecX = mColumnSize * column;
            int startRecY = mRowSize * row;
            int endRecX = startRecX + mColumnSize;
            int endRecY = startRecY + mRowSize;
            mPaint.setColor(mBgColor2);
            canvas.drawRect(startRecX, startRecY, endRecX, endRecY, mPaint);
        }
    }

    private int downX = 0, downY = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int eventCode = event.getAction();
        switch (eventCode) {
            case MotionEvent.ACTION_DOWN:
                downX = ((int) event.getX());
                downY = ((int) event.getY());
                doDownClickAction(downX, downY, true);
                break;
            case MotionEvent.ACTION_CANCEL:
                doDownClickAction(downX, downY, false);
                break;
            case MotionEvent.ACTION_UP:
                doDownClickAction(downX, downY, false);
                int upX = ((int) event.getX());
                int upY = (int) event.getY();
                if (Math.abs(upX - downX) < 20 && Math.abs(upY - downY) < 20) {//点击事件
                    doUpClickAction((upX + downX) / 2, (upY + downY) / 2);
                }
                break;
        }
        return true;
    }

    private void doUpClickAction(int x, int y) {
        if(mColumnSize==0||mRowSize==0)return;
        int column = x / mColumnSize;
        int row = y / mRowSize;
        String numString = "";
        if (column > 2 || row > 3) {
            return;
        }
        if (numsString[row][column] == 10) {
            numString = ".";
        } else if (numsString[row][column] == 11) {
            numString = "0";
        } else if (numsString[row][column] == 12) {
            numString = "删除";
        } else {
            numString = numsString[row][column] + "";
        }
        if (mNumClick != null) {
            mNumClick.onClickNum(numString);
        }
    }

    private void doDownClickAction(int x, int y, boolean isPress) {
        if(mColumnSize==0||mRowSize==0)return;
        int column = x / mColumnSize;
        int row = y / mRowSize;
        if (column > 2 || row > 3) {
            return;
        }
        setSelectKey(numsString[row][column], isPress);
        int startRecX = column * mColumnSize;
        int startRecY = row * mRowSize;
        int endRecX = startRecX + mColumnSize;
        int endRecY = startRecY + mRowSize;
        invalidate(startRecX, startRecY, endRecX, endRecY);
    }

    private void initSize() {
        mColumnSize = getWidth() / NUM_COLUMNS;
        mRowSize = getHeight() / NUM_ROWS;
    }

    public void setSelectKey(int selectKey, boolean isPress) {
        mSelectKey = selectKey;
        mIsPress = isPress;
    }

    /**
     * 键盘点击回调时间
     */
    public interface NumClick {
        void onClickNum(String num);
    }

    public void setNumClick(NumClick numClick) {
        mNumClick = numClick;
    }
}