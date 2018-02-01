package com.zy.yaoproject.base.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;

import com.zy.yaoproject.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by muzi on 2018/1/31.
 * 727784430@qq.com
 */

public abstract class BaseUIActivity extends RxActivity {

    protected Context context;

    private Unbinder mUnbinder;

    private SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bindLayout());
        context = this;
        mUnbinder = ButterKnife.bind(this);
        initView();
        requestData();
    }

    protected abstract int bindLayout();

    protected void initView() {
    }

    protected void requestData() {
    }

    /**
     * 初始化刷新
     *
     * @param refreshLayout
     * @param onRefreshListener
     */
    protected void initRefresh(SwipeRefreshLayout refreshLayout, SwipeRefreshLayout.OnRefreshListener onRefreshListener) {
        this.refreshLayout = refreshLayout;
        refreshLayout.setColorSchemeColors(ContextCompat.getColor(this, R.color.themeColor));
        refreshLayout.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.white));
        refreshLayout.setOnRefreshListener(onRefreshListener);
    }

    /**
     * 取消刷新
     */
    public void refreshFinish() {
        if (refreshLayout != null && refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
        }
    }

    /**
     * 开始刷新
     */
    public void refreshStart() {
        if (refreshLayout != null && !refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

}
