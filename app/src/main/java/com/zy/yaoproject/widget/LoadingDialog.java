package com.zy.yaoproject.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.wang.avi.AVLoadingIndicatorView;
import com.zy.yaoproject.R;


/**
 * Created by muzi on 2017/7/21.
 * 727784430@qq.com
 */

public class LoadingDialog {

    private Dialog dialog;
    private Context context;
    private AVLoadingIndicatorView indicatorView;

    public LoadingDialog(Context context) {
        this.context = context;
        init();
    }

    public void init() {
        dialog = new Dialog(context, R.style.LoadingDialog);
        View view = LayoutInflater.from(context).inflate(R.layout.widget_loading_dialog, null, false);
        indicatorView = view.findViewById(R.id.avLoading);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setDimAmount(0);
        dialog.setContentView(view);
    }

    public void show() {
        if (!dialog.isShowing()) {
            try {
                //显示对话框
                indicatorView.show();
                dialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

//    public void hide() {
//        if (dialog != null && dialog.isShowing()) {
//            dialog.hide();
//        }
//    }

    public void dismiss() {
        if (dialog != null && dialog.isShowing()) {
            try {
                indicatorView.hide();
                dialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
