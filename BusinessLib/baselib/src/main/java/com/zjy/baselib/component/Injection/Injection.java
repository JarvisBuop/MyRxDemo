package com.zjy.baselib.component.Injection;

import android.app.Application;
import android.content.Context;

import com.zjy.baselib.data.source.PersistenceContract;
import com.zjy.baselib.data.source.local.DaoMaster;
import com.zjy.baselib.data.source.local.DaoSession;
import com.zjy.baselib.data.source.local.DbOpenHelper;

public class Injection {
    protected static Context sContext;
    public static DaoSession sDaoSession;

   public  static void init(Application application){
       sContext = application;
       sDaoSession=new DaoMaster(new DbOpenHelper(sContext, PersistenceContract.AppDBEntry.DB_NAME).getWritableDatabase()).newSession();
   }

   public  static  Context provideContext(){
       return sContext;
   }

    public static DaoSession provideDaoSession(){
        return sDaoSession;
    }
}
