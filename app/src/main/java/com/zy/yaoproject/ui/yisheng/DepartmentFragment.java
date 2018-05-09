package com.zy.yaoproject.ui.yisheng;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.zy.yaoproject.R;
import com.zy.yaoproject.adapter.DepartmentAdapter;
import com.zy.yaoproject.base.fragment.BaseFragment;
import com.zy.yaoproject.bean.DataBean;
import com.zy.yaoproject.bean.ListBean;
import com.zy.yaoproject.layoutmanager.NsLinearLayoutManager;
import com.zy.yaoproject.widget.CustomLoadMoreView;
import com.zy.yaoproject.widget.IndicatorView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by muzi on 2018/4/12.
 * 727784430@qq.com
 */

public class DepartmentFragment extends BaseFragment {

    @BindView(R.id.tabView)
    IndicatorView indicatorView;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.fragmentContainer)
    FrameLayout fragmentContainer;

    private DataBean dataBean;
    private List<ListBean> beanList;
    private DepartmentAdapter adapter;
    private NsLinearLayoutManager layoutManager;

    private int currPosition = 0;
    private DepartmentChildFragment fragment;
    private DepartmentChildFragment[] fragments;

    @Override
    protected int bindLayout() {
        dataBean = getArguments().getParcelable("bean");
        beanList = dataBean.getList();
        fragments = new DepartmentChildFragment[beanList.size()];
        return R.layout.fragment_department;
    }

    @Override
    protected void initView(View view) {
        adapter = new DepartmentAdapter(R.layout.item_nav_class, beanList);
        layoutManager = new NsLinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
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

        //默认加载第一个fragment
        fragment = DepartmentChildFragment.getInstance(beanList.get(0));
        changeFragment(currPosition);

        adapter.setLoadMoreView(new CustomLoadMoreView());
        adapter.setOnLoadMoreListener(() -> {
            View child = layoutManager.findViewByPosition(0);
            indicatorView.openAnimator(child);
        },recyclerView);
    }

    /**
     * 加载fragment
     *
     * @param position
     */
    private void changeFragment(int position) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        if (fragments[currPosition] != null) {
            transaction.hide(fragments[currPosition]);
        }
        fragment = fragments[position];
        if (fragment == null) {
            fragment = DepartmentChildFragment.getInstance(beanList.get(position));
            fragments[position] = fragment;
            transaction.add(R.id.fragmentContainer, fragment);
        } else {
            transaction.show(fragment);
        }
        transaction.commitAllowingStateLoss();
    }

    public static DepartmentFragment getInstance(DataBean bean) {
        DepartmentFragment fragment = new DepartmentFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("bean", bean);
        fragment.setArguments(bundle);
        return fragment;
    }

}
