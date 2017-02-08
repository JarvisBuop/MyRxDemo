package com.zjy.myrxdemo.data.source.local;

import com.google.gson.Gson;
import com.zjy.myrxdemo.component.injection.Injection;
import com.zjy.myrxdemo.data.model.User;
import com.zjy.myrxdemo.data.source.DataSource;
import com.zjy.myrxdemo.data.source.PersistenceContract;
import com.zjy.myrxdemo.data.source.local.db.AppDB;
import com.zjy.myrxdemo.data.source.local.db.AppDBDao;
import com.zjy.myrxdemo.data.source.local.db.DaoMaster;
import com.zjy.myrxdemo.data.source.local.db.DaoSession;
import com.zjy.myrxdemo.data.source.local.db.DbOpenHelper;

import org.greenrobot.greendao.query.QueryBuilder;

import rx.Observable;
import rx.Subscriber;



public class LocalDataSource implements DataSource {
    private static LocalDataSource instance = null;
    private final DaoSession mDaoSession;
    private LocalDataSource(){
        mDaoSession=new DaoMaster(new DbOpenHelper(Injection.provideContext(), PersistenceContract.AppDBEntry.DB_NAME).getWritableDatabase()).newSession();
    }

    public static LocalDataSource getInstance() {
        synchronized (LocalDataSource.class) {
            if (instance == null) {
                instance = new LocalDataSource();
            }
        }
        return instance;
    }
    @Override
    public Observable<User> getUser() {
        return Observable.create(new Observable.OnSubscribe<User>() {
            @Override
            public void call(Subscriber<? super User> subscriber) {
                QueryBuilder<AppDB> qb = mDaoSession.getAppDBDao().queryBuilder();
                AppDB unique = qb.where(AppDBDao.Properties.Key.eq(PersistenceContract.AppDBEntry.USER)).unique();
                User user = new Gson().fromJson(unique.getValue(), User.class);
                subscriber.onNext(user);
                subscriber.onCompleted();
            }
        });
    }

    @Override
    public Observable<Boolean> saveUser(final User user) {
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                AppDB appDB = new AppDB();
                appDB.setKey(PersistenceContract.AppDBEntry.USER);
                appDB.setValue(new Gson().toJson(user));
                long l = mDaoSession.getAppDBDao().insertOrReplace(appDB);
                if(l>0){
                    subscriber.onNext(true);
                }else {
                    subscriber.onError(new Exception("insert data failed"));
                }
                subscriber.onCompleted();
            }
        });
    }
}
