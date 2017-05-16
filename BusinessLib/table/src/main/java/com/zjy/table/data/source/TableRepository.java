package com.zjy.table.data.source;

import com.zjy.table.data.model.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

/**
 * Description:
 * Creator:zhou.junyou@puscene.com
 * Create by:Android Studio
 * Date:2017/4/26
 */
public class TableRepository implements TableDataSource {
    private static TableRepository INSTANCE = null;

    private final TableDataSource remoteTableDataSource;

    private final TableDataSource localTableDataSource;

    Map<String ,List<Table>> mTableCaches;

    public TableRepository(TableDataSource remoteTableDataSource, TableDataSource localTableDataSource) {
        this.remoteTableDataSource = remoteTableDataSource;
        this.localTableDataSource = localTableDataSource;
    }


    public static TableRepository getInstance(TableDataSource remoteTableDataSource,TableDataSource localTableDataSource) {
        synchronized (TableRepository.class) {
            if (INSTANCE == null) {
                INSTANCE = new TableRepository(remoteTableDataSource,localTableDataSource);
            }
        }
        return INSTANCE;
    }

    public Observable<List<Table>> getTables(String tableType, int pageIndex) {
        Table table = new Table();
        table.setTableName(tableType);
        ArrayList<Table> tables = new ArrayList<>();
        tables.add(table);
        return Observable.just(tables);
        /*if(mTableCaches!=null){
           return Observable.just(mTableCaches.get(tableType));
        }
        return null;*/
    }
}

