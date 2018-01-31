package com.zy.yaoproject.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.zy.yaoproject.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by muzi on 2018/1/31.
 * 727784430@qq.com
 */

public abstract class BaseUIFragment extends RxFragment {

    protected Context context;
    protected View rootView;
    protected Unbinder unbinder;
    private SwipeRefreshLayout refreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        rootView = inflater.inflate(bindLayout(), container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        initView(view);
        requstData();
    }

    protected abstract int bindLayout();

    protected void initView(View view) {
    }

    protected void requstData() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * 绑定生命周期
     */

    public <T> LifecycleTransformer<T> bindToLife() {
        return bindUntilEvent(FragmentEvent.DESTROY);
    }

    /**
     * 初始化刷新
     *
     * @param refreshLayout
     * @param onRefreshListener
     */
    protected void initRefresh(SwipeRefreshLayout refreshLayout, SwipeRefreshLayout.OnRefreshListener onRefreshListener) {
        this.refreshLayout = refreshLayout;
        refreshLayout.setColorSchemeColors(ContextCompat.getColor(context, R.color.themeColor));
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

}
