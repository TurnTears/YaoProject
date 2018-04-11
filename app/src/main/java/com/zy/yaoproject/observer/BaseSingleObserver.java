package com.zy.yaoproject.observer;

import android.accounts.NetworkErrorException;
import android.support.annotation.IntDef;

import com.zy.yaoproject.base.inter.IBaseView;
import com.zy.yaoproject.bean.BaseBean;
import com.zy.yaoproject.network.ErrorHandle;
import com.zy.yaoproject.utils.NetUtils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.util.EndConsumerHelper;

/**
 * Created by muzi on 2018/3/2.
 * 727784430@qq.com
 */

public abstract class BaseSingleObserver<T> implements SingleObserver<T> {

    private Disposable s;

    private IBaseView iBaseView;
    /**
     * 是否显示进度条
     */
    private boolean showLoadView = true;


    public BaseSingleObserver(IBaseView iBaseView) {
        this.iBaseView = iBaseView;
    }

    public BaseSingleObserver(IBaseView iBaseView, boolean showLoadView) {
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
     * 成功
     *
     * @param t
     */
    @Override
    public void onSuccess(T t) {
        onNetAvailable(true);
        onComplete();
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

    /**
     * 错误信息
     *
     * @param t
     */
    protected void onError(@NonNull BaseBean t) {
        onComplete();
    }

    /**
     * 请求完成
     */
    protected void onComplete() {
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

    /**
     * 没有登录
     *
     * @return 操作
     * -1：toast提示
     * 0：不做处理
     * 1：打开登录Activity
     */
    public static final int LOGIN_TOAST = -1;
    public static final int LOGIN_NO_ACTION = 0;
    public static final int LOGIN_LOGIN = 1;

    @IntDef({LOGIN_NO_ACTION, LOGIN_TOAST, LOGIN_LOGIN})
    @Retention(RetentionPolicy.SOURCE)
    public @interface LoginAction {
    }

    protected @LoginAction
    int onLoginOut() {
        return LOGIN_TOAST;
    }

    /**
     * 是否显示toast
     *
     * @return
     */
    protected boolean isShowToast() {
        return true;
    }

}
