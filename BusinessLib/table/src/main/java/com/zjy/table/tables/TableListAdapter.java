package com.zjy.table.tables;

import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zjy.table.R;
import com.zjy.table.component.TableConstant;
import com.zjy.table.data.model.Table;

import java.util.List;

/**
 * Description:
 * Creator:zhou.junyou@puscene.com
 * Create by:Android Studio
 * Date:2017/4/26
 */
public class TableListAdapter extends BaseQuickAdapter<Table,BaseViewHolder> {
    public TableListAdapter(int layoutResId, List<Table> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Table item) {
        helper.setText(R.id.tv_table,item.getTableName());
        helper.setVisible(R.id.tv_table, !TextUtils.isEmpty(item.getTableName()));
        helper.getConvertView().setSelected(item.getTableStatus() == TableConstant.TableStatus.BUSY);
    }


}
