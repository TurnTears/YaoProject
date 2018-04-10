package com.zy.yaoproject.ui.main;

import android.view.View;

import com.zy.yaoproject.R;
import com.zy.yaoproject.base.fragment.BaseFragment;
import com.zy.yaoproject.ui.LoginActivity;

import butterknife.OnClick;

/**
 * Created by muzi on 2018/2/1.
 * 727784430@qq.com
 * 我的
 */

public class UserFragment extends BaseFragment {

    @Override
    protected int bindLayout() {
        return R.layout.fragment_user;
    }

    @Override
    protected void initView(View view) {

    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        startActivity(LoginActivity.class);
    }
}
