package com.zy.yaoproject.ui.houqin;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zy.yaoproject.R;
import com.zy.yaoproject.adapter.LogisticsChildAdapter;
import com.zy.yaoproject.base.fragment.BaseFragment;
import com.zy.yaoproject.bean.LogisticsBean;

import java.util.List;

import butterknife.BindView;


public class LogisticsFragment extends BaseFragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private LogisticsChildAdapter adapter;
    private LogisticsBean.DataBeanX bean;
    private List<LogisticsBean.DataBeanX.DataBean> beanList;

    @Override
    protected int bindLayout() {
        bean = getArguments().getParcelable("bean");
        beanList = bean.getData();
        return R.layout.fragment_logistics;
    }

    @Override
    protected void initView(View view) {
        adapter = new LogisticsChildAdapter(R.layout.item_logistics, beanList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }


    public static LogisticsFragment getInstance(LogisticsBean.DataBeanX bean) {
        LogisticsFragment fragment = new LogisticsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("bean", bean);
        fragment.setArguments(bundle);
        return fragment;
    }
}
