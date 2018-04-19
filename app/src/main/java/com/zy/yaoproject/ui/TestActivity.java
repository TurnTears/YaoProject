package com.zy.yaoproject.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.zy.yaoproject.R;
import com.zy.yaoproject.base.activity.BaseActivity;
import com.zy.yaoproject.bean.LogisticsBean;
import com.zy.yaoproject.network.RxRetrofit;
import com.zy.yaoproject.observer.EntityObserver;

import butterknife.BindView;
import butterknife.OnClick;

public class TestActivity extends BaseActivity {

    @BindView(R.id.button)
    Button button;

    @Override
    protected int bindLayout() {
        return R.layout.activity_test;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        RxRetrofit
                .getApi()
                .getCommonData()
                .compose(applySchedulers())
                .subscribe(new EntityObserver<LogisticsBean>(this) {
                    @Override
                    protected void onSuccess(LogisticsBean logisticsBean) {
                        super.onSuccess(logisticsBean);
                        Log.d("LogisticsActivity", "logisticsBean.getData().size():" + logisticsBean.getData().size());
                    }
                });
    }
}
