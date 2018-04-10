package com.zy.yaoproject.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.zy.yaoproject.R;
import com.zy.yaoproject.base.activity.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录
 */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.password)
    EditText password;

    @Override
    protected int bindLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @OnClick({R.id.login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login:
//                startActivity(DepartmentActivity.class);
                showProgress();
//                startActivity(LogisticsActivity.class);
                break;
        }
    }
}
