package com.zy.yaoproject.ui;

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


    @OnClick({R.id.forget, R.id.login, R.id.register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.forget:
                break;
            case R.id.login:
                startActivity(DepartmentActivity.class);
                break;
            case R.id.register:
                startActivity(LogisticsActivity.class);
                break;
        }
    }
}
