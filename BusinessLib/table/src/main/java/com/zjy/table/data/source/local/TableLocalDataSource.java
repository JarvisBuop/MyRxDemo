package com.zjy.table.data.source.local;

import com.zjy.table.data.source.TableDataSource;

/**
 * Description:
 * Creator:zhou.junyou@puscene.com
 * Create by:Android Studio
 * Date:2017/4/26
 */
public class TableLocalDataSource implements TableDataSource {
    private static TableLocalDataSource INSTANCE = null;

    private TableLocalDataSource(){
    }

    public static TableLocalDataSource getInstance() {
        synchronized (TableLocalDataSource.class) {
            if (INSTANCE == null) {
                INSTANCE = new TableLocalDataSource();
            }
        }
        return INSTANCE;
    }
}
