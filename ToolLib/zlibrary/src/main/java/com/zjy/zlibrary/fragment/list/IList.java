package com.zjy.zlibrary.fragment.list;

public interface IList {

    void loadData(int pageIndex);

    void refreshData();

    void loadMore();

    void showError(String message);

    void showLoading();

    void showEmpty(String msg);

    void showContent();
}