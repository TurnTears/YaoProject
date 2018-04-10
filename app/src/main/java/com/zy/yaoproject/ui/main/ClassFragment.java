package com.zy.yaoproject.ui.main;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.zy.yaoproject.R;
import com.zy.yaoproject.base.fragment.BaseFragment;
import com.zy.yaoproject.widget.titlebar.TitleBar;

import butterknife.BindView;

/**
 * Created by muzi on 2018/2/1.
 * 727784430@qq.com
 * 分类
 */

public class ClassFragment extends BaseFragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.fragmentContainer)
    FrameLayout fragmentContainer;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    @BindView(R.id.titleBar)
    TitleBar titleBar;


    @Override
    protected int bindLayout() {
        return R.layout.fragment_class;
    }

    @Override
    protected void initView(View view) {

    }


}
