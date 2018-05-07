package com.zy.yaoproject.ui.yisheng;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.zy.yaoproject.R;
import com.zy.yaoproject.adapter.DepartmentChildAdapter;
import com.zy.yaoproject.base.fragment.BaseFragment;
import com.zy.yaoproject.bean.BusNeedBean;
import com.zy.yaoproject.bean.ChangeBean;
import com.zy.yaoproject.bean.ChangeCallBackBean;
import com.zy.yaoproject.bean.CommitCallBackBean;
import com.zy.yaoproject.bean.ListBean;
import com.zy.yaoproject.bean.NeeadBean;
import com.zy.yaoproject.layoutmanager.NsLinearLayoutManager;
import com.zy.yaoproject.network.RxRetrofit;
import com.zy.yaoproject.observer.EntityObserver;
import com.zy.yaoproject.widget.dialogfragment.AddNeedFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by muzi on 2018/4/12.
 * 727784430@qq.com
 */

public class DepartmentChildFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.btn_complete)
    TextView btnComplete;

    /**
     * 添加
     */
    private Button btnAdd;

    private ListBean listBean;
    private List<NeeadBean> beanList;
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
        beanList = listBean.getNeeadBean();
        return R.layout.fragment_department_child;
    }

    @Override
    protected void initView(View view) {
        btnComplete.setOnClickListener(this);
        childAdapter = new DepartmentChildAdapter(R.layout.item_need, beanList);
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

            @Override
            public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                super.onItemLongClick(adapter, view, position);
                deleteNeed(position);
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
                NeeadBean neeadBean = beanList.get(position);
                neeadBean.setSelect(true);
                busNeedBean = new BusNeedBean(String.valueOf(number), neeadBean.getId());
                beanHashMap.put(position, busNeedBean);
            } else {
                if (number == 0) {
                    beanHashMap.remove(position);
                    beanList.get(position).setSelect(false);
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
            addNeedFragment.setAddCallBack(neeadBean -> {
                beanList.add(neeadBean);
                childAdapter.notifyDataSetChanged();
            });
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
                .subscribe(new EntityObserver<CommitCallBackBean>(this) {
                    @Override
                    protected void onSuccess(CommitCallBackBean bean) {
                        super.onSuccess(bean);
                        showToast(bean.getMsg());
                        reset();
                    }
                });
    }

    /**
     * 恢复未选择状态
     */
    private void reset() {
        Set<Integer> integers = beanHashMap.keySet();
        if (integers.size() != 0) {
            for (Integer integer : integers) {
                beanList.get(integer).setSelect(false);
            }
            childAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 长按删除
     */
    private void deleteNeed(int position) {
        Observable.just(position)
                .map(index -> beanList.get(position).getId())
                .map(id -> new ChangeBean(id))
                .map(bean -> {
                    List<ChangeBean> list = new ArrayList<>();
                    list.add(bean);
                    return list;
                })
                .flatMap((Function<List<ChangeBean>, Observable<ChangeCallBackBean>>) changeBeans -> RxRetrofit.getApi().deleteNeed(changeBeans))
                .compose(applySchedulers())
                .subscribe(new EntityObserver<ChangeCallBackBean>(this) {
                    @Override
                    protected void onSuccess(ChangeCallBackBean changeCallBackBean) {
                        super.onSuccess(changeCallBackBean);
                        showToast(changeCallBackBean.getMsg());

                        beanList.remove(position);
                        beanHashMap.remove(position);
                        childAdapter.notifyItemRemoved(position);
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
