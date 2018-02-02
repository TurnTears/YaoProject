package com.zy.yaoproject.ui.main;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zy.yaoproject.R;
import com.zy.yaoproject.adapter.ClassChildAdapter;
import com.zy.yaoproject.base.fragment.BaseFragment;
import com.zy.yaoproject.entity.ClassifyChildEntity;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by muzi on 2018/2/1.
 * 727784430@qq.com
 * 分类-子类
 */

public class ClassChildFragment extends BaseFragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private ClassChildAdapter adapter;
    private ArrayList<ClassifyChildEntity> entityArrayList;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_class_child;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        entityArrayList = getArguments().getParcelableArrayList("data");
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
        adapter = new ClassChildAdapter(R.layout.item_class_child, entityArrayList);
        adapter.setSpanSizeLookup((gridLayoutManager, position) -> 1);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 3));
        recyclerView.setAdapter(adapter);
    }

}
