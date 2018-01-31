package com.zy.yaoproject.network;

import android.util.ArrayMap;

import com.zy.yaoproject.app.App;
import com.zy.yaoproject.utils.NetUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hcc on 16/8/4 21:18
 * 100332338@qq.com
 * <p/>
 * Retrofit帮助类
 */
public class RetrofitHelper {

    private static ArrayMap<String, String> params = new ArrayMap<>();

    public static OkHttpClient mOkHttpClient;

    static {
        initOkHttpClient();

        params.put("showapi_appid", ApiContent.APPID);
        params.put("showapi_sign", ApiContent.SIGN);
    }

    /**
     * 根据传入的baseUrl，和api创建retrofit
     */
    protected static <T> T createApi(Class<T> clazz) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiContent.API_URL)
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(clazz);
    }


    /**
     * 初始化OKHttpClient,设置缓存,设置超时时间,设置打印日志,设置UA拦截器
     */
    private static void initOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (mOkHttpClient == null) {
            synchronized (RetrofitHelper.class) {
                if (mOkHttpClient == null) {
                    //设置Http缓存
                    Cache cache = new Cache(new File(App.getInstance().getCacheDir(), "HttpCache"), 1024 * 1024 * 10);
                    mOkHttpClient = new OkHttpClient.Builder()
                            .cache(cache)
                            .addInterceptor(interceptor)
                            .addNetworkInterceptor(new CacheInterceptor())
                            .retryOnConnectionFailure(true)
                            .connectTimeout(20, TimeUnit.SECONDS)
                            .writeTimeout(20, TimeUnit.SECONDS)
                            .readTimeout(20, TimeUnit.SECONDS)
                            .addInterceptor(new CustomInterceptor())
                            .build();
                }
            }
        }
    }

    /**
     * 添加公共参数
     */
    private static class CustomInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request oldRequest = chain.request();
            //如果公共请求参数不为空,则构建新的请求
            if (params != null) {
                Request.Builder newRequestBuilder = oldRequest.newBuilder();
                //GET请求则使用HttpUrl.Builder来构建
                if ("GET".equalsIgnoreCase(oldRequest.method())) {
                    HttpUrl.Builder httpUrlBuilder = oldRequest.url().newBuilder();
                    for (String key : params.keySet()) {
                        httpUrlBuilder.addQueryParameter(key, params.get(key));
                    }
                    newRequestBuilder.url(httpUrlBuilder.build());
                } else {
                    //如果原请求是表单请求
                    if (oldRequest.body() instanceof FormBody) {
                        FormBody.Builder formBodyBuilder = new FormBody.Builder();
                        for (String key : params.keySet()) {
                            formBodyBuilder.add(key, params.get(key));
                        }
                        FormBody oldFormBody = (FormBody) oldRequest.body();
                        int size = oldFormBody.size();
                        for (int i = 0; i < size; i++) {
                            formBodyBuilder.add(oldFormBody.name(i), oldFormBody.value(i));
                        }
                        newRequestBuilder.post(formBodyBuilder.build());
                    }
                }
                return chain.proceed(newRequestBuilder.build());
            }
            return chain.proceed(oldRequest);
        }
    }

    /**
     * 为okhttp添加缓存，这里是考虑到服务器不支持缓存时，从而让okhttp支持缓存
     */
    private static class CacheInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            // 有网络时 设置缓存超时时间1个小时
            int maxAge = 60 * 60;
            // 无网络时，设置超时为1天
            int maxStale = 60 * 60 * 24;
            Request request = chain.request();
            if (NetUtils.isNetworkAvailable(App.getInstance())) {
                //有网络时只从网络获取
                request = request.newBuilder().cacheControl(CacheControl.FORCE_NETWORK).build();
            } else {
                //无网络时只从缓存中读取
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            }
            Response response = chain.proceed(request);
            if (NetUtils.isNetworkAvailable(App.getInstance())) {
                response = response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            } else {
                response = response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
            return response;
        }
    }

}
