package com.zjy.table.tables;

import com.zjy.table.data.model.Table;
import com.zjy.table.data.source.TableRepository;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Description:
 * Creator:zhou.junyou@puscene.com
 * Create by:Android Studio
 * Date:2017/4/26
 */
public class TableListPresenter implements TableListContract.Presenter {
    private CompositeDisposable mCompositeDisposable;
    private TableRepository mTableRepository;
    private TableListContract.View mView;
    /**
     * 当前页数
     */
    public int currentPage = 0;
    /**
     * 下一页的查询游标
     */
    private int nextPageIndex = 0;
    /**
     * 是否还有更多订单
     */
    public boolean hasMoreOrders = false;
    /**
     * 桌子区域
     */
    protected String tableType;

    public TableListPresenter(TableRepository tableRepository, TableListContract.View view) {
        mTableRepository = tableRepository;
        mView = view;
        mCompositeDisposable=new CompositeDisposable();
        mView.setPresenter(this);
    }

    @Override
    public void loadData(int pageIndex) {
        currentPage=pageIndex;
        DisposableObserver<List<Table>> observer = mTableRepository.getTables(tableType, pageIndex)
                .subscribeWith(new DisposableObserver<List<Table>>() {


                    @Override
                    public void onNext(List<Table> tables) {
                        mView.showTables(pageIndex,tables);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        mCompositeDisposable.add(observer);
    }

    @Override
    public boolean hasMoreData() {
        return hasMoreOrders;
    }

    @Override
    public void setTableType(String type) {
        tableType = type;
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        mCompositeDisposable.clear();
    }
}
