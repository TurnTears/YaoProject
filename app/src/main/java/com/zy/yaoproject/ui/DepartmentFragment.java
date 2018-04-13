package com.zy.yaoproject.ui;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.zy.yaoproject.R;
import com.zy.yaoproject.adapter.DepartmentAdapter;
import com.zy.yaoproject.base.fragment.BaseFragment;
import com.zy.yaoproject.bean.DataBean;
import com.zy.yaoproject.bean.ListBean;
import com.zy.yaoproject.layoutmanager.NsLinearLayoutManager;

import java.util.List;

import butterknife.BindView;

/**
 * Created by muzi on 2018/4/12.
 * 727784430@qq.com
 */

public class DepartmentFragment extends BaseFragment {

    @BindView(R.id.tabView)
    View tabView;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.fragmentContainer)
    FrameLayout fragmentContainer;

    private DataBean dataBean;
    private List<ListBean> beanList;
    private DepartmentAdapter adapter;

    private View oldView;

    @Override
    protected int bindLayout() {
        dataBean = getArguments().getParcelable("bean");
        beanList = dataBean.getList();
        return R.layout.fragment_department;
    }

    @Override
    protected void initView(View view) {
        adapter = new DepartmentAdapter(R.layout.item_nav_class, beanList);
        recyclerView.setLayoutManager(new NsLinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                for (int i = 0; i < beanList.size(); i++) {
                    if (beanList.get(i).isSelect()) {
                        beanList.get(i).setSelect(false);
                    }
                    if (!beanList.get(i).isSelect() && i == position) {
                        beanList.get(i).setSelect(true);
                    }
                }
                openAnimator(view);
                adapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * 开启Indicator动画
     *
     * @param view
     */
    private void openAnimator(View view) {
        if (view == null) {
            return;
        }
        if (oldView == null) {
            oldView = view;
        }
        if (tabView.getVisibility() != View.VISIBLE) {
            tabView.setVisibility(View.VISIBLE);
        }
        float oldY = calculateViewCenter(oldView);
        float newY = calculateViewCenter(view);

        ObjectAnimator animator = ObjectAnimator.ofFloat(tabView, "TranslationY", oldY, newY);
        animator.setDuration(500);
        animator.setInterpolator(new OvershootInterpolator());
        animator.start();

        oldView = view;
    }

    /**
     * 计算位移距离
     *
     * @param view
     * @return
     */
    private float calculateViewCenter(View view) {
        int tabHeight = tabView.getHeight();
        int viewHeight = view.getHeight();
        int viewTop = view.getTop();
        return 1f * (viewTop + viewHeight / 2 - tabHeight / 2);
    }

    public static DepartmentFragment getInstance(DataBean bean) {
        DepartmentFragment fragment = new DepartmentFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("bean", bean);
        fragment.setArguments(bundle);
        return fragment;
    }

}
