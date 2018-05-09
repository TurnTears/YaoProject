package com.zy.yaoproject.ui.houqin;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.internal.util.AppendOnlyLinkedArrayList;


public class LogisticsChildFragment extends BaseFragment {

    @BindView(R.id.stackview)
    CardStackView cardStackView;
    @BindView(R.id.action_change)
    FloatingActionButton actionChange;
    @BindView(R.id.action_delete)
    FloatingActionButton actionDelete;
    @BindView(R.id.multiple_actions)
    FloatingActionsMenu multipleActions;

    private CardStackAdapter stackAdapter;
    private LogisticsBean.DataBean.ListBean bean;
    private List<LogisticsBean.DataBean.ListBean.NeeadBeanBean> beanList;

    private AlertDialog.Builder deleteBuilder;
    private AlertDialog.Builder changeBuilder;

    @Override
    protected int bindLayout() {
        bean = getArguments().getParcelable("bean");
        beanList = bean.getNeeadBean();
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
        Observable.just(position)
                .map(index -> beanList.get(position))
                .map(dataBean -> new ChangeBean(dataBean.getId()))
                .toList()
                .flatMapObservable((Function<List<ChangeBean>, Observable<ChangeCallBackBean>>) list -> RxRetrofit.getApi()
                        .changeState(list))
                .subscribe(new EntityObserver<ChangeCallBackBean>(this) {
                    @Override
                    protected void onSuccess(ChangeCallBackBean changeCallBackBean) {
                        super.onSuccess(changeCallBackBean);
                        showToast(changeCallBackBean.getMsg());

                        //更改配送状态
                        beanList.get(position).setPeisongFlag("1");
                        stackAdapter.updateData(beanList);
                    }
                });
    }

    /**
     * 更改所有配送状态
     */
    private void changeState() {
        Observable.fromIterable(beanList)
                .filter((AppendOnlyLinkedArrayList.NonThrowingPredicate<LogisticsBean.DataBean.ListBean.NeeadBeanBean>) dataBean -> !dataBean.getPeisongFlag().equals("1"))
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
                        for (LogisticsBean.DataBean.ListBean.NeeadBeanBean dataBean : beanList) {
                            if (!dataBean.getPeisongFlag().equals("1")) {
                                dataBean.setPeisongFlag("1");
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
        Observable.just(position)
                .map(index -> beanList.get(position))
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
                        beanList.remove(position);
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

    public static LogisticsChildFragment getInstance(LogisticsBean.DataBean.ListBean bean) {
        LogisticsChildFragment fragment = new LogisticsChildFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("bean", bean);
        fragment.setArguments(bundle);
        return fragment;
    }


    @OnClick({R.id.action_delete, R.id.action_change})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.action_delete:
                showDeleteDialog();
                break;
            case R.id.action_change:
                showChangeDialog();
                break;
        }
        multipleActions.toggle();
    }

    /**
     * 删除确认弹窗
     */
    private void showDeleteDialog() {
        if (deleteBuilder == null) {
            deleteBuilder = new AlertDialog.Builder(getContext());
            deleteBuilder.setTitle("提示")
                    .setMessage("确认全部删除吗？")
                    .setPositiveButton("确定", (dialog, which) -> {
                        dialog.dismiss();
                        deleteState();
                    })
                    .setNegativeButton("取消", (dialog, which) -> {
                        dialog.dismiss();
                    });
        }
        deleteBuilder.show();
    }

    /**
     * 更改确认弹窗
     */
    private void showChangeDialog() {
        if (changeBuilder == null) {
            changeBuilder = new AlertDialog.Builder(getContext());
            changeBuilder.setTitle("提示")
                    .setMessage("确认全部配送吗？")
                    .setPositiveButton("确定", (dialog, which) -> {
                        dialog.dismiss();
                        changeState();
                    })
                    .setNegativeButton("取消", (dialog, which) -> {
                        dialog.dismiss();
                    });
        }
        changeBuilder.show();
    }
}
