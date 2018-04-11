package com.zy.yaoproject.ui;

import android.os.Bundle;
import android.util.Log;

import com.zy.yaoproject.R;
import com.zy.yaoproject.base.activity.BaseActivity;
import com.zy.yaoproject.bean.AllDataBean;
import com.zy.yaoproject.bean.DataBean;
import com.zy.yaoproject.network.RxRetrofit;
import com.zy.yaoproject.observer.EntityObserver;

import java.util.List;

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
                .subscribe(new EntityObserver<AllDataBean>(this) {
                    @Override
                    protected void onSuccess(AllDataBean data) {
                        super.onSuccess(data);
                        List<DataBean> data1 = data.getData();
                        Log.d("DepartmentActivity", data1.get(0).getList().get(0).getNeeadBean().get(0).getName());
                    }
                });
    }
}
