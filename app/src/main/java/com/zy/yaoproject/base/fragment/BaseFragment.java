package com.zy.yaoproject.base.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zy.yaoproject.base.activity.BaseActivity;


/**
 * Created by muzi on 2017/12/28.
 * 727784430@qq.com
 */

public abstract class BaseFragment extends BaseViewFragment {

    protected BaseActivity baseActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        baseActivity = (BaseActivity) getActivity();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected void startActivity(Class<?> clazz) {
        baseActivity.startActivity(clazz);
    }

    protected void startActivity(Class<?> clazz, Bundle bundle) {
        baseActivity.startActivity(clazz, bundle);
    }

    /**
     * 隐藏软键盘
     */
    protected void hiddenKeyboard() {
        baseActivity.hiddenKeyboard();
    }

    /**
     * 显示键盘
     *
     * @param view
     */
    protected void showKeyboard(View view) {
        baseActivity.showKeyboard(view);
    }

    /**
     * 加载View
     *
     * @param layout
     * @return
     */
    protected View inflaterView(@LayoutRes int layout, ViewGroup root, boolean attach) {
        return baseActivity.inflaterView(layout, root, attach);
    }

    protected View inflaterView(@LayoutRes int layout, ViewGroup root) {
        return inflaterView(layout, root, false);
    }

    protected View inflaterView(@LayoutRes int layout) {
        return inflaterView(layout, null);
    }

}
