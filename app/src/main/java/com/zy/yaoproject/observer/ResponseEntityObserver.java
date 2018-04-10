package com.zy.yaoproject.observer;


import com.zy.yaoproject.base.inter.IBaseView;
import com.zy.yaoproject.bean.BaseEntity;
import com.zy.yaoproject.network.ErrorHandle;

import java.util.Date;

import io.reactivex.annotations.NonNull;

/**
 * Created by muzi on 2018/3/5.
 * 727784430@qq.com
 */
public class ResponseEntityObserver<T extends BaseEntity> extends ResponseObserver<T> {

    private IBaseView iBaseView;

    public ResponseEntityObserver(IBaseView iBaseView) {
        super(iBaseView);
        this.iBaseView = iBaseView;
    }

    public ResponseEntityObserver(IBaseView iBaseView, boolean showLoadView) {
        super(iBaseView, showLoadView);
        this.iBaseView = iBaseView;
    }


    @Override
    public void onNext(T t, Date date) {
        super.onNext(t, date);
        switch (t.getCode()) {
            case ErrorHandle.SUCCESS:
                onSuccess(t, date);
                break;
            default:
                onOther(t, date);
                break;
        }
    }

    protected void onSuccess(@NonNull T t) {
    }

    protected void onSuccess(@NonNull T t, Date date) {
        this.onSuccess(t);
    }

    protected void onOther(@NonNull T t) {

    }

    protected void onOther(@NonNull T t, Date date) {
        this.onOther(t);
    }

}
