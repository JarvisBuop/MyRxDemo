package com.zjy.baselib.component.keyboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.zjy.baselib.R;
import com.zjy.zlibrary.fragment.fragmentation.SupportFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * Description:键盘布局 可继承自定义
 * Creator:zhou.junyou@puscene.com
 * Create by:Android Studio
 * Date:2017/4/6
 */
public class KeyBoardFragment extends SupportFragment {
    public static final String TAG = KeyBoardFragment.class.getSimpleName();
    protected RecyclerView recyclerView;

    protected List<KeyEntry> items = new ArrayList<>();
    protected KeyBoardAdapter adapter;
    protected KeyBoardListener keyBoardListener;


    OnItemClickListener mOnItemClickListener = new OnItemClickListener() {
        @Override
        public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
            KeyEntry keyEntry = ((KeyBoardAdapter) adapter).getData().get(position);
            if (keyBoardListener != null) {
                keyBoardListener.onVirtualClick(keyEntry);
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_keyboard, container, false);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initView(view);
        initKeyBoardItems(items);
        adapter.notifyDataSetChanged();
    }

    private void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        adapter = getAdapter();
        layoutAndDivide(recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(mOnItemClickListener);
    }

    protected KeyBoardAdapter getAdapter() {
        return new KeyBoardAdapter(R.layout.item_virtual_keyboard, items);
    }

    protected void layoutAndDivide(RecyclerView recyclerView) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        DividerItemDecoration decoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        decoration.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.keyboard_divider));
        recyclerView.addItemDecoration(decoration);
        DividerItemDecoration decoration2 = new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL);
        decoration2.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.keyboard_divider));
        recyclerView.addItemDecoration(decoration2);
    }

    protected void initKeyBoardItems(List<KeyEntry> items) {

    }

    public void setKeyBoardClickListener(KeyBoardListener keyBoardListener) {
        this.keyBoardListener = keyBoardListener;
    }
}
