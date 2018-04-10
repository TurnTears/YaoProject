package com.zy.yaoproject.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by muzi on 2017/11/13.
 * 727784430@qq.com
 */

public class ToastUtils {

    private static Toast toast;

    public static void showToast(Context context, String content) {
        if (StringUtils.isEmpty(content))
            return;
        if (toast == null) {
            toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }
}
