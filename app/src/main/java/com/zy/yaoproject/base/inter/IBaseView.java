package com.zy.yaoproject.base.inter;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;

/**
 * Created by muzi on 2018/4/3.
 * 727784430@qq.com
 */

public interface IBaseView {

    /**
     * 获取当前上下文对象
     *
     * @return
     */
    Context getContext();

    /**
     * 显示进度条
     */
    void showProgress();

    /**
     * 隐藏进度条
     */
    void hideProgress();

    /**
     * 刷新
     *
     * @param refreshLayout
     * @param onRefreshListener
     */
    void initRefresh(SwipeRefreshLayout refreshLayout, SwipeRefreshLayout.OnRefreshListener onRefreshListener);

    /**
     * 刷新完成
     */
    void refreshFinish();

    /**
     * 根据资源文件id弹出toast
     *
     * @param resId 资源文件id
     */
    void showToast(int resId);

    /**
     * 根据字符串弹出toast
     *
     * @param msg 提示内容
     */
    void showToast(String msg);

    /**
     * 结束当前页面
     */
    void close();

    /**
     * 登录
     */
    void startLogin();

}
