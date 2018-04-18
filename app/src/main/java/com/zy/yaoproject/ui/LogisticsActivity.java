package com.zy.yaoproject.ui;

import android.os.Bundle;
import android.widget.Button;

import com.zy.yaoproject.R;
import com.zy.yaoproject.base.activity.BaseActivity;
import com.zy.yaoproject.network.RxRetrofit;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 后勤
 */
public class LogisticsActivity extends BaseActivity {

    @BindView(R.id.button)
    Button button;

    @Override
    protected int bindLayout() {
        return R.layout.activity_logistics;
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
