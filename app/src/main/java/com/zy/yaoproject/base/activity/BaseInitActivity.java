package com.zy.yaoproject.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by muzi on 2018/4/3.
 * 727784430@qq.com
 */

public abstract class BaseInitActivity extends RxActivity {

    private Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bindLayout());
        mUnbinder = ButterKnife.bind(this);
        initView(savedInstanceState);
        initRecyclerView();
        requestData();
    }

    /**
     * 绑定layout
     *
     * @return
     */
    protected abstract int bindLayout();

    /**
     * 初始化UI
     *
     * @param savedInstanceState
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 初始化RecyclerView
     */
    protected void initRecyclerView() {
    }

    /**
     * 请求数据
     */
    protected void requestData() {
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
