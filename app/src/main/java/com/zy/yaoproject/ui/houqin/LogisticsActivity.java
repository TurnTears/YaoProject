package com.zy.yaoproject.ui.houqin;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.zy.yaoproject.R;
import com.zy.yaoproject.base.activity.BaseActivity;
import com.zy.yaoproject.bean.LogisticsBean;
import com.zy.yaoproject.network.RxRetrofit;
import com.zy.yaoproject.observer.EntityObserver;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 后勤
 */
public class LogisticsActivity extends BaseActivity {

    @BindView(R.id.tab)
    SlidingTabLayout tab;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    private String[] titles;
    private List<LogisticsBean.DataBean> dataBeans;
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
        RxRetrofit
                .getApi()
                .getCommonData()
                .compose(applySchedulers())
                .subscribe(new EntityObserver<LogisticsBean>(this) {
                    @Override
                    protected void onSuccess(LogisticsBean logisticsBean) {
                        super.onSuccess(logisticsBean);
                        dataBeans = logisticsBean.getData();
                        initFragment();
                    }
                });
    }

    /**
     * 加载fragment
     *
     */
    private void initFragment() {
        if (dataBeans != null && dataBeans.size() > 0 && titles == null) {
            titles = new String[dataBeans.size()];
        }
        for (int i = 0; i < dataBeans.size(); i++) {
            LogisticsBean.DataBean dataBean = dataBeans.get(i);
            titles[i] = dataBean.getName();
            LogisticsFragment fragment = LogisticsFragment.getInstance(dataBean);
            fragments.add(fragment);
        }

        tab.setViewPager(viewpager, titles, this, fragments);
    }

}
