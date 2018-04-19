package com.zy.yaoproject.ui.houqin;

import android.os.Bundle;
import android.view.View;

import com.zy.yaoproject.R;
import com.zy.yaoproject.base.fragment.BaseFragment;
import com.zy.yaoproject.bean.ListBean;


public class LogisticsFragment extends BaseFragment {

    @Override
    protected int bindLayout() {
        return R.layout.fragment_logistics;
    }

    @Override
    protected void initView(View view) {

    }

    public static LogisticsFragment getInstance(ListBean bean) {
        LogisticsFragment fragment = new LogisticsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("bean", bean);
        fragment.setArguments(bundle);
        return fragment;
    }

}
