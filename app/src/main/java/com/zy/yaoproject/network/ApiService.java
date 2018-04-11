package com.zy.yaoproject.network;


import com.zy.yaoproject.bean.AllDataBean;
import com.zy.yaoproject.bean.LoginBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by muzi on 2018/04/10.
 * 727784430@qq.com
 */

public interface ApiService {

    /**
     * 登录
     *
     * @param loginName
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("/zy/user/login")
    Observable<LoginBean> login(@Field("loginName") String loginName, @Field("password") String password);

    /**
     * 退出
     *
     * @return
     */
    @GET("/zy/user/logout")
    Observable<ResponseBody> logout();

    /**
     * 获取补给信息
     *
     * @return
     */
    @GET("/zy/system/getData")
    Observable<AllDataBean> getSupply();

}
