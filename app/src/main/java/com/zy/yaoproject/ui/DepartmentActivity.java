package com.zy.yaoproject.ui;

import android.os.Bundle;

import com.zy.yaoproject.R;
import com.zy.yaoproject.base.activity.BaseActivity;
import com.zy.yaoproject.network.RxRetrofit;

/**
 * 科室
 */
public class DepartmentActivity extends BaseActivity {

    @Override
    protected int bindLayout() {
        return R.layout.activity_department;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void requestData() {
        super.requestData();
        RxRetrofit.getApi()
                .getSupply()
                .compose(applySchedulers())
                .subscribe(responseBody -> {

                });
    }
}
