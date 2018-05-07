package com.zy.yaoproject.ui.houqin;

import android.os.Bundle;
import android.view.View;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.loopeer.cardstack.CardStackView;
import com.zy.yaoproject.R;
import com.zy.yaoproject.adapter.CardStackAdapter;
import com.zy.yaoproject.base.fragment.BaseFragment;
import com.zy.yaoproject.bean.ChangeBean;
import com.zy.yaoproject.bean.ChangeCallBackBean;
import com.zy.yaoproject.bean.LogisticsBean;
import com.zy.yaoproject.network.RxRetrofit;
import com.zy.yaoproject.observer.EntityObserver;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.internal.util.AppendOnlyLinkedArrayList;


public class LogisticsFragment extends BaseFragment {

    @BindView(R.id.stackview)
    CardStackView cardStackView;
    @BindView(R.id.action_change)
    FloatingActionButton actionChange;
    @BindView(R.id.action_delete)
    FloatingActionButton actionDelete;
    @BindView(R.id.multiple_actions)
    FloatingActionsMenu multipleActions;

    private CardStackAdapter stackAdapter;
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
        stackAdapter = new CardStackAdapter(getContext(), new CardStackAdapter.OnClick() {
            @Override
            public void onDelete(int position) {
                deleteState(position);
            }

            @Override
            public void onDelivery(int position) {
                changeState(position);
            }
        });
        cardStackView.setAdapter(stackAdapter);
        stackAdapter.updateData(beanList);
        cardStackView.setItemExpendListener(expend -> {
        });
    }

    /**
     * 更改配送状态
     *
     * @param position
     */
    private void changeState(final int position) {
        LogisticsBean.DataBeanX.DataBean dataBean = beanList.get(position);
        String id = dataBean.getId();
        List<ChangeBean> list = new ArrayList<>();
        list.add(new ChangeBean(id));
        RxRetrofit.getApi()
                .changeState(list)
                .compose(applySchedulers())
                .subscribe(new EntityObserver<ChangeCallBackBean>(this) {
                    @Override
                    protected void onSuccess(ChangeCallBackBean changeCallBackBean) {
                        super.onSuccess(changeCallBackBean);
                        showToast(changeCallBackBean.getMsg());

                        //更改配送状态
                        dataBean.setDistributionFlag("1");
                        stackAdapter.updateData(beanList);
                    }
                });
    }

    /**
     * 更改所有配送状态
     */
    private void changeState() {
        Observable.fromIterable(beanList)
                .filter((AppendOnlyLinkedArrayList.NonThrowingPredicate<LogisticsBean.DataBeanX.DataBean>) dataBean -> !dataBean.getDistributionFlag().equals("1"))
                .map(dataBean -> new ChangeBean(dataBean.getId()))
                .toList()
                .flatMapObservable((Function<List<ChangeBean>, Observable<ChangeCallBackBean>>) list -> RxRetrofit.getApi()
                        .changeState(list))
                .compose(applySchedulers())
                .subscribe(new EntityObserver<ChangeCallBackBean>(this) {
                    @Override
                    protected void onSuccess(ChangeCallBackBean changeCallBackBean) {
                        super.onSuccess(changeCallBackBean);
                        showToast(changeCallBackBean.getMsg());

                        //更改配送状态
                        for (LogisticsBean.DataBeanX.DataBean dataBean : beanList) {
                            if (!dataBean.getDistributionFlag().equals("1")) {
                                dataBean.setDistributionFlag("1");
                            }
                        }
                        stackAdapter.updateData(beanList);
                    }
                });
    }

    /**
     * 删除配送状态
     *
     * @param position
     */
    private void deleteState(final int position) {
        LogisticsBean.DataBeanX.DataBean dataBean = beanList.get(position);
        String id = dataBean.getId();
        List<ChangeBean> list = new ArrayList<>();
        list.add(new ChangeBean(id));
        RxRetrofit.getApi()
                .deleteState(list)
                .compose(applySchedulers())
                .subscribe(new EntityObserver<ChangeCallBackBean>(this) {
                    @Override
                    protected void onSuccess(ChangeCallBackBean changeCallBackBean) {
                        super.onSuccess(changeCallBackBean);
                        showToast(changeCallBackBean.getMsg());
                        //删除配送
                        beanList.remove(dataBean);
                        stackAdapter.updateData(beanList);
                    }
                });
    }

    /**
     * 删除所有配送
     */
    private void deleteState() {
        Observable.fromIterable(beanList)
                .map(dataBean -> new ChangeBean(dataBean.getId()))
                .toList()
                .flatMapObservable((Function<List<ChangeBean>, Observable<ChangeCallBackBean>>) list -> RxRetrofit.getApi()
                        .deleteState(list))
                .compose(applySchedulers())
                .subscribe(new EntityObserver<ChangeCallBackBean>(this) {
                    @Override
                    protected void onSuccess(ChangeCallBackBean changeCallBackBean) {
                        super.onSuccess(changeCallBackBean);
                        showToast(changeCallBackBean.getMsg());
                        //删除配送
                        beanList.clear();
                        stackAdapter.updateData(beanList);
                    }
                });
    }

    public static LogisticsFragment getInstance(LogisticsBean.DataBeanX bean) {
        LogisticsFragment fragment = new LogisticsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("bean", bean);
        fragment.setArguments(bundle);
        return fragment;
    }


    @OnClick({R.id.action_delete, R.id.action_change})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.action_delete:
                deleteState();
                break;
            case R.id.action_change:
                changeState();
                break;
        }
        multipleActions.toggle();
    }
}
