package com.zy.yaoproject.observer;

import android.content.Context;

import com.zy.yaoproject.entity.BaseEntity;
import com.zy.yaoproject.utils.StringUtils;
import com.zy.yaoproject.utils.ToastUtils;


/**
 * Created by muzi on 2018/1/30.
 * 727784430@qq.com
 */

public abstract class LoadingEntityObserver<T extends BaseEntity> extends BaseEntityObserver<T> {

    private Context context;
    private boolean isShow;

    public LoadingEntityObserver(Context context) {
        super(context);
//        this.context = (BaseUIActivity) context;
        this.context = context;
    }

    public LoadingEntityObserver(Context context, boolean isShow) {
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
    public void onError(BaseEntity baseEntity) {
        super.onError(baseEntity);
        if (!StringUtils.isEmpty(baseEntity.getShowapi_res_error())) {
            ToastUtils.showToast(baseEntity.getShowapi_res_error());
        }
    }

}
