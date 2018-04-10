package com.zy.yaoproject.base.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.zy.yaoproject.utils.ActivityStackManager;
import com.zy.yaoproject.utils.StringUtils;

import java.lang.ref.WeakReference;

/**
 * Created by muzi on 2017/12/26.
 * 727784430@qq.com
 */

public abstract class BaseActivity extends BaseViewActivity {

    protected InputMethodManager inputMethodManager;

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

    /**
     * 隐藏软键盘
     */
    public void hiddenKeyboard() {
        try {
            if (inputMethodManager == null) {
                inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            }
            // 强制隐藏软键盘
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示键盘
     *
     * @param view
     */
    public void showKeyboard(View view) {
        try {
            if (inputMethodManager == null) {
                inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            }
            inputMethodManager.showSoftInput(view, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 复制到剪贴板
     *
     * @param text
     * @param toast
     */
    public void copyClipboard(String text, String toast) {
        ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData mClipData = ClipData.newPlainText("Label", text);
        cm.setPrimaryClip(mClipData);
        if (!StringUtils.isEmpty(toast)) {
            showToast(toast);
        }
    }

    /**
     * 复制到剪贴板
     *
     * @param text
     */
    public void copyClipboard(String text) {
        copyClipboard(text, null);
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
