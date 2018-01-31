package com.zy.yaoproject.ui;

import android.util.Log;
import android.widget.Button;

import com.zy.yaoproject.R;
import com.zy.yaoproject.base.activity.BaseActivity;
import com.zy.yaoproject.entity.ClassifyEntity;
import com.zy.yaoproject.entity.Result;
import com.zy.yaoproject.network.RxRetrofit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    @BindView(R.id.button)
    Button button;

    @Override
    protected int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        RxRetrofit.getApi()
                .getClassify()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Result<ClassifyEntity>>() {
                    @Override
                    public void accept(Result<ClassifyEntity> classifyEntityResult) throws Exception {
                        Log.d("MainActivity", "size():" + classifyEntityResult.getShowapi_res_body().getData().size());
                    }
                });

    }
}
