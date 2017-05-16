package com.zjy.table.tables;

import com.zjy.table.data.model.Table;
import com.zjy.zlibrary.base.BasePresenter;
import com.zjy.zlibrary.base.BaseView;

import java.util.List;

/**
 * Description:
 * Creator:zhou.junyou@puscene.com
 * Create by:Android Studio
 * Date:2017/4/26
 */
public interface TableListContract {
    interface View extends BaseView<Presenter> {

        void showTables(int page, List<Table> tables);

        void showError(String message);

    }

    interface Presenter extends BasePresenter {

        void loadData(int pageIndex);

        boolean hasMoreData();

        void setTableType(String type);
    }
}
