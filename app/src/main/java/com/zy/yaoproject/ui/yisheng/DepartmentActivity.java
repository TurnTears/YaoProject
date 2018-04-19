package com.zy.yaoproject.ui.yisheng;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.zy.yaoproject.R;
import com.zy.yaoproject.base.activity.BaseActivity;
import com.zy.yaoproject.bean.AllDataBean;
import com.zy.yaoproject.bean.DataBean;
import com.zy.yaoproject.network.RxRetrofit;
import com.zy.yaoproject.observer.EntityObserver;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class DepartmentActivity extends BaseActivity {

    @BindView(R.id.tab)
    SlidingTabLayout tab;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    private String[] titles;
    private List<DataBean> dataBeans;
    private ArrayList<Fragment> fragments = new ArrayList<>();

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
                        dataBeans = data.getData();
                        initFragment();
                    }
                });
    }

    private void initFragment() {
        if (dataBeans != null && dataBeans.size() > 0 && titles == null) {
            titles = new String[dataBeans.size()];
        }
        for (int i = 0; i < dataBeans.size(); i++) {
            DataBean dataBean = dataBeans.get(i);
            titles[i] = dataBean.getName();
            DepartmentFragment fragment = DepartmentFragment.getInstance(dataBean);
            fragments.add(fragment);
        }

        tab.setViewPager(viewpager, titles, this, fragments);
    }

}
