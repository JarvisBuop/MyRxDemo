package com.zjy.myrxdemo.data.source.remote;

import android.graphics.Bitmap;

import com.blankj.utilcode.utils.ImageUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zjy.baselib.component.Injection.Injection;
import com.zjy.baselib.component.rx.ApiErrorOperator;
import com.zjy.baselib.data.model.NetWorkResponse;
import com.zjy.baselib.data.model.bean.SessionModel;
import com.zjy.baselib.data.model.bean.User;
import com.zjy.baselib.data.source.remote.RetrofitHelper;
import com.zjy.myrxdemo.data.model.login.ShopInfo;
import com.zjy.myrxdemo.data.model.login.bean.AdvModel;
import com.zjy.myrxdemo.data.model.login.bean.ConfigQRModel;
import com.zjy.myrxdemo.data.model.login.bean.LoginResponse;
import com.zjy.myrxdemo.data.model.login.bean.PayConfigModel;
import com.zjy.myrxdemo.data.model.login.bean.UnionConfigModel;
import com.zjy.myrxdemo.data.source.Constants;
import com.zjy.myrxdemo.data.source.DataSource;

import java.net.URLDecoder;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;


public class RemoteDataSource implements DataSource {
    private static RemoteDataSource instance = null;
    protected final MwService mMwService;

    public static RemoteDataSource getInstance() {
        synchronized (RemoteDataSource.class) {
            if (instance == null) {
                instance = new RemoteDataSource();
            }
        }
        return instance;
    }

    private RemoteDataSource() {
        mMwService = RetrofitHelper.getInstance().getApiService(MwService.HOST, MwService.class, null);
    }


    ///////////////////////////////////////////////////////////////////////////
    //        remote use
    ///////////////////////////////////////////////////////////////////////////

    @Override
    public Observable<LoginResponse> login(String UserName, String password, String deviceId, String V, String AP, String apiVersion) {
        return mMwService.getLoginResponse(UserName, password, deviceId, V, AP, apiVersion);
    }

    @Override
    public Observable<NetWorkResponse<PayConfigModel>> getPayConfig(String token, String apiVersion) {
        return mMwService.getPayConfigResponse(token, apiVersion);
    }

    @Override
    public Observable<NetWorkResponse<UnionConfigModel>> getUnionConfig(String token, String apiVersion) {
        return mMwService.getUnionConfigResponse(token, apiVersion);
    }

    @Override
    public Observable<Bitmap> getAdvBitmap(String token,String shopId, int businessId, String dimension, String apiVersion) {
        return mMwService.getAdvResopnse(token,shopId, businessId, dimension, apiVersion)
                .lift(new ApiErrorOperator<>())
                .flatMap(new Function<NetWorkResponse<AdvModel>, ObservableSource<Bitmap>>() {
                    @Override
                    public ObservableSource<Bitmap> apply(@NonNull NetWorkResponse<AdvModel> advModelNetWorkResponse) throws Exception {
                        return Observable.create(new ObservableOnSubscribe<Bitmap>() {
                            @Override
                            public void subscribe(ObservableEmitter<Bitmap> e) throws Exception {
                                Bitmap resource = Glide.with(Injection.provideContext())
                                        .load(URLDecoder.decode(advModelNetWorkResponse.data.image, "GB2312"))
                                        .asBitmap()
                                        .skipMemoryCache(true)
                                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                                        .into(500, 500)
                                        .get();
                                if (resource != null) {
                                    ImageUtils.save(resource, Constants.Advertisement.BITMAP_FILE_PATH, Bitmap.CompressFormat.JPEG);
                                    e.onNext(resource);
                                }
                                e.onComplete();


                            }
                        });

                    }
                });

    }

    @Override
    public Observable<NetWorkResponse<List<ConfigQRModel>>> getConfigQR(String token, String apiVersion) {
        return mMwService.getQRResponse(token, apiVersion);
    }

    @Override
    public Observable<NetWorkResponse<SessionModel>> refreshSessionId(String UserName, String Password, String DeviceID) {
        return null;
    }

    ///////////////////////////////////////////////////////////////////////////
    //                 local use
    ///////////////////////////////////////////////////////////////////////////
    @Override
    public Observable<ShopInfo> getShopInfo() {
        return null;
    }

    @Override
    public Observable<Boolean> saveShopInfo(ShopInfo shopInfo) {
        return null;
    }


    @Override
    public void saveSessionId(String sessionId) {

    }

    @Override
    public String getSessionId() {
        return null;
    }

    @Override
    public Observable<User> getUser() {
        return null;
    }

    @Override
    public Observable<Boolean> saveUser(User user) {
        return null;
    }

}
