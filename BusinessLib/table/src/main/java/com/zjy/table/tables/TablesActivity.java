package com.zjy.table.tables;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zjy.zlibrary.fragment.fragmentation.SupportActivity;
@Route(path = "/table/tables_activity")
public class TablesActivity extends SupportActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadRootFragment(android.R.id.content, TablesFragment.newInstance());
    }
}
