package com.zy.yaoproject.ui;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.zy.yaoproject.R;
import com.zy.yaoproject.adapter.DepartmentChildAdapter;
import com.zy.yaoproject.base.fragment.BaseFragment;
import com.zy.yaoproject.bean.ListBean;
import com.zy.yaoproject.bean.NeeadBean;
import com.zy.yaoproject.layoutmanager.NsLinearLayoutManager;

import java.util.List;

import butterknife.BindView;

/**
 * Created by muzi on 2018/4/12.
 * 727784430@qq.com
 */

public class DepartmentChildFragment extends BaseFragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private ListBean listBean;
    private List<NeeadBean> neeadBean;
    private DepartmentChildAdapter adapter;

    @Override
    protected int bindLayout() {
        listBean = getArguments().getParcelable("bean");
        neeadBean = listBean.getNeeadBean();
        return R.layout.fragment_department_child;
    }

    @Override
    protected void initView(View view) {
        adapter = new DepartmentChildAdapter(R.layout.item_need, neeadBean);
        recyclerView.setLayoutManager(new NsLinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }

    public static DepartmentChildFragment getInstance(ListBean bean) {
        DepartmentChildFragment fragment = new DepartmentChildFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("bean", bean);
        fragment.setArguments(bundle);
        return fragment;
    }

}
