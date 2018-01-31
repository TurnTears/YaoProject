package com.zy.yaoproject.network;

/**
 * Created by muzi on 2018/1/30.
 * 727784430@qq.com
 */

public class CodeContent {

    /**
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
     */

    public final static int SUCCESS = 0;//成功

    public final static int EROOR1 = -1;
    public final static String EROOR1_MSG = "系统调用错误";

    public final static int EROOR2 = -2;
    public final static String EROOR2_MSG = "可调用次数或金额为0";

    public final static int EROOR3 = -3;
    public final static String EROOR3_MSG = "读取超时";

    public final static int EROOR4 = -4;
    public final static String EROOR4_MSG = "服务端返回数据解析错误";

    public final static int EROOR5 = -5;
    public final static String EROOR5_MSG = "后端服务器DNS解析错误";

    public final static int EROOR1000 = -1000;
    public final static String EROOR1000_MSG = "系统维护";

    public final static int EROOR6 = -6;
    public final static String EROOR6_MSG = "服务不存在或未上线";

    public final static int EROOR1002 = -1002;
    public final static String EROOR1002_MSG = "showapi_appid字段缺失";

    public final static int EROOR1003 = -1003;
    public final static String EROOR1003_MSG = "showapi_sign字段缺失";

    public final static int EROOR1004 = -1004;
    public final static String EROOR1004_MSG = "签名sign验证有误";

    public final static int EROOR1005 = -1005;
    public final static String EROOR1005_MSG = "showapi_timestamp无效";

    public final static int EROOR1006 = -1006;
    public final static String EROOR1006_MSG = "app无权限调用接口";

    public final static int EROOR1007 = -1007;
    public final static String EROOR1007_MSG = "没有订购套餐";

    public final static int EROOR1008 = -1008;
    public final static String EROOR1008_MSG = "服务商关闭对您的调用权限";

    public final static int EROOR1009 = -1009;
    public final static String EROOR1009_MSG = "调用频率受限";

    public final static int EROOR1010 = -1010;
    public final static String EROOR1010_MSG = "找不到您的应用";

    public final static int EROOR1011 = -1011;
    public final static String EROOR1011_MSG = "子授权app_child_id无效";

    public final static int EROOR1012 = -1012;
    public final static String EROOR1012_MSG = "子授权已过期或失效";

    public final static int EROOR1013 = -1013;
    public final static String EROOR1013_MSG = "子授权ip受限";

    public final static int EXCEPTION_NETWORK_NOT_CONNECTED = 0x0001000;
    public final static String EXCEPTION_NETWORK_NOT_CONNECTED_MSG = "网络连接异常，请检查您的网络状态";

    public final static int EXCEPTION_NETWORK_ERROR = 0x0001001;
    public final static String EXCEPTION_NETWORK_ERROR_MSG = "网络发生错误，请检查您的网络后重试";

    public final static int EXCEPTION_ACCOUNTS = 0x0001002;
    public final static String EXCEPTION_ACCOUNTS_MSG = "账户异常";

    public final static int EXCEPTION_CONNECT = 0x0001003;
    public final static String EXCEPTION_CONNECT_MSG = "网络连接异常，请检查您的网络后重试";

    public final static int EXCEPTION_SOCKET = 0x0001004;
    public final static String EXCEPTION_SOCKET_MSG = "数据接收异常，请检查您的网络后重试";

    public final static int EXCEPTION_HTTP = 0x0001005;
    public final static String EXCEPTION_HTTP_MSG = "网络连接异常，请检查您的网络后重试";

    public final static int EXCEPTION_UNKNOWN_HOST = 0x0001006;
    public final static String EXCEPTION_UNKNOWN_HOST_MSG = "未知的网址";

    public final static int EXCEPTION_JSON_SYNTAX = 0x0001007;
    public final static String EXCEPTION_JSON_SYNTAX_MSG = "数据格式化异常";

    public final static int EXCEPTION_TIME_OUT = 0x0001008;
    public final static String EXCEPTION_TIME_OUT_MSG = "连接超时";

    public final static int EXCEPTION_CLASS_CAST = 0x0001009;
    public final static String EXCEPTION_CLASS_CAST_MSG = "类异常";

    public final static int EXCEPTION_OTHER_ERROR = 0x0001010;
    public final static String EXCEPTION_OTHER_ERROR_MSG = "未知错误";

}
