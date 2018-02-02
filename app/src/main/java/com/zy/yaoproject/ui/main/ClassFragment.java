package com.zy.yaoproject.ui.main;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.zy.yaoproject.R;
import com.zy.yaoproject.adapter.ClassNavAdapter;
import com.zy.yaoproject.base.fragment.BaseFragment;
import com.zy.yaoproject.entity.ClassifyEntity;
import com.zy.yaoproject.layoutmanager.NsLinearLayoutManager;
import com.zy.yaoproject.network.RxRetrofit;
import com.zy.yaoproject.observer.JsonFragmentObserver;
import com.zy.yaoproject.utils.JsonUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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

    private ClassNavAdapter adapter;
    private List<ClassifyEntity> beanList = new ArrayList<>();
    private int currPosition = 0;
    private Bundle bundle;
    private ClassChildFragment childFragment;
    private ClassChildFragment[] childFragments;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_class;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        initRefresh(refresh, () -> getData());
        initRecyclerView();
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        if (beanList.size() > 0) {
            return;
        }
        getData();
    }

    /**
     * 获取数据
     */
    private void getData() {
        RxRetrofit.getApi()
                .getClassify()
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new JsonFragmentObserver(this) {
                    @Override
                    public void onSuccess(JSONObject jsonObject) {
                        super.onSuccess(jsonObject);
                        Observable.just(jsonObject)
                                .map(object -> JsonUtils.parseClassData(object))
                                .subscribe(classifyEntities -> {
                                    beanList.addAll(classifyEntities);
                                    if (beanList.size() > 0) {
                                        childFragments = new ClassChildFragment[beanList.size()];
                                        beanList.get(currPosition).setSelect(true);
                                        changeFragment(currPosition);
                                    }
                                    adapter.notifyDataSetChanged();
                                });
                    }
                });
    }


    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        adapter = new ClassNavAdapter(context, R.layout.item_nav_class, beanList);
        recyclerView.setLayoutManager(new NsLinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (currPosition != position) {
                    beanList.get(currPosition).setSelect(false);
                    beanList.get(position).setSelect(true);
                    adapter.notifyDataSetChanged();
                    //切换fragment
                    changeFragment(position);
                    currPosition = position;
                }
            }
        });
    }

    /**
     * 切换fragment
     *
     * @param position
     */
    private void changeFragment(int position) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        if (childFragments[currPosition] != null) {
            transaction.hide(childFragments[currPosition]);
        }
        childFragment = childFragments[position];
        if (childFragment == null) {
            childFragment = new ClassChildFragment();
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putParcelableArrayList("data", beanList.get(position).getEntityList());
            childFragment.setArguments(bundle);
            childFragments[position] = childFragment;
            transaction.add(R.id.fragmentContainer, childFragment);
        } else {
            transaction.show(childFragment);
        }
        transaction.commit();
    }

}
