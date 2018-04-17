package com.zy.yaoproject.ui;

import android.os.Bundle;
import android.widget.Button;

import com.zy.yaoproject.R;
import com.zy.yaoproject.base.activity.BaseActivity;
import com.zy.yaoproject.network.RxRetrofit;

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
                .subscribe();
    }
}
