package com.zy.yaoproject.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zy.yaoproject.R;
import com.zy.yaoproject.base.fragment.BaseFragment;

import butterknife.BindView;

/**
 * Created by muzi on 2018/2/1.
 * 727784430@qq.com
 * 分类-子类
 */

public class ClassChildFragment extends BaseFragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private String id;
    private ClassFragment parentFragment;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_class_child;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        id = getArguments().getString("id");
        parentFragment = (ClassFragment) getParentFragment();
        initRecyclerView();
    }

    @Override
    protected void requstData() {
        super.requstData();

    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {

    }


}
