package com.zjy.baselib.data.source.local;

import com.google.gson.Gson;
import com.zjy.baselib.component.Injection.Injection;
import com.zjy.baselib.data.model.bean.User;
import com.zjy.baselib.data.source.PersistenceContract;

import org.greenrobot.greendao.query.QueryBuilder;

import rx.Observable;
import rx.Subscriber;

public class BaseLocalDataSource {
    private static BaseLocalDataSource INSTANCE = null;
    private final DaoSession mDaoSession;

    private BaseLocalDataSource() {
        mDaoSession = Injection.provideDaoSession();
    }

    public static BaseLocalDataSource getInstance() {
        synchronized (BaseLocalDataSource.class) {
            if (INSTANCE == null) {
                INSTANCE = new BaseLocalDataSource();
            }
        }
        return INSTANCE;
    }

    public Observable<User> getUser() {
        return Observable.create(new Observable.OnSubscribe<User>() {
            @Override
            public void call(Subscriber<? super User> subscriber) {
                QueryBuilder<AppDB> qb = mDaoSession.getAppDBDao().queryBuilder();
                AppDB unique = qb.where(AppDBDao.Properties.Key.eq(PersistenceContract.AppDBEntry.USER)).unique();
                User user=null;
                if (unique != null) {
                     user = new Gson().fromJson(unique.getValue(), User.class);
                }
                subscriber.onNext(user);
                subscriber.onCompleted();
            }
        });
    }

    public Observable<Boolean> saveUser(final User user) {
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                AppDB appDB = new AppDB();
                appDB.setKey(PersistenceContract.AppDBEntry.USER);
                appDB.setValue(new Gson().toJson(user));
                long l = mDaoSession.getAppDBDao().insertOrReplace(appDB);
                if (l > 0) {
                    subscriber.onNext(true);
                } else {
                    subscriber.onError(new Exception("insert data failed"));
                }
                subscriber.onCompleted();
            }
        });
    }


}
