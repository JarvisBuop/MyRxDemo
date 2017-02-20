package com.zjy.zlibrary.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zjy.zlibrary.R;

/**
 * Created with android studio
 * Creator:zhou.junyou@puscene.com
 * DATE:16/11/24
 * TIME:下午1:48
 * DESC:
 */

public class TwoTextView extends LinearLayout {
    private static final String DEFAULT_TEXT="";
    private static final float DEFAULT_WEIGHT=1;
    private static  final int  DEFAULT_COLOR= Color.BLACK;
    private static  final float  DEFAULT_SIZE= 14;
    private static  final int  DEFAULT_GRAVITY= Gravity.LEFT;
    protected TextView mLeftText;
    protected TextView mRightText;
    protected float mLeftLayoutWeight;
    protected float mRightLayoutWeight;
    protected int mLeftTextColor;
    protected int mRightTextColor;
    protected float mLeftTextSize;
    protected float mRightTextSize;
    protected int mLeftGravity;
    protected int mRightGravity;
    protected String mLeftTextText;
    protected String mRigtTextText;

    public TwoTextView(Context context) {
        super(context);
        init(context ,null);
    }



    public TwoTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public TwoTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if(attrs!=null){
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.twoTextView);
            mLeftLayoutWeight = typedArray.getFloat(R.styleable.twoTextView_two_leftLayoutWeight,DEFAULT_WEIGHT);
            mRightLayoutWeight = typedArray.getFloat(R.styleable.twoTextView_two_rightLayoutWeight,DEFAULT_WEIGHT);
            mLeftTextColor = typedArray.getColor(R.styleable.twoTextView_two_leftTextColor,DEFAULT_COLOR);
            mRightTextColor = typedArray.getColor(R.styleable.twoTextView_two_rightTextColor,DEFAULT_COLOR);
            mLeftTextSize = typedArray.getDimension(R.styleable.twoTextView_two_leftTextSize,DEFAULT_SIZE);
            mRightTextSize = typedArray.getDimension(R.styleable.twoTextView_two_rightTextSize,DEFAULT_SIZE);
            mLeftGravity = typedArray.getInteger(R.styleable.twoTextView_two_leftGravity,DEFAULT_GRAVITY);
            mRightGravity = typedArray.getInteger(R.styleable.twoTextView_two_rightGravity,DEFAULT_GRAVITY);
            mLeftTextText = typedArray.getString(R.styleable.twoTextView_two_leftText);
            if(mLeftTextText==null){
                mLeftTextText=DEFAULT_TEXT;
            }
            mRigtTextText = typedArray.getString(R.styleable.twoTextView_two_rightText);
            if(mRigtTextText==null){
                mRigtTextText=DEFAULT_TEXT;
            }
            typedArray.recycle();
        }
        initView(context);
    }

    private void initView(Context context) {
        mLeftText = new TextView(context);
        mRightText = new TextView(context);
        mLeftText.setTextSize(TypedValue.COMPLEX_UNIT_PX,mLeftTextSize);
        mRightText.setTextSize(TypedValue.COMPLEX_UNIT_PX,mRightTextSize);
        mLeftText.setText(mLeftTextText);
        mRightText.setText(mRigtTextText);
        mLeftText.setTextColor(mLeftTextColor);
        mRightText.setTextColor(mRightTextColor);
        mLeftText.setGravity(mLeftGravity);
        mRightText.setGravity(mRightGravity);
        LayoutParams leftParams = new LayoutParams(0, LayoutParams.MATCH_PARENT, mLeftLayoutWeight);
        LayoutParams rightParams = new LayoutParams(0, LayoutParams.MATCH_PARENT, mRightLayoutWeight);
        addView(mLeftText,leftParams);
        addView(mRightText,rightParams);
    }

    public void setLeftText(String text){
        mLeftText.setText(text);
    }
    public void setLeftText(int  resId){
        mLeftText.setText(resId);
    }

    public void setLeftTextColor(int color){
        mLeftText.setTextColor(color);
    }

    public void setLeftTextSize(float textSize){
        mLeftText.setTextSize(textSize);
    }

    public void setLeftTextGravity(int gravity){
        mLeftText.setGravity(gravity);
    }

    public void setRightText(String text){
        mRightText.setText(text);
    }
    public void setRightText(int  resId){
        mRightText.setText(resId);
    }

    public void setRightTextColor(int color){
        mRightText.setTextColor(color);
    }

    public void setRightTextSize(float textSize){
        mRightText.setTextSize(textSize);
    }

    public void setRightTextGravity(int gravity){
        mRightText.setGravity(gravity);
    }
}