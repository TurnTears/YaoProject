package com.zy.yaoproject.ui.houqin;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.zy.yaoproject.R;
import com.zy.yaoproject.adapter.LogisticsAdapter;
import com.zy.yaoproject.base.activity.BaseActivity;
import com.zy.yaoproject.bean.LogisticsBean;
import com.zy.yaoproject.layoutmanager.NsLinearLayoutManager;
import com.zy.yaoproject.network.RxRetrofit;
import com.zy.yaoproject.observer.EntityObserver;
import com.zy.yaoproject.widget.IndicatorView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 后勤
 */
public class LogisticsActivity extends BaseActivity {

    @BindView(R.id.tabView)
    IndicatorView indicatorView;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private LogisticsAdapter adapter;
    private List<LogisticsBean.DataBeanX> beanList = new ArrayList<>();

    private int currPosition = 0;
    private LogisticsFragment fragment;
    private LogisticsFragment[] fragments;

    @Override
    protected int bindLayout() {
        return R.layout.activity_logistics;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        adapter = new LogisticsAdapter(R.layout.item_nav_class, beanList);
        recyclerView.setLayoutManager(new NsLinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (currPosition != position) {
                    //切换fragment
                    changeFragment(position);
                    currPosition = position;
                }
                indicatorView.openAnimator(view);
            }
        });
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
                        beanList.clear();
                        beanList.addAll(logisticsBean.getData());

                        fragments = new LogisticsFragment[beanList.size()];
                        changeFragment(currPosition);
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    /**
     * 加载fragment
     *
     * @param position
     */
    private void changeFragment(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (fragments[currPosition] != null) {
            transaction.hide(fragments[currPosition]);
        }
        fragment = fragments[position];
        if (fragment == null) {
            fragment = LogisticsFragment.getInstance(beanList.get(position));
            fragments[position] = fragment;
            transaction.add(R.id.fragmentContainer, fragment);
        } else {
            transaction.show(fragment);
        }
        transaction.commitAllowingStateLoss();
    }

}
