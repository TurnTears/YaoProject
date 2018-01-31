package com.zy.yaoproject.network;


import com.zy.yaoproject.entity.ClassifyEntity;
import com.zy.yaoproject.entity.Result;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by muzi on 2017/12/15.
 * 727784430@qq.com
 */

public interface ApiService {

    @GET("93-96")
    Observable<Result<ClassifyEntity>> getClassify();

}
