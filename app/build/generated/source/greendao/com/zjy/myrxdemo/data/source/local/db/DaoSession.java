package com.zjy.myrxdemo.data.source.local.db;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.zjy.myrxdemo.data.source.local.db.AppDB;

import com.zjy.myrxdemo.data.source.local.db.AppDBDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig appDBDaoConfig;

    private final AppDBDao appDBDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        appDBDaoConfig = daoConfigMap.get(AppDBDao.class).clone();
        appDBDaoConfig.initIdentityScope(type);

        appDBDao = new AppDBDao(appDBDaoConfig, this);

        registerDao(AppDB.class, appDBDao);
    }
    
    public void clear() {
        appDBDaoConfig.clearIdentityScope();
    }

    public AppDBDao getAppDBDao() {
        return appDBDao;
    }

}
