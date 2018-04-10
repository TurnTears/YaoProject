package com.zy.yaoproject.observer;


import com.zy.yaoproject.base.inter.IBaseView;
import com.zy.yaoproject.bean.BaseEntity;
import com.zy.yaoproject.utils.StringUtils;

import java.util.Date;

import retrofit2.Response;

/**
 * Created by muzi on 2018/3/5.
 * 727784430@qq.com
 */
public abstract class ResponseObserver<T> extends BaseObserver<Response<T>> {

    private IBaseView iBaseView;
    private Date date;
    private T t;

    public ResponseObserver(IBaseView iBaseView) {
        super(iBaseView);
        this.iBaseView = iBaseView;
    }

    public ResponseObserver(IBaseView iBaseView, boolean showLoadView) {
        super(iBaseView, showLoadView);
        this.iBaseView = iBaseView;
    }

    @Override
    public final void onNext(Response<T> tResponse) {
        super.onNext(tResponse);
        onSuccess(tResponse);
        t = tResponse.body();
        date = tResponse.headers().getDate("Date");
        onNext(t, date);
    }

    protected void onNext(T t, Date date) {
    }

    @Override
    protected void onSuccess(Response<T> tResponse) {
        super.onSuccess(tResponse);
    }

    @Override
    protected void onError(BaseEntity t) {
        super.onError(t);
        if (!StringUtils.isEmpty(t.getMsg()) && isShowToast()) {
            iBaseView.showToast(t.getMsg());
        }
    }
}
