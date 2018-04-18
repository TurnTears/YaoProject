package com.zy.yaoproject.ui;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.zy.yaoproject.R;
import com.zy.yaoproject.adapter.DepartmentChildAdapter;
import com.zy.yaoproject.base.fragment.BaseFragment;
import com.zy.yaoproject.bean.BusNeedBean;
import com.zy.yaoproject.bean.ListBean;
import com.zy.yaoproject.bean.NeeadBean;
import com.zy.yaoproject.layoutmanager.NsLinearLayoutManager;
import com.zy.yaoproject.network.RxRetrofit;
import com.zy.yaoproject.observer.BaseObserver;
import com.zy.yaoproject.widget.IconFont;
import com.zy.yaoproject.widget.dialogfragment.AddNeedFragment;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by muzi on 2018/4/12.
 * 727784430@qq.com
 */

public class DepartmentChildFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.btn_complete)
    IconFont btnComplete;

    /**
     * 添加
     */
    private Button btnAdd;

    private ListBean listBean;
    private List<NeeadBean> neeadBean;
    private DepartmentChildAdapter childAdapter;

    /**
     * 增加弹窗
     */
    private AddNeedFragment addNeedFragment;

    private BusNeedBean busNeedBean;
    private HashMap<Integer, BusNeedBean> beanHashMap = new HashMap<>();

    @Override
    protected int bindLayout() {
        listBean = getArguments().getParcelable("bean");
        neeadBean = listBean.getNeeadBean();
        return R.layout.fragment_department_child;
    }

    @Override
    protected void initView(View view) {
        btnComplete.setOnClickListener(this);
        childAdapter = new DepartmentChildAdapter(R.layout.item_need, neeadBean);
        View footView = inflaterView(R.layout.foot_item_need);
        btnAdd = footView.findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(this);
        childAdapter.addFooterView(footView);
        recyclerView.setLayoutManager(new NsLinearLayoutManager(getContext()));
        recyclerView.setAdapter(childAdapter);

        recyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.numberView) {
                    recordData(position);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_complete:
                commitNeed();
                break;
            case R.id.btn_add:
                addNeed();
                break;
        }
    }

    /**
     * 记录数据
     *
     * @param position
     */
    private void recordData(int position) {
        childAdapter.setListener((isAdd, number) -> {
            busNeedBean = beanHashMap.get(position);
            if (busNeedBean == null) {
                busNeedBean = new BusNeedBean(String.valueOf(number), neeadBean.get(position).getId());
                beanHashMap.put(position, busNeedBean);
            } else {
                if (number == 0) {
                    beanHashMap.remove(position);
                } else {
                    busNeedBean.setFundNum(String.valueOf(number));
                }
            }
        });
    }

    /**
     * 添加
     */
    private void addNeed() {
        if (addNeedFragment == null) {
            addNeedFragment = AddNeedFragment.getInstance(listBean.getId());
        }
        addNeedFragment.show(getChildFragmentManager(), "addNeedFragment");
    }

    /**
     * 提交
     */
    private void commitNeed() {
        Observable.just(beanHashMap)
                .map(map -> map.entrySet())
                .flatMapIterable(entries -> entries)
                .map(integerBusNeedBeanEntry -> integerBusNeedBeanEntry.getValue())
                .toList()
                .flatMapObservable(busNeedBeans ->
                        RxRetrofit.getApi()
                                .commitNeed(busNeedBeans)
                                .compose(applySchedulers())
                )
                .subscribe(new BaseObserver<ResponseBody>(this) {
                    @Override
                    protected void onSuccess(ResponseBody responseBody) {
                        super.onSuccess(responseBody);
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
