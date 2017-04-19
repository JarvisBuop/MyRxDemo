package com.zjy.baselib.component.keyboard;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zjy.baselib.R;

import java.util.List;

/**
 * Description: 键盘item 不同要求直接替换
 * Creator:zhou.junyou@puscene.com
 * Create by:Android Studio
 * Date:2017/4/6
 */
public class KeyBoardAdapter extends BaseQuickAdapter<KeyEntry, BaseViewHolder> {

    public KeyBoardAdapter(int layoutResId, List<KeyEntry> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, KeyEntry item) {
        TextView tvItem = helper.getView(R.id.keyboard_item);
        if (!TextUtils.isEmpty(item.getKeyName())) {
            tvItem.setText(item.getKeyName());
        }
        if (item.getKeyImage() != 0) {
            tvItem.setBackground(ContextCompat.getDrawable(mContext, item.getKeyImage()));
        }

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {

    }
}
