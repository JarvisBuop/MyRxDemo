package com.zjy.zlibrary.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.zjy.zlibrary.R;


/**
 * Created by _SOLID
 * Date:2016/7/8
 * Time:10:36
 */
public class StatusViewLayout extends FrameLayout {

    private View mLoadingView;
    private View mErrorView;
    private View mEmptyView;
    private LayoutParams mLayoutParams;
    private OnClickListener mOnRetryListener;
    private TextView status_view_tv_error;
    private TextView status_tv_empty_msg;

    public StatusViewLayout(Context context) {
        this(context, null);
    }

    public StatusViewLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StatusViewLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setUpView();

    }

    private void setUpView() {
        mLayoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mLayoutParams.gravity = Gravity.CENTER;

        mLoadingView = LayoutInflater.from(getContext()).inflate(R.layout.status_view_layout_empty, null);
        mErrorView = LayoutInflater.from(getContext()).inflate(R.layout.status_view_layout_empty, null);
        mEmptyView = LayoutInflater.from(getContext()).inflate(R.layout.status_view_layout_empty, null);
        status_view_tv_error = (TextView) mErrorView.findViewById(R.id.warn);
        status_tv_empty_msg = (TextView) mEmptyView.findViewById(R.id.warn);
        addView(mLoadingView, mLayoutParams);
        addView(mErrorView, mLayoutParams);
        addView(mEmptyView, mLayoutParams);
        mEmptyView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnRetryListener != null) {
                    showLoading();
                    mOnRetryListener.onClick(view);
                }
            }
        });
        mErrorView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnRetryListener != null) {
                    showLoading();
                    mOnRetryListener.onClick(view);
                }
            }
        });
        showContent();
    }

    public void setOnRetryListener(OnClickListener listener) {
        mOnRetryListener = listener;
    }


    public void showLoading() {
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).setVisibility(View.GONE);
        }
        mLoadingView.setVisibility(View.VISIBLE);
    }

    public void showError() {
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).setVisibility(View.GONE);
        }
        mErrorView.setVisibility(View.VISIBLE);
    }

    public void showError(String msg) {
        showError();
        status_view_tv_error.setText(msg);

    }

    public void showEmpty() {
        showEmpty("");

    }

    public void showEmpty(String msg) {
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).setVisibility(View.GONE);
        }
        mEmptyView.setVisibility(View.VISIBLE);
        status_tv_empty_msg.setText(msg);

    }

    public void setEmptyIcon(int iconId){
        Drawable drawable = ContextCompat.getDrawable(getContext(), iconId);
        if(drawable!=null);
        status_tv_empty_msg.setCompoundDrawablesWithIntrinsicBounds(null, drawable,null,null);
    }



    public void showContent() {
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).setVisibility(View.GONE);
        }
        getChildAt(getChildCount() - 1).setVisibility(View.VISIBLE);
    }
}
