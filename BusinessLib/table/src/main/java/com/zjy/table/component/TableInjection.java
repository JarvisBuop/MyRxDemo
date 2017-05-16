package com.zjy.table.component;

import com.zjy.table.data.source.TableRepository;
import com.zjy.table.data.source.local.TableLocalDataSource;
import com.zjy.table.data.source.remote.TableRemoteDataSource;

/**
 * Description:
 * Creator:zhou.junyou@puscene.com
 * Create by:Android Studio
 * Date:2017/4/26
 */
public class TableInjection {

    public static TableRepository provideTableRepository(){
        return TableRepository.getInstance(TableRemoteDataSource.getInstance(), TableLocalDataSource.getInstance());
    }
}
