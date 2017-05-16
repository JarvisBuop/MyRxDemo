package com.zjy.zlibrary.fragment.list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.zjy.zlibrary.R;
import com.zjy.zlibrary.dialog.Progress;
import com.zjy.zlibrary.fragment.fragmentation.SupportFragment;
import com.zjy.zlibrary.widget.StatusViewLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * Created with android studio
 * Creator:zhou.junyou@puscene.com
 * DATE:16/12/22
 * TIME:上午11:01 * DESC:
 */

public abstract class AbsListFragment extends SupportFragment implements IList {
    public static final String TAG = AbsListFragment.class.getSimpleName();
    protected View mContentView;
    protected StatusViewLayout mStatusLayout;

    protected int mCurrentPageIndex;
    private List mItems;
    protected BaseQuickAdapter mAdapter;


    protected SwipeRefreshLayout swipeLayout;
    protected RecyclerView recyclerView;
    protected Progress progress;
    protected boolean mRefreshEnable = true;
    protected boolean mLoadMoreEnable = true;
    protected boolean mInit = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContentView = inflater.inflate(R.layout.fragment_base_list, container, false);
        return mContentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mStatusLayout = ((StatusViewLayout) view.findViewById(R.id.status_view));
        swipeLayout = ((SwipeRefreshLayout) view.findViewById(R.id.swipe_layout));
        recyclerView = ((RecyclerView) view.findViewById(R.id.rv_list));
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mAdapter.removeAllFooterView();
                refreshData();
            }
        });
        setUpLayoutManager(recyclerView);

        init();
    }

    protected void setUpLayoutManager(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void init() {
        mCurrentPageIndex = getInitPageIndex();
        mItems = new ArrayList<>();
        mAdapter = getAdapter();
        if (mLoadMoreEnable) {
            mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    loadMore();
                }
            });
        }
        mStatusLayout.setOnRetryListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRetry(v);
            }
        });
        // mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                AbsListFragment.this.onItemClick(adapter, view, position);

            }
        });
        swipeLayout.setEnabled(mRefreshEnable);
        customConfig();

    }

    /*
    * 重试
    * */
    protected void onRetry(View v) {
        normalLoad(mCurrentPageIndex);
    }

    private void normalLoad(int index) {
        if (!mInit) return;
        //ProgressManager.showProgress(this,R.string.loading);
        loadData(index);
    }

    protected abstract void onItemClick(BaseQuickAdapter adapter, View view, int position);

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser && isVisible()) {
            lazyInit();
        }
        super.setUserVisibleHint(isVisibleToUser);

    }

    protected void lazyInit() {
        showProgress();
        normalLoad(getInitPageIndex());
    }


    @Override
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint()) {
            lazyInit();
        }
    }

    protected void customConfig() {
    }


    protected abstract BaseQuickAdapter getAdapter();

    /**
     * 得到当前列表数据
     *
     * @return 当前列表数据
     */
    protected final List getItems() {
        return mItems;
    }


    @Override
    public void refreshData() {
        mCurrentPageIndex = getInitPageIndex();
        loadData(getInitPageIndex());
    }

    @Override
    public void loadMore() {
        if (hasMoreData()) {
            loadData(++mCurrentPageIndex);
        } else {
            recyclerView.post(() -> mAdapter.loadMoreEnd());
        }
    }

    protected boolean hasMoreData() {
        return true;
    }

    @Override
    public void showError(String message) {
        if (mCurrentPageIndex == getInitPageIndex()) {
            mStatusLayout.showError(message);
        } else {
            recyclerView.post(() -> mAdapter.loadMoreFail());
        }
        swipeLayout.setRefreshing(false);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showEmpty(String msg) {
        mStatusLayout.showEmpty(msg);
    }

    @Override
    public void showContent() {
        mStatusLayout.showContent();
        recyclerView.post(() -> {
            swipeLayout.setRefreshing(false);
            mAdapter.loadMoreComplete();
        });
    }


    @Override
    public abstract void loadData(int pageIndex);


    /**
     * 列表数据接收成功时调用（相关的实现类需要手动去调用此方法）
     *
     * @param pageIndex 当前请求的页数
     * @param items     返回的数据
     */
    protected final void onDataSuccessReceived(int pageIndex, List items) {
        showContent();
        if (pageIndex == getInitPageIndex() && items.size() <= 0) {//无数据
            showEmpty(getEmptyMsg());
        } else if (pageIndex == getInitPageIndex()) {//刷新
            mItems.clear();
            mItems.addAll(items);
        } else if (items != null && items.size() != 0) {//加载更多
            mItems.addAll(items);
        } else {//没有更多数据了
            mCurrentPageIndex--;
        }
        mAdapter.setNewData(mItems);
    }

    protected int getInitPageIndex() {
        return 0;
    }

    protected void controlLoadData(boolean refresh, boolean loadMore, boolean init) {
        mRefreshEnable = refresh;
        mLoadMoreEnable = loadMore;
        mInit = init;
    }


    @NonNull
    protected String getEmptyMsg() {
        return "暂无搜索结果";
    }


}
