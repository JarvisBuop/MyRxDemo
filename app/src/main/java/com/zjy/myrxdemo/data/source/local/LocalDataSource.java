package com.zjy.myrxdemo.data.source.local;

import com.google.gson.Gson;
import com.zjy.myrxdemo.component.injection.Injection;
import com.zjy.myrxdemo.data.model.BaseResponse;
import com.zjy.myrxdemo.data.model.login.ShopInfo;
import com.zjy.myrxdemo.data.model.login.User;
import com.zjy.myrxdemo.data.model.login.bean.LoginResponse;
import com.zjy.myrxdemo.data.model.login.bean.PayConfigModel;
import com.zjy.myrxdemo.data.model.login.bean.UnionConfigModel;
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
    public Observable<Boolean> saveSessionId(final String sessionId) {
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                AppDB appDB = new AppDB();
                appDB.setKey(PersistenceContract.AppDBEntry.SESSION_ID);
                appDB.setValue(sessionId);
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

    @Override
    public Observable<String> getSessionId() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                QueryBuilder<AppDB> qb = mDaoSession.getAppDBDao().queryBuilder();
                AppDB unique = qb.where(AppDBDao.Properties.Key.eq(PersistenceContract.AppDBEntry.SESSION_ID)).unique();
                subscriber.onNext(unique.getValue());
                subscriber.onCompleted();
            }
        });
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

    @Override
    public Observable<ShopInfo> getShopInfo() {
        return Observable.create(new Observable.OnSubscribe<ShopInfo>() {
            @Override
            public void call(Subscriber<? super ShopInfo> subscriber) {
                QueryBuilder<AppDB> qb = mDaoSession.getAppDBDao().queryBuilder();
                AppDB unique = qb.where(AppDBDao.Properties.Key.eq(PersistenceContract.AppDBEntry.SHOP_INFO)).unique();
                ShopInfo shopInfo = new Gson().fromJson(unique.getValue(), ShopInfo.class);
                subscriber.onNext(shopInfo);
                subscriber.onCompleted();
            }
        });
    }

    @Override
    public Observable<Boolean> saveShopInfo(final ShopInfo shopInfo) {
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                AppDB appDB = new AppDB();
                appDB.setKey(PersistenceContract.AppDBEntry.SHOP_INFO);
                appDB.setValue(new Gson().toJson(shopInfo));
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

    @Override
    public Observable<LoginResponse> login(String UserName, String password, String deviceId, String V, String AP, String apiVersion) {
        return null;
    }

    @Override
    public Observable<BaseResponse<PayConfigModel>> getPayConfig(String token, String apiVersion) {
        return null;
    }

    @Override
    public Observable<BaseResponse<UnionConfigModel>> getUnionConfig(String token, String apiVersion) {
        return null;
    }


}
