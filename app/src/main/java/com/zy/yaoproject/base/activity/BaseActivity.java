package com.zy.yaoproject.base.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zy.yaoproject.utils.ActivityStackManager;
import com.zy.yaoproject.utils.ToastUtils;

import java.lang.ref.WeakReference;

/**
 * Created by muzi on 2018/1/31.
 * 727784430@qq.com
 */

public abstract class BaseActivity extends BaseUIActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStackManager.getInstance().addActivity(new WeakReference<>(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityStackManager.getInstance().removeActivity(new WeakReference<>(this));
    }

    public void startActivity(Class<?> clazz) {
        startActivity(clazz, null);
    }

    public void startActivity(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void showToast(String s) {
        ToastUtils.showToast(s);
    }

    public void showToast(int resId) {
        ToastUtils.showToast(getString(resId));
    }

    /**
     * 加载View
     *
     * @param layout
     * @return
     */
    public View inflaterView(@LayoutRes int layout, ViewGroup root, boolean attach) {
        return LayoutInflater.from(this).inflate(layout, root, attach);
    }

    public View inflaterView(@LayoutRes int layout, ViewGroup root) {
        return inflaterView(layout, root, false);
    }

    public View inflaterView(@LayoutRes int layout) {
        return inflaterView(layout, null);
    }

}
