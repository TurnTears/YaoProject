package com.zy.yaoproject.ui;

import android.content.Context;
import android.content.Intent;

import com.zy.yaoproject.R;
import com.zy.yaoproject.base.activity.BaseActivity;
import com.zy.yaoproject.network.RxRetrofit;
import com.zy.yaoproject.observer.JsonActivityObserver;
import com.zy.yaoproject.widget.titlebar.TitleBar;
import com.zy.yaoproject.widget.titlebar.TitleBarLeftClick;

import org.json.JSONObject;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 药品详情
 */
public class DrugDetailActivity extends BaseActivity {

    @BindView(R.id.titleBar)
    TitleBar titleBar;
    private String classifyId;
    private String searchKey;

    @Override
    protected int bindLayout() {
        return R.layout.activity_drug_detail;
    }

    @Override
    protected void initView() {
        super.initView();
        classifyId = getIntent().getStringExtra("classifyId");
        searchKey = getIntent().getStringExtra("searchKey");
        titleBar.setTitleBarClickListener(new TitleBarLeftClick() {
            @Override
            public void onSimpleLeftClick() {
                finish();
            }
        });
    }

    @Override
    protected void requestData() {
        super.requestData();
        RxRetrofit.getApi()
                .getDrugDetail(classifyId, "1", searchKey, 1)
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new JsonActivityObserver(this) {
                    @Override
                    public void onSuccess(JSONObject jsonObject) {
                        super.onSuccess(jsonObject);
                    }
                });
    }

    public static void startIntent(Context context, String classifyId, String searchKey) {
        Intent intent = new Intent(context, DrugDetailActivity.class);
        intent.putExtra("classifyId", classifyId);
        intent.putExtra("searchKey", searchKey);
        context.startActivity(intent);
    }

}
