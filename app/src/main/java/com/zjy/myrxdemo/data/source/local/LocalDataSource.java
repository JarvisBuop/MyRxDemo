package com.zjy.myrxdemo.data.source.local;

import com.google.gson.Gson;
import com.zjy.baselib.component.Injection.Injection;
import com.zjy.baselib.data.model.NetWorkResponse;
import com.zjy.baselib.data.model.bean.SessionModel;
import com.zjy.baselib.data.model.bean.User;
import com.zjy.baselib.data.source.PersistenceContract;
import com.zjy.baselib.data.source.local.AppDB;
import com.zjy.baselib.data.source.local.AppDBDao;
import com.zjy.baselib.data.source.local.BaseLocalDataSource;
import com.zjy.baselib.data.source.local.DaoSession;
import com.zjy.baselib.data.source.local.PreferencesManager;
import com.zjy.myrxdemo.data.model.login.ShopInfo;
import com.zjy.myrxdemo.data.model.login.bean.AdvModel;
import com.zjy.myrxdemo.data.model.login.bean.ConfigQRModel;
import com.zjy.myrxdemo.data.model.login.bean.LoginResponse;
import com.zjy.myrxdemo.data.model.login.bean.PayConfigModel;
import com.zjy.myrxdemo.data.model.login.bean.UnionConfigModel;
import com.zjy.myrxdemo.data.source.DataSource;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import io.reactivex.Observable;


public class LocalDataSource implements DataSource {
    private static LocalDataSource instance = null;
    private final DaoSession mDaoSession;
    private LocalDataSource(){
        mDaoSession= Injection.provideDaoSession();
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
    public void saveSessionId(String sessionId) {
         PreferencesManager.saveSessionId(sessionId);
    }

    @Override
    public String getSessionId() {
        return PreferencesManager.getSessionId();
    }

    @Override
    public Observable<User> getUser() {
        return BaseLocalDataSource.getInstance().getUser();
    }

    @Override
    public Observable<Boolean> saveUser(final User user) {
        return BaseLocalDataSource.getInstance().saveUser(user);
    }

    @Override
    public Observable<ShopInfo> getShopInfo() {
        return Observable.create(e -> {
            QueryBuilder<AppDB> qb = mDaoSession.getAppDBDao().queryBuilder();
            AppDB unique = qb.where(AppDBDao.Properties.Key.eq(PersistenceContract.AppDBEntry.SHOP_INFO)).unique();
            ShopInfo shopInfo = new Gson().fromJson(unique.getValue(), ShopInfo.class);
            e.onNext(shopInfo);
            e.onComplete();
        });

    }

    @Override
    public Observable<Boolean> saveShopInfo(final ShopInfo shopInfo) {
        return Observable.create(e -> {
            AppDB appDB = new AppDB();
            appDB.setKey(PersistenceContract.AppDBEntry.SHOP_INFO);
            appDB.setValue(new Gson().toJson(shopInfo));
            long l = mDaoSession.getAppDBDao().insertOrReplace(appDB);
            if(l>0){
                e.onNext(true);
            }else {
                e.onError(new Exception("insert data failed"));
            }
            e.onComplete();
        });
    }

    @Override
    public Observable<LoginResponse> login(String UserName, String password, String deviceId, String V, String AP, String apiVersion) {
        return null;
    }

    @Override
    public Observable<NetWorkResponse<PayConfigModel>> getPayConfig(String token, String apiVersion) {
        return null;
    }

    @Override
    public Observable<NetWorkResponse<UnionConfigModel>> getUnionConfig(String token, String apiVersion) {
        return null;
    }

    @Override
    public Observable<NetWorkResponse<AdvModel>> getAdvUrl(String token, int businessId, String dimension, String apiVersion) {
        return null;
    }

    @Override
    public Observable<NetWorkResponse<List<ConfigQRModel>>> getConfigQR(String token, String apiVersion) {
        return null;
    }

    @Override
    public Observable<NetWorkResponse<SessionModel>> refreshSessionId(String UserName, String Password, String DeviceID) {
        return null;
    }


}
