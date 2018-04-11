package com.zy.yaoproject.observer;

import android.accounts.NetworkErrorException;

import com.zy.yaoproject.base.inter.IBaseView;
import com.zy.yaoproject.bean.BaseBean;
import com.zy.yaoproject.network.ErrorHandle;
import com.zy.yaoproject.utils.NetUtils;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.util.EndConsumerHelper;

/**
 * Created by muzi on 2018/3/2.
 * 727784430@qq.com
 */

public abstract class BaseObserver<T> implements Observer<T> {

    private Disposable s;
    /**
     * Activity上下文
     */
    private IBaseView iBaseView;
    /**
     * 是否显示进度条
     */
    private boolean showLoadView = true;


    public BaseObserver(IBaseView iBaseView) {
        this.iBaseView = iBaseView;
    }

    public BaseObserver(IBaseView iBaseView, boolean showLoadView) {
        this(iBaseView);
        this.showLoadView = showLoadView;
    }


    @Override
    public final void onSubscribe(@NonNull Disposable s) {
        if (EndConsumerHelper.validate(this.s, s, getClass())) {
            this.s = s;
            if (!NetUtils.isNetworkAvailable(iBaseView.getContext())) {
                onNetAvailable(false);
                onError(ErrorHandle.handleException(new NetworkErrorException()));
            } else {
                onStart();
            }
        }
    }

    /**
     * 取消订阅
     */
    protected final void cancel() {
        Disposable s = this.s;
        this.s = DisposableHelper.DISPOSED;
        s.dispose();
    }

    /**
     * 开始
     * 展示进度条
     */
    protected void onStart() {
        if (showLoadView && iBaseView != null) {
            iBaseView.showProgress();
        }
    }

    /**
     * 网络是否可用
     *
     * @param available
     */
    protected void onNetAvailable(boolean available) {

    }

    /**
     * 错误信息
     *
     * @param t
     */
    protected void onError(@NonNull BaseBean t) {
        onComplete();
    }

    /**
     * 请求成功
     *
     * @param t
     */
    protected void onSuccess(@NonNull T t) {

    }

    /**
     * 是否显示toast
     *
     * @return
     */
    protected boolean isShowToast() {
        return true;
    }

    /**
     * 请求完成
     */
    @Override
    public void onComplete() {
        cancel();
        //隐藏进度条
        if (showLoadView && iBaseView != null) {
            iBaseView.hideProgress();
        }
        //结束刷新
        if (iBaseView != null) {
            iBaseView.refreshFinish();
        }
    }

    @Override
    public void onNext(T t) {
        onNetAvailable(true);
    }

    /**
     * 默认错误处理方法
     *
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        onError(ErrorHandle.handleException(e));
    }
}
