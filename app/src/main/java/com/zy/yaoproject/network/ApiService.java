package com.zy.yaoproject.network;


import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by muzi on 2018/04/10.
 * 727784430@qq.com
 */

public interface ApiService {

    @GET("/zy/user/get")
    Observable<ResponseBody> getTest(@Query("1") String state);

}
