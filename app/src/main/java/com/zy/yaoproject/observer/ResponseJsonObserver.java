package com.zy.yaoproject.observer;


import com.zy.yaoproject.base.inter.IBaseView;
import com.zy.yaoproject.network.ErrorHandle;

import org.json.JSONObject;

import java.util.Date;

import okhttp3.ResponseBody;


/**
 * Created by muzi on 2018/3/5.
 * 727784430@qq.com
 */
public abstract class ResponseJsonObserver<T extends ResponseBody> extends ResponseObserver<T> {

    private IBaseView iBaseView;

    private int code;
    private String msg;
    private JSONObject jsonObject;

    public ResponseJsonObserver(IBaseView iBaseView) {
        super(iBaseView);
        this.iBaseView = iBaseView;
    }

    public ResponseJsonObserver(IBaseView iBaseView, boolean showLoadView) {
        super(iBaseView, showLoadView);
        this.iBaseView = iBaseView;
    }


    @Override
    protected void onNext(T t, Date date) {
        super.onNext(t, date);
        try {
            jsonObject = new JSONObject(t.string());
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject = new JSONObject();
        }
        code = jsonObject.optInt("code");
        msg = jsonObject.optString("msg");
        switch (code) {
            case ErrorHandle.SUCCESS:
                onSuccess(jsonObject, date);
                break;
            default:
                onOther(jsonObject, date, code);
                break;
        }
    }

    protected void onSuccess(JSONObject jsonObject) {
    }

    protected void onSuccess(JSONObject jsonObject, Date date) {
        this.onSuccess(jsonObject);
    }

    protected void onOther(JSONObject jsonObject) {
    }

    protected void onOther(JSONObject jsonObject, Date date) {
        this.onOther(jsonObject);
    }

    protected void onOther(JSONObject jsonObject, Date date, int code) {
        this.onOther(jsonObject, date);
    }
}
