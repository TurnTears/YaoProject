package com.zy.yaoproject.network;


import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by muzi on 2017/12/15.
 * 727784430@qq.com
 * <p>
 * 药品信息
 * https://www.showapi.com/api/view/93/33
 * https://www.showapi.com/api/view/1468/1
 * <p>
 * 健康咨询
 * https://www.showapi.com/api/lookPoint/96
 * <p>
 * 疾病查询
 * https://www.showapi.com/api/lookPoint/546
 * <p>
 * 健康知识
 * https://www.showapi.com/api/lookPoint/90
 * <p>
 * <p>
 * <p>状态码
 * 0，成功
 * -1，系统调用错误
 * -2，可调用次数或金额为0
 * -3，读取超时
 * -4，服务端返回数据解析错误
 * -5，后端服务器DNS解析错误
 * -6，服务不存在或未上线
 * -1000，系统维护
 * -1002，showapi_appid字段必传
 * -1003，showapi_sign字段必传
 * -1004，签名sign验证有误
 * -1005，showapi_timestamp无效
 * -1006，app无权限调用接口
 * -1007，没有订购套餐
 * -1008，服务商关闭对您的调用权限
 * -1009，调用频率受限
 * -1010，找不到您的应用
 * -1011，子授权app_child_id无效
 * -1012，子授权已过期或失效
 * -1013，子授权ip受限
 * <p>
 */


public interface ApiService {

    /**
     * 药品分类信息
     *
     * @return
     */
    @GET("1468-1")
    Observable<ResponseBody> getClassify();


    /**
     * 药品分类下的相关药品的简单信息
     *
     * @param classifyId-药品分类Id
     * @param page-页码（可选）
     * @param maxResult-当前页最大返回条目数（可选）
     * @return
     */
    @GET("1468-2")
    Observable<ResponseBody> getClassInfo(@Query("classifyId") String classifyId,
                                          @Query("page") int page,
                                          @Query("maxResult") String maxResult);

    /**
     * 药品详情，根据药品Id，药品名称，药企信息，药准字号查询药品详细信息
     *
     * @param classifyId-药品分类Id
     * @param searchType-searchType搜索关键字的类型:1、药品名称 2、药企名称 3、药准字号 4、药品Id
     * @param searchKey-查询的关键字
     * @param page-页码（可选）
     * @return
     */
    @GET("1468-3")
    Observable<ResponseBody> getDrugDetail(@Query("classifyId") String classifyId,
                                           @Query("searchType") String searchType,
                                           @Query("searchKey") String searchKey,
                                           @Query("page") int page);

}
