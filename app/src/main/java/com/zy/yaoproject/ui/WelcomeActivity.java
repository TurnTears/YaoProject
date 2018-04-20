package com.zy.yaoproject.ui;

import android.os.Bundle;

import com.zy.yaoproject.R;
import com.zy.yaoproject.base.activity.BaseActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class WelcomeActivity extends BaseActivity {

    @Override
    protected int bindLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        Observable.timer(2000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    startActivity(LoginActivity.class);
                    finish();
                });
    }
}
