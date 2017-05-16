package com.zjy.table.tables;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zjy.table.R;
import com.zjy.table.component.TableInjection;
import com.zjy.table.data.model.Table;
import com.zjy.zlibrary.fragment.list.AbsListFragment;

import java.util.List;

import static com.zjy.table.component.TableConstant.TABLE_TYPE;

/**
 * Description:
 * Creator:zhou.junyou@puscene.com
 * Create by:Android Studio
 * Date:2017/4/26
 */
public class TableListFragment extends AbsListFragment implements TableListContract.View{
    public static final String TAG=TableListFragment.class.getSimpleName();
    protected TableListContract.Presenter mPresenter;

    public static TableListFragment newInstance(String type) {

        Bundle args = new Bundle();
        args.putString(TABLE_TYPE,type);
        TableListFragment fragment = new TableListFragment();
        new TableListPresenter(TableInjection.provideTableRepository(),fragment);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        getExtraData();
    }

    private void getExtraData() {
        if(getArguments()!=null){
            String tableType = getArguments().getString(TABLE_TYPE);
            mPresenter.setTableType(tableType);
        }
    }

    @Override
    protected void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        return new TableListAdapter(R.layout.item_table,getItems());
    }

    @Override
    public void loadData(int pageIndex) {
        mPresenter.loadData(pageIndex);
    }

    @Override
    public boolean hasMoreData() {
        return mPresenter.hasMoreData();
    }


    @Override
    protected void setUpLayoutManager(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
    }


    @Override
    protected void lazyInit() {
        super.lazyInit();
    }

    @Override
    public void setPresenter(TableListContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showTables(int page, List<Table> tables) {
        hideProgress();
        onDataSuccessReceived(page,tables);
    }


}
