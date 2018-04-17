package com.zy.yaoproject.base.dialogfragment;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;

import com.trello.rxlifecycle2.components.support.RxDialogFragment;
import com.zy.yaoproject.base.inter.IBaseView;
import com.zy.yaoproject.ui.LoginActivity;
import com.zy.yaoproject.utils.ToastUtils;
import com.zy.yaoproject.widget.LoadingDialog;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by muzi on 2018/4/4.
 * 727784430@qq.com
 */

public class BaseDialogFragment extends RxDialogFragment implements IBaseView{

    protected LoadingDialog loadingDialog;

    protected <T> ObservableTransformer<T, T> applySchedulers() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void show(android.support.v4.app.FragmentManager manager, String tag) {
        manager.executePendingTransactions();
        if (!this.isAdded()) {
            try {
                super.show(manager, tag);
            } catch (IllegalStateException e) {
            }
        }
    }

    /**
     * 显示进度条
     */
    @Override
    public void showProgress() {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(getContext());
        }
        loadingDialog.show();
    }

    /**
     * 隐藏进度条
     */
    @Override
    public void hideProgress() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }

    @Override
    public void initRefresh(SwipeRefreshLayout refreshLayout, SwipeRefreshLayout.OnRefreshListener onRefreshListener) {

    }

    @Override
    public void refreshFinish() {

    }

    @Override
    public void showToast(int resId) {
        showToast(getString(resId));
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.showToast(getContext(), msg);
    }

    @Override
    public void close() {
    }

    @Override
    public void startLogin() {
        startActivity(new Intent(getContext(), LoginActivity.class));
    }
}
