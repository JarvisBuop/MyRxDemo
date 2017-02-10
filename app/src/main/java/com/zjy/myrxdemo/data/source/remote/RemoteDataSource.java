package com.zjy.myrxdemo.data.source.remote;

import com.blankj.utilcode.utils.NetworkUtils;
import com.zjy.myrxdemo.component.injection.Injection;
import com.zjy.myrxdemo.data.model.BaseResponse;
import com.zjy.myrxdemo.data.model.login.ShopInfo;
import com.zjy.myrxdemo.data.model.login.User;
import com.zjy.myrxdemo.data.model.login.bean.LoginResponse;
import com.zjy.myrxdemo.data.model.login.bean.PayConfigModel;
import com.zjy.myrxdemo.data.model.login.bean.UnionConfigModel;
import com.zjy.myrxdemo.data.source.DataSource;
import com.zjy.zlibrary.gsonconverter.GsonConverterFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;

public class RemoteDataSource implements DataSource {
    private static RemoteDataSource instance = null;
    protected final MwService mMwService;
    protected OkHttpClient mOkHttpClient;

    public static RemoteDataSource getInstance() {
        synchronized (RemoteDataSource.class) {
            if (instance == null) {
                instance = new RemoteDataSource();
            }
        }
        return instance;
    }

    private RemoteDataSource(){
        initOkHttp();
        mMwService = getApiService(MwService.HOST,MwService.class);
    }

    private void initOkHttp() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        File cacheFile = new File(Injection.provideContext().getCacheDir().getAbsolutePath());
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!NetworkUtils.isConnected()) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);
                if (NetworkUtils.isConnected()) {
                    int maxAge = 0;
                    // 有网络时, 不缓存, 最大保存时长为0
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader("Pragma")
                            .build();
                } else {
                    // 无网络时，设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }
                return response;
            }
        };
        //设置缓存
        builder.addNetworkInterceptor(cacheInterceptor);
        builder.addInterceptor(cacheInterceptor);
        builder.cache(cache);
        //设置超时
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        //错误重连
       // builder.retryOnConnectionFailure(true);
        mOkHttpClient = builder.build();
    }


    private <T> T getApiService(String baseUrl, Class<T> clz) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(clz);
    }


    ///////////////////////////////////////////////////////////////////////////
    //        remote use
    ///////////////////////////////////////////////////////////////////////////

    @Override
    public Observable<LoginResponse> login(String UserName, String password, String deviceId, String V, String AP, String apiVersion) {
        return mMwService.getLoginResponse(UserName,password,deviceId,V,AP,apiVersion);
    }

    @Override
    public Observable<BaseResponse<PayConfigModel>> getPayConfig(String token, String apiVersion) {
        return mMwService.getPayConfigResponse(token,apiVersion);
    }

    @Override
    public Observable<BaseResponse<UnionConfigModel>> getUnionConfig(String token, String apiVersion) {
        return mMwService.getUnionConfigResponse(token,apiVersion);
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
    public Observable<Boolean> saveSessionId(String sessionId) {
        return null;
    }

    @Override
    public Observable<String> getSessionId() {
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
