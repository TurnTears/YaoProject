package com.zy.yaoproject.observer;

import android.accounts.AccountsException;
import android.accounts.NetworkErrorException;
import android.content.Context;

import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.zy.yaoproject.entity.Result;
import com.zy.yaoproject.network.CodeContent;
import com.zy.yaoproject.utils.NetUtils;

import org.json.JSONObject;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.util.EndConsumerHelper;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

/**
 * Created by muzi on 2017/12/15.
 * 727784430@qq.com
 */

public abstract class BaseJsonObserver implements Observer<ResponseBody> {

    final AtomicReference<Disposable> s = new AtomicReference<Disposable>();

    private Context context;
    private JSONObject jsonObject;
    private JSONObject showapi_res_body;
    private int showapi_res_code;
    private String showapi_res_error;

    public BaseJsonObserver(Context context) {
        this.context = context;
    }

    @Override
    public final void onSubscribe(@NonNull Disposable s) {
        if (EndConsumerHelper.setOnce(this.s, s, getClass())) {
            if (!NetUtils.isNetworkAvailable(context)) {
                onError(new Result(CodeContent.EXCEPTION_NETWORK_NOT_CONNECTED, CodeContent.EXCEPTION_NETWORK_NOT_CONNECTED_MSG));
                s.dispose();
                return;
            }
            onStart();
        }
    }

    public void onStart() {
    }

    public void onError(@NonNull Result result) {
    }

    public void onSuccess(@NonNull JSONObject jsonObject) {
    }

    @Override
    public void onComplete() {
    }

    @Override
    public final void onNext(@NonNull ResponseBody responseBody) {
        try {
            jsonObject = new JSONObject(responseBody.string());
            showapi_res_code = jsonObject.optInt("showapi_res_code");
            showapi_res_error = jsonObject.optString("showapi_res_error");
            showapi_res_body = jsonObject.optJSONObject("showapi_res_body");
        } catch (Exception e) {
            e.printStackTrace();
        }
        switch (showapi_res_code) {
            case CodeContent.SUCCESS:
                //成功
                onSuccess(showapi_res_body);
                break;
            default:
                onError(new Result(showapi_res_code, showapi_res_error));
                onComplete();
                break;
        }
    }

    @Override
    public void onError(@NonNull Throwable e) {
        e.printStackTrace();
        String reason = CodeContent.EXCEPTION_OTHER_ERROR_MSG;
        int code = CodeContent.EXCEPTION_OTHER_ERROR;

        if (!NetUtils.isNetworkAvailable(context)) {//网络断开
            reason = CodeContent.EXCEPTION_NETWORK_NOT_CONNECTED_MSG;
            code = CodeContent.EXCEPTION_NETWORK_NOT_CONNECTED;
        } else {
            if (e instanceof NetworkErrorException) {//网络异常--继承于AccountsException
                reason = CodeContent.EXCEPTION_NETWORK_ERROR_MSG;
                code = CodeContent.EXCEPTION_NETWORK_ERROR;
            } else if (e instanceof AccountsException) {//账户异常
                reason = CodeContent.EXCEPTION_ACCOUNTS_MSG;
                code = CodeContent.EXCEPTION_ACCOUNTS;
            } else if (e instanceof ConnectException) {//连接异常--继承于SocketException
                reason = CodeContent.EXCEPTION_CONNECT_MSG;
                code = CodeContent.EXCEPTION_CONNECT;
            } else if (e instanceof SocketException) {//socket异常
                reason = CodeContent.EXCEPTION_SOCKET_MSG;
                code = CodeContent.EXCEPTION_SOCKET;
            } else if (e instanceof HttpException) {// http异常
                reason = CodeContent.EXCEPTION_HTTP_MSG;
                code = CodeContent.EXCEPTION_HTTP;
            } else if (e instanceof UnknownHostException) {//DNS错误
                reason = CodeContent.EXCEPTION_UNKNOWN_HOST_MSG;
                code = CodeContent.EXCEPTION_UNKNOWN_HOST;
            } else if (e instanceof JsonSyntaxException
                    || e instanceof JsonIOException
                    || e instanceof JsonParseException) {//数据格式化错误
                reason = CodeContent.EXCEPTION_JSON_SYNTAX_MSG;
                code = CodeContent.EXCEPTION_JSON_SYNTAX;
            } else if (e instanceof SocketTimeoutException || e instanceof TimeoutException) {//超时
                reason = CodeContent.EXCEPTION_TIME_OUT_MSG;
                code = CodeContent.EXCEPTION_TIME_OUT;
            } else if (e instanceof ClassCastException) {//类异常
                reason = CodeContent.EXCEPTION_CLASS_CAST_MSG;
                code = CodeContent.EXCEPTION_CLASS_CAST;
            }
        }
        onError(new Result(code, reason));
        onComplete();
    }
}
