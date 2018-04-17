package com.zy.yaoproject.ui;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import com.zy.yaoproject.R;
import com.zy.yaoproject.base.activity.BaseActivity;
import com.zy.yaoproject.bean.LoginBean;
import com.zy.yaoproject.network.RxRetrofit;
import com.zy.yaoproject.observer.EntityObserver;
import com.zy.yaoproject.utils.StringUtils;
import com.zy.yaoproject.utils.UserInfoUtils;

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

    private String name, psd;

    @Override
    protected int bindLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        getSaveInfo();
        password.setOnEditorActionListener((textView, id, keyEvent) -> {
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                acceptePsd();
                return true;
            }
            return false;
        });
    }

    @OnClick({R.id.login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login:
                acceptePsd();
                break;
        }
    }

    /**
     * 获取保存的账号密码
     */
    private void getSaveInfo() {
        name = UserInfoUtils.getMob();
        if (!StringUtils.isEmpty(name)) {
            username.setText(name);
            username.setSelection(username.getText().length());
        }
        psd = UserInfoUtils.getPsd();
        if (!StringUtils.isEmpty(name)) {
            password.setText(psd);
            password.setSelection(password.getText().length());
        }
    }

    /**
     * 验证账号密码
     */
    private void acceptePsd() {
        name = username.getText().toString().trim();
        if (StringUtils.isEmpty(name)) {
            showToast("请输入账号");
            return;
        }
        psd = password.getText().toString().trim();
        if (StringUtils.isEmpty(psd)) {
            showToast("请输入密码");
            return;
        }
        login();
    }

    /**
     * 登录
     */
    private void login() {
        RxRetrofit.getApi()
                .login(name, psd)
                .compose(applySchedulers())
                .subscribe(new EntityObserver<LoginBean>(this) {
                    @Override
                    protected void onSuccess(LoginBean userEntity) {
                        super.onSuccess(userEntity);
                        UserInfoUtils.setMob(name);
                        UserInfoUtils.setePsd(psd);
                        switch (userEntity.getDepart_flag()) {
                            case "yisheng":
                                startActivity(DepartmentActivity.class);
                                break;
                            case "houqin":
                                startActivity(LogisticsActivity.class);
                                break;
                        }
                    }
                });
    }
}
