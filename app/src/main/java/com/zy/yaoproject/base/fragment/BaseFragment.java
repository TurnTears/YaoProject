package com.zy.yaoproject.base.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zy.yaoproject.base.activity.BaseActivity;

/**
 * Created by muzi on 2018/1/31.
 * 727784430@qq.com
 */

public abstract class BaseFragment extends BaseUIFragment {

    private BaseActivity baseActivity;

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

    protected void showToast(String s) {
        baseActivity.showToast(s);
    }

    protected void showToast(int resId) {
        baseActivity.showToast(resId);
    }

    /**
     * 加载View
     *
     * @param layout
     * @return
     */
    protected View inflaterView(@LayoutRes int layout) {
        return baseActivity.inflaterView(layout);
    }

}
