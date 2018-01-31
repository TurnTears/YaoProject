package com.zy.yaoproject.network;


/**
 * Created by muzi on 2017/10/20.
 * 727784430@qq.com
 */

public class RxRetrofit extends RetrofitHelper {

    public static ApiService getApi() {
        return createApi(ApiService.class);
    }

}
