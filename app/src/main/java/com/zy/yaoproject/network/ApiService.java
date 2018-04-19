package com.zy.yaoproject.network;


import com.zy.yaoproject.bean.AddCallBackBean;
import com.zy.yaoproject.bean.AllDataBean;
import com.zy.yaoproject.bean.BusNeedBean;
import com.zy.yaoproject.bean.BusTypeFund;
import com.zy.yaoproject.bean.LoginBean;
import com.zy.yaoproject.bean.LogisticsBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
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
     * 获取所有物品信息
     *
     * @return
     */
    @GET("/zy/system/getData")
    Observable<AllDataBean> getSupply();

    /**
     * 添加物品种类
     *
     * @param list
     * @return
     */
    @POST("/zy/system/addSystemData")
    Observable<AddCallBackBean> addNeed(@Body() List<BusTypeFund> list);

    /**
     * 提交物品需求
     *
     * @param list
     * @return
     */
    @POST("/zy/common/addCommonData")
    Observable<ResponseBody> commitNeed(@Body() List<BusNeedBean> list);


    /**
     * 后勤获取配送物品数据
     *
     * @return
     */
    @POST("/zy/common/getCommonData")
    Observable<LogisticsBean> getCommonData();
}
