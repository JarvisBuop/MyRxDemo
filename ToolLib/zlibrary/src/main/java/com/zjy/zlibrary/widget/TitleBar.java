package com.zjy.zlibrary.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zjy.zlibrary.R;

import java.util.LinkedList;

public class TitleBar extends ViewGroup implements View.OnClickListener {
    private static final int DEFAULT_MAIN_TEXT_SIZE = 20;
    private static final int DEFAULT_SUB_TEXT_SIZE = 12;
    private static final int DEFAULT_ACTION_TEXT_SIZE = 18;
    private static final int DEFAULT_TITLE_BAR_HEIGHT = 56;

    private static final String STATUS_BAR_HEIGHT_RES_NAME = "status_bar_height";

    private static final String LAYOUT_MODE_FIX = "fix";
    private static final String LAYOUT_MODE_FIT = "fit";


    private TextView mLeftText;
    private LinearLayout mRightLayout;
    private LinearLayout mCenterLayout;
    private TextView mCenterText;
    private TextView mSubTitleText;
    private View mCustomCenterView;
    private View mDividerView;

    private boolean mImmersive;

    private int mScreenWidth;
    private int mStatusBarHeight;
    private int mActionPadding;
    private int mOutPadding;
    private int mActionTextColor;
    private int mHeight;

    private String mode = LAYOUT_MODE_FIT;

    public TitleBar(Context context) {
        super(context);
        init(context, null);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.titleBar);
            mode = typedArray.getString(R.styleable.titleBar_title_mode);
            if (mode == null) {
                mode = LAYOUT_MODE_FIT;
            }
            typedArray.recycle();

        }
        mScreenWidth = getResources().getDisplayMetrics().widthPixels;
        if (mImmersive) {
            mStatusBarHeight = getStatusBarHeight();
        }
        mActionPadding = dip2px(8);
        mOutPadding = dip2px(16);
        mHeight = dip2px(DEFAULT_TITLE_BAR_HEIGHT);
        initView(context);
    }

    private void initView(Context context) {
        mLeftText = new TextView(context);
        mCenterLayout = new LinearLayout(context);
        mRightLayout = new LinearLayout(context);
        mDividerView = new View(context);

        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        mLeftText.setTextSize(DEFAULT_ACTION_TEXT_SIZE);
        mLeftText.setSingleLine();
        mLeftText.setGravity(Gravity.CENTER_VERTICAL);
        mLeftText.setPadding(mOutPadding, 0, mOutPadding, 0);

        mCenterText = new MarqueeTextView(context);
        mSubTitleText = new TextView(context);
        mCenterLayout.addView(mCenterText);
        mCenterLayout.addView(mSubTitleText);

        mCenterLayout.setGravity(Gravity.LEFT|Gravity.CENTER_VERTICAL);
        mCenterText.setTextSize(DEFAULT_MAIN_TEXT_SIZE);
        mCenterText.setSingleLine();
        mCenterText.setGravity(Gravity.CENTER);
        mCenterText.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        mCenterText.setMarqueeRepeatLimit(6);


        mSubTitleText.setTextSize(DEFAULT_SUB_TEXT_SIZE);
        mSubTitleText.setGravity(Gravity.CENTER);
        mSubTitleText.setEllipsize(TextUtils.TruncateAt.END);

        mRightLayout.setPadding(mOutPadding, 0, mActionPadding, 0);

        addView(mLeftText, layoutParams);
        addView(mCenterLayout);
        addView(mRightLayout, layoutParams);
        addView(mDividerView, new LayoutParams(LayoutParams.MATCH_PARENT, 1));
    }

    public void setImmersive(boolean immersive) {
        mImmersive = immersive;
        if (mImmersive) {
            mStatusBarHeight = getStatusBarHeight();
        } else {
            mStatusBarHeight = 0;
        }
    }

    public void setHeight(int height) {
        mHeight = height;
        setMeasuredDimension(getMeasuredWidth(), mHeight);
    }

    public void setLeftImageResource(int resId) {
        mLeftText.setCompoundDrawablesWithIntrinsicBounds(resId, 0, 0, 0);
    }

    public void setLeftClickListener(OnClickListener l) {
        mLeftText.setOnClickListener(l);
    }

    public void setLeftText(CharSequence title) {
        mLeftText.setText(title);
    }

    public void setLeftText(int resid) {
        mLeftText.setText(resid);
    }

    public void setLeftTextSize(float size) {
        mLeftText.setTextSize(size);
    }

    public void setLeftTextColor(int color) {
        mLeftText.setTextColor(color);
    }

    public void setLeftVisible(boolean visible) {
        mLeftText.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    public String getTitle() {
        return mCenterText.getText().toString();
    }

    public void setCenterDrawable(Drawable drawable) {
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        mCenterText.setCompoundDrawables(null, null, drawable, null);
    }

    public void setTitle(CharSequence title) {
        int index = title.toString().indexOf("\nn");
        if (index > 0) {
            setTitle(title.subSequence(0, index), title.subSequence(index + 1, title.length()), LinearLayout.VERTICAL);
        } else {
            index = title.toString().indexOf("\t");
            if (index > 0) {
                setTitle(title.subSequence(0, index), "  " + title.subSequence(index + 1, title.length()), LinearLayout.HORIZONTAL);
            } else {
                mCenterText.setText(title);
                mSubTitleText.setVisibility(View.GONE);
            }
        }
    }

    public void setTitle(CharSequence title, int gravity) {
        mCenterLayout.setGravity(gravity);
        setTitle(title);
    }

    public void setTitle(int resId, int gravity) {
        mCenterLayout.setGravity(gravity);
        setTitle(resId);
    }

    private void setTitle(CharSequence title, CharSequence subTitle, int orientation) {
        mCenterLayout.setOrientation(orientation);
        mCenterText.setText(title);
        mSubTitleText.setText(subTitle);
        mSubTitleText.setVisibility(View.VISIBLE);
    }

    public void setCenterClickListener(OnClickListener l) {
        mCenterLayout.setOnClickListener(l);
    }

    public void setTitle(int resid) {
        setTitle(getResources().getString(resid));
    }

    public void setTitleColor(int resid) {
        mCenterText.setTextColor(resid);
    }

    public void setTitleSize(float size) {
        mCenterText.setTextSize(size);
    }

    public void setTitleBackground(int resid) {
        mCenterText.setBackgroundResource(resid);
    }

    public void setOnTouchListen(OnTouchListener onTouchListen) {
        mCenterText.setOnTouchListener(onTouchListen);
    }

    public void setSubTitleColor(int resid) {
        mSubTitleText.setTextColor(resid);
    }

    public void setSubTitleSize(float size) {
        mSubTitleText.setTextSize(size);
    }

    public void setCustomTitle(View titleView) {
        if (titleView == null) {
            mCenterText.setVisibility(View.VISIBLE);
            if (mCustomCenterView != null) {
                mCenterLayout.removeView(mCustomCenterView);
            }

        } else {
            if (mCustomCenterView != null) {
                mCenterLayout.removeView(mCustomCenterView);
            }
            LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            mCustomCenterView = titleView;
            mCenterLayout.addView(titleView, layoutParams);
            mCenterText.setVisibility(View.GONE);
        }
    }

    public void setDivider(Drawable drawable) {
        mDividerView.setBackgroundDrawable(drawable);
    }

    public void setDividerColor(int color) {
        mDividerView.setBackgroundColor(color);
    }

    public void setDividerHeight(int dividerHeight) {
        mDividerView.getLayoutParams().height = dividerHeight;
    }

    public void setActionTextColor(int colorResId) {
        mActionTextColor = colorResId;
    }

    /**
     * Function to set a click listener for Title TextView
     *
     * @param listener the onClickListener
     */
    public void setOnTitleClickListener(OnClickListener listener) {
        mCenterText.setOnClickListener(listener);
    }

    @Override
    public void onClick(View view) {
        final Object tag = view.getTag();
        if (tag instanceof Action) {
            final Action action = (Action) tag;
            action.performAction(view);
        }
    }

    /**
     * Adds a list of {@link Action}s.
     *
     * @param actionList the actions to add
     */
    public void addActions(ActionList actionList) {
        int actions = actionList.size();
        for (int i = 0; i < actions; i++) {
            addAction(actionList.get(i));
        }
    }

    /**
     * Adds a new {@link Action}.
     *
     * @param action the action to add
     */
    public View addAction(Action action) {
        final int index = mRightLayout.getChildCount();
        return addAction(action, index);
    }

    /**
     * Adds a new {@link Action} at the specified index.
     *
     * @param action the action to add
     * @param index  the position at which to add the action
     */
    public View addAction(Action action, int index) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.MATCH_PARENT);
        View view = inflateAction(action);
        mRightLayout.addView(view, index, params);
        return view;
    }

    /**
     * Removes all action views from this action bar
     */
    public void removeAllActions() {
        mRightLayout.removeAllViews();
    }

    /**
     * Remove a action from the action bar.
     *
     * @param index position of action to remove
     */
    public void removeActionAt(int index) {
        mRightLayout.removeViewAt(index);
    }

    /**
     * Remove a action from the action bar.
     *
     * @param action The action to remove
     */
    public void removeAction(Action action) {
        int childCount = mRightLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = mRightLayout.getChildAt(i);
            if (view != null) {
                final Object tag = view.getTag();
                if (tag instanceof Action && tag.equals(action)) {
                    mRightLayout.removeView(view);
                }
            }
        }
    }

    /**
     * Returns the number of actions currently registered with the action bar.
     *
     * @return action count
     */
    public int getActionCount() {
        return mRightLayout.getChildCount();
    }

    /**
     * Inflates a {@link View} with the given {@link Action}.
     *
     * @param action the action to inflate
     * @return a view
     */
    private View inflateAction(Action action) {
        View view = null;
        if (TextUtils.isEmpty(action.getText())) {
            ImageView img = new ImageView(getContext());
            img.setImageResource(action.getDrawable());
            view = img;
        } else {
            TextView text = new TextView(getContext());
            text.setGravity(Gravity.CENTER);
            text.setText(action.getText());
            text.setTextSize(DEFAULT_ACTION_TEXT_SIZE);
            if (mActionTextColor != 0) {
                text.setTextColor(mActionTextColor);
            }
            view = text;
        }

        view.setPadding(mActionPadding + 5, 0, mActionPadding, 0);
        view.setTag(action);
        view.setOnClickListener(this);
        return view;
    }

    public View getViewByAction(Action action) {
        View view = findViewWithTag(action);
        return view;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int height;
        if (heightMode != MeasureSpec.EXACTLY) {
            height = mHeight + mStatusBarHeight;
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(mHeight, MeasureSpec.EXACTLY);
        } else {
            height = MeasureSpec.getSize(heightMeasureSpec) + mStatusBarHeight;
        }

        measureChild(mLeftText, widthMeasureSpec, heightMeasureSpec);
        measureChild(mRightLayout, widthMeasureSpec, heightMeasureSpec);
        if (TextUtils.equals(mode, LAYOUT_MODE_FIT)) {
            mCenterLayout.measure(
                    MeasureSpec.makeMeasureSpec(mScreenWidth - mLeftText.getMeasuredWidth() - mRightLayout.getMeasuredWidth(), MeasureSpec.EXACTLY)
                    , heightMeasureSpec);
        } else if (TextUtils.equals(mode, LAYOUT_MODE_FIX)) {
            if (mLeftText.getMeasuredWidth() > mRightLayout.getMeasuredWidth()) {
                mCenterLayout.measure(
                        MeasureSpec.makeMeasureSpec(mScreenWidth - 2 * mLeftText.getMeasuredWidth(), MeasureSpec.EXACTLY)
                        , heightMeasureSpec);
            } else {
                mCenterLayout.measure(
                        MeasureSpec.makeMeasureSpec(mScreenWidth - 2 * mRightLayout.getMeasuredWidth(), MeasureSpec.EXACTLY)
                        , heightMeasureSpec);
            }
        }
        measureChild(mDividerView, widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mLeftText.layout(0, mStatusBarHeight, mLeftText.getMeasuredWidth(), mLeftText.getMeasuredHeight() + mStatusBarHeight);
        mRightLayout.layout(mScreenWidth - mRightLayout.getMeasuredWidth(), mStatusBarHeight,
                mScreenWidth, mRightLayout.getMeasuredHeight() + mStatusBarHeight);
        if (TextUtils.equals(mode, LAYOUT_MODE_FIT)) {
            mCenterLayout.layout(mLeftText.getMeasuredWidth(), mStatusBarHeight, mScreenWidth - mRightLayout.getMeasuredWidth(), getMeasuredHeight());
        } else if (TextUtils.equals(mode, LAYOUT_MODE_FIX)) {
            if (mLeftText.getMeasuredWidth() > mRightLayout.getMeasuredWidth()) {
                mCenterLayout.layout(mLeftText.getMeasuredWidth(), mStatusBarHeight,
                        mScreenWidth - mLeftText.getMeasuredWidth(), getMeasuredHeight());
            } else {
                mCenterLayout.layout(mRightLayout.getMeasuredWidth(), mStatusBarHeight,
                        mScreenWidth - mRightLayout.getMeasuredWidth(), getMeasuredHeight());
            }
        }
        mDividerView.layout(0, getMeasuredHeight() - mDividerView.getMeasuredHeight(), getMeasuredWidth(), getMeasuredHeight());
    }

    public static int dip2px(int dpValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 计算状态栏高度高度
     * getStatusBarHeight
     *
     * @return
     */
    public static int getStatusBarHeight() {
        return getInternalDimensionSize(Resources.getSystem(), STATUS_BAR_HEIGHT_RES_NAME);
    }

    private static int getInternalDimensionSize(Resources res, String key) {
        int result = 0;
        int resourceId = res.getIdentifier(key, "dimen", "android");
        if (resourceId > 0) {
            result = res.getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public void setTitleParamBundle(TitleParamBundle bundle) {
        if (bundle.mLeftDrawable != -1) {
            setLeftImageResource(bundle.mLeftDrawable);
        }
        if (!TextUtils.isEmpty(bundle.mLeftText)) {
            setLeftText(bundle.mLeftText);
        }
        if (bundle.mLeftTextId != 0) {
            setLeftText(bundle.mLeftTextId);
        }
        if (bundle.mLeftTextColor != 0) {
            setLeftTextColor(bundle.mLeftTextColor);
        }
        if (bundle.mLeftTextSize != 0) {
            setLeftTextSize(bundle.mLeftTextSize);
        }
        if (bundle.mLeftOnClickListener != null) {
            setLeftClickListener(bundle.mLeftOnClickListener);
        }
        if (!TextUtils.isEmpty(bundle.mTitleText)) {
            setTitle(bundle.mTitleText);
        }
        if (bundle.mTitleTextId != 0) {
            setTitle(bundle.mTitleTextId);
        }
        if (bundle.mTitleTextColor != 0) {
            setTitleColor(bundle.mTitleTextColor);
        }
        if (bundle.mTitleTextSize != 0) {
            setTitleSize(bundle.mTitleTextSize);
        }
    }

    public static class TitleParamBundle {
        public int mLeftDrawable = 0;
        public String mLeftText = "";
        public int mLeftTextId = 0;
        public int mLeftTextColor = 0;
        public int mLeftTextSize = 0;
        public OnClickListener mLeftOnClickListener = null;
        public String mTitleText = "";
        public int mTitleTextId = 0;
        public int mTitleTextColor = 0;
        public int mTitleTextSize = 0;

        public TitleParamBundle() {

        }

        public TitleParamBundle setLeftDrawable(int leftDrawable) {
            mLeftDrawable = leftDrawable;
            return this;
        }

        public TitleParamBundle setLeftText(String leftText) {
            mLeftText = leftText;
            return this;
        }

        public TitleParamBundle setLeftTextId(int leftTextId) {
            mLeftTextId = leftTextId;
            return this;
        }

        public TitleParamBundle setLeftTextColor(int leftTextColor) {
            mLeftTextColor = leftTextColor;
            return this;
        }

        public TitleParamBundle setLeftTextSize(int leftTextSize) {
            mLeftTextSize = leftTextSize;
            return this;
        }

        public TitleParamBundle setLeftOnClickListener(OnClickListener leftOnClickListener) {
            mLeftOnClickListener = leftOnClickListener;
            return this;
        }

        public TitleParamBundle setTitleText(String titleText) {
            mTitleText = titleText;
            return this;
        }

        public TitleParamBundle setTitleTextId(int titleTextId) {
            mTitleTextId = titleTextId;
            return this;
        }

        public TitleParamBundle setTitleTextColor(int titleTextColor) {
            mTitleTextColor = titleTextColor;
            return this;
        }

        public TitleParamBundle setTitleTextSize(int titleTextSize) {
            mTitleTextSize = titleTextSize;
            return this;
        }
    }


    /**
     * A {@link LinkedList} that holds a list of {@link Action}s.
     */
    @SuppressWarnings("serial")
    public static class ActionList extends LinkedList<Action> {
    }

    /**
     * Definition of an action that could be performed, along with a icon to
     * showProgress.
     */
    public interface Action {
        String getText();

        int getDrawable();

        void performAction(View view);
    }

    public static abstract class ImageAction implements Action {
        private int mDrawable;

        public ImageAction(int drawable) {
            mDrawable = drawable;
        }

        @Override
        public int getDrawable() {
            return mDrawable;
        }

        @Override
        public String getText() {
            return null;
        }
    }

    public static abstract class TextAction implements Action {
        final private String mText;

        public TextAction(String text) {
            mText = text;
        }

        @Override
        public int getDrawable() {
            return 0;
        }

        @Override
        public String getText() {
            return mText;
        }
    }

}