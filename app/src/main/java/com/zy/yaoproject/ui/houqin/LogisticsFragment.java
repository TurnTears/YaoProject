package com.zy.yaoproject.ui.houqin;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.zy.yaoproject.R;
import com.zy.yaoproject.adapter.LogisticsAdapter;
import com.zy.yaoproject.base.fragment.BaseFragment;
import com.zy.yaoproject.bean.LogisticsBean;
import com.zy.yaoproject.layoutmanager.NsLinearLayoutManager;
import com.zy.yaoproject.widget.CustomLoadMoreView;
import com.zy.yaoproject.widget.IndicatorView;

import java.util.List;

import butterknife.BindView;

/**
 * 后勤
 */
public class LogisticsFragment extends BaseFragment {

    @BindView(R.id.tabView)
    IndicatorView indicatorView;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private LogisticsAdapter adapter;
    private LogisticsBean.DataBean dataBean;
    private NsLinearLayoutManager layoutManager;
    private List<LogisticsBean.DataBean.ListBean> beanList;


    private int currPosition = 0;
    private LogisticsChildFragment fragment;
    private LogisticsChildFragment[] fragments;

    @Override
    protected int bindLayout() {
        dataBean = getArguments().getParcelable("bean");
        beanList = dataBean.getList();
        fragments = new LogisticsChildFragment[beanList.size()];
        return R.layout.fragment_logistics;
    }

    @Override
    protected void initView(View view) {
        adapter = new LogisticsAdapter(R.layout.item_nav_class, beanList);
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
        fragment = LogisticsChildFragment.getInstance(beanList.get(0));
        changeFragment(currPosition);

        adapter.setLoadMoreView(new CustomLoadMoreView());
        adapter.setOnLoadMoreListener(() -> {
            View child = layoutManager.findViewByPosition(0);
            indicatorView.openAnimator(child);
        }, recyclerView);
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
            fragment = LogisticsChildFragment.getInstance(beanList.get(position));
            fragments[position] = fragment;
            transaction.add(R.id.fragmentContainer, fragment);
        } else {
            transaction.show(fragment);
        }
        transaction.commitAllowingStateLoss();
    }

    public static LogisticsFragment getInstance(LogisticsBean.DataBean bean) {
        LogisticsFragment fragment = new LogisticsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("bean", bean);
        fragment.setArguments(bundle);
        return fragment;
    }

}
