package com.zy.yaoproject.observer;

import android.content.Context;

import com.zy.yaoproject.entity.BaseEntity;
import com.zy.yaoproject.utils.StringUtils;
import com.zy.yaoproject.utils.ToastUtils;

import org.json.JSONObject;

/**
 * Created by muzi on 2018/1/30.
 * 727784430@qq.com
 */

public abstract class LoadingJsonObserver extends BaseJsonObserver {

    private boolean isShow;
    private Context context;


    public LoadingJsonObserver(Context context) {
        super(context);
//        this.context = (BaseUIActivity) context;
        this.context = context;
    }

    public LoadingJsonObserver(Context context, boolean isShow) {
        this(context);
        this.isShow = isShow;
    }

//    @Override
//    public void onStart() {
//        if (isShow) {
//            context.showLoadingView();
//        }
//    }
//
//    @Override
//    public void onComplete() {
//        super.onComplete();
//        if (isShow) {
//            context.hiddenLoadingView();
//        }
//    }

    @Override
    public void onError(BaseEntity baseEntity, JSONObject jsonObject) {
        super.onError(baseEntity, jsonObject);
        if (!StringUtils.isEmpty(baseEntity.getShowapi_res_error())) {
            ToastUtils.showToast(baseEntity.getShowapi_res_error());
        }
    }

}
