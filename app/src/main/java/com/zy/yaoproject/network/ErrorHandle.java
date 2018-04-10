package com.zy.yaoproject.network;

import android.accounts.AccountsException;
import android.accounts.NetworkErrorException;

import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.zy.yaoproject.bean.BaseEntity;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import retrofit2.HttpException;

/**
 * Created by muzi on 2018/1/30.
 * 727784430@qq.com
 */

public class ErrorHandle {

    /**
     * 错误信息和错误码
     * 默认未知错误
     */
    private static int errorCode;
    private static String errorReason;

    public final static int SUCCESS = 200;

    public final static int NO_LOGIN = 201;
    public final static String NO_LOGIN_MSG = "请先登录";

    public final static int EXCEPTION_ERROR_NETWORK_CODE = 0x0001000;
    public final static String EXCEPTION_ERROR_NETWORK_MSG = "网络连接异常，请检查您的网络状态";

    public final static int EXCEPTION_ACCOUNTS = 0x0001001;
    public final static String EXCEPTION_ACCOUNTS_MSG = "账户异常";

    public final static int EXCEPTION_SOCKET = 0x0001002;
    public final static String EXCEPTION_SOCKET_MSG = "数据接收异常，请检查您的网络后重试";

    public final static int EXCEPTION_UNKNOWN_HOST = 0x0001003;
    public final static String EXCEPTION_UNKNOWN_HOST_MSG = "未知的网址";

    public final static int EXCEPTION_JSON_SYNTAX = 0x0001004;
    public final static String EXCEPTION_JSON_SYNTAX_MSG = "数据格式化异常";

    public final static int EXCEPTION_TIME_OUT = 0x0001005;
    public final static String EXCEPTION_TIME_OUT_MSG = "连接超时";

    public final static int EXCEPTION_CLASS_CAST = 0x0001006;
    public final static String EXCEPTION_CLASS_CAST_MSG = "类异常";

    public final static int EXCEPTION_OTHER_ERROR = 0x0001007;
    public final static String EXCEPTION_OTHER_ERROR_MSG = "未知错误";

    public static BaseEntity handleException(Throwable e) {

        errorCode = ErrorHandle.EXCEPTION_OTHER_ERROR;
        errorReason = ErrorHandle.EXCEPTION_OTHER_ERROR_MSG;

        if (e instanceof NetworkErrorException ||//网络异常--继承于AccountsException
                e instanceof ConnectException || //连接异常--继承于SocketException
                e instanceof HttpException) {
            errorReason = ErrorHandle.EXCEPTION_ERROR_NETWORK_MSG;
            errorCode = ErrorHandle.EXCEPTION_ERROR_NETWORK_CODE;
        } else if (e instanceof AccountsException) {//账户异常
            errorReason = ErrorHandle.EXCEPTION_ACCOUNTS_MSG;
            errorCode = ErrorHandle.EXCEPTION_ACCOUNTS;
        } else if (e instanceof SocketException) {//socket异常
            errorReason = ErrorHandle.EXCEPTION_SOCKET_MSG;
            errorCode = ErrorHandle.EXCEPTION_SOCKET;
        } else if (e instanceof UnknownHostException) {//DNS错误
            errorReason = ErrorHandle.EXCEPTION_UNKNOWN_HOST_MSG;
            errorCode = ErrorHandle.EXCEPTION_UNKNOWN_HOST;
        } else if (e instanceof JsonSyntaxException
                || e instanceof JsonIOException
                || e instanceof JsonParseException) {//数据格式化错误
            errorReason = ErrorHandle.EXCEPTION_JSON_SYNTAX_MSG;
            errorCode = ErrorHandle.EXCEPTION_JSON_SYNTAX;
        } else if (e instanceof SocketTimeoutException ||
                e instanceof TimeoutException) {//超时
            errorReason = ErrorHandle.EXCEPTION_TIME_OUT_MSG;
            errorCode = ErrorHandle.EXCEPTION_TIME_OUT;
        } else if (e instanceof ClassCastException) {//类异常
            errorReason = ErrorHandle.EXCEPTION_CLASS_CAST_MSG;
            errorCode = ErrorHandle.EXCEPTION_CLASS_CAST;
        }
        return new BaseEntity(errorReason, errorCode);
    }

}
