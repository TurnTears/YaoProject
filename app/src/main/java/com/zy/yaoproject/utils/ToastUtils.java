package com.zy.yaoproject.utils;

import android.widget.Toast;

import com.zy.yaoproject.app.App;

/**
 * Created by muzi on 2017/11/13.
 * 727784430@qq.com
 */

public class ToastUtils {

    private static Toast toast;

    public static void showToast(String content) {
        if (StringUtils.isEmpty(content))
            return;
        if (toast == null) {
            toast = Toast.makeText(App.getInstance(), content, Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }
}
