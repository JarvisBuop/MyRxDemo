package com.zjy.baselib.data.source.local;

import com.google.gson.Gson;
import com.zjy.baselib.component.Injection.Injection;
import com.zjy.baselib.data.model.bean.User;
import com.zjy.baselib.data.source.PersistenceContract;

import org.greenrobot.greendao.query.QueryBuilder;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;


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
        return Observable.create(new ObservableOnSubscribe<User>() {
            @Override
            public void subscribe(ObservableEmitter<User> e) throws Exception {
                QueryBuilder<AppDB> qb = mDaoSession.getAppDBDao().queryBuilder();
                AppDB unique = qb.where(AppDBDao.Properties.Key.eq(PersistenceContract.AppDBEntry.USER)).unique();
                User user=new User();
                if (unique != null) {
                    user = new Gson().fromJson(unique.getValue(), User.class);
                }
                e.onNext(user);
                e.onComplete();
            }
        });

    }

    public Observable<Boolean> saveUser(final User user) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
                AppDB appDB = new AppDB();
                appDB.setKey(PersistenceContract.AppDBEntry.USER);
                appDB.setValue(new Gson().toJson(user));
                long l = mDaoSession.getAppDBDao().insertOrReplace(appDB);
                if (l > 0) {
                    e.onNext(true);
                } else {
                    e.onError(new Exception("insert data failed"));
                }
                e.onComplete();
            }
        });


    }


}
