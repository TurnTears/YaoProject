package com.zy.yaoproject.ui.houqin;

import android.os.Bundle;
import android.view.View;

import com.loopeer.cardstack.CardStackView;
import com.zy.yaoproject.R;
import com.zy.yaoproject.adapter.CardStackAdapter;
import com.zy.yaoproject.base.fragment.BaseFragment;
import com.zy.yaoproject.bean.LogisticsBean;

import java.util.List;

import butterknife.BindView;


public class LogisticsFragment extends BaseFragment {

    @BindView(R.id.stackview)
    CardStackView cardStackView;

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
                showToast("onDelete:" + position);
            }

            @Override
            public void onDelivery(int position) {
                showToast("onDelivery:" + position);
            }
        });
        cardStackView.setAdapter(stackAdapter);
        stackAdapter.updateData(beanList);
        cardStackView.setItemExpendListener(expend -> {});
    }

    public static LogisticsFragment getInstance(LogisticsBean.DataBeanX bean) {
        LogisticsFragment fragment = new LogisticsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("bean", bean);
        fragment.setArguments(bundle);
        return fragment;
    }
}
