package com.zy.yaoproject.network;


import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

/**
 * Created by muzi on 2017/12/15.
 * 727784430@qq.com
 */

public interface ApiService {

    @GET("93-96")
    Observable<ResponseBody> getClassify();
}
