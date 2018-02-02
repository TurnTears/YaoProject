package com.zy.yaoproject.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.zy.yaoproject.R;
import com.zy.yaoproject.adapter.ClassInfoAdapter;
import com.zy.yaoproject.base.activity.BaseActivity;
import com.zy.yaoproject.entity.ClassInfoEntity;
import com.zy.yaoproject.layoutmanager.NsLinearLayoutManager;
import com.zy.yaoproject.network.RxRetrofit;
import com.zy.yaoproject.observer.JsonActivityObserver;
import com.zy.yaoproject.utils.JsonUtils;
import com.zy.yaoproject.widget.titlebar.TitleBar;
import com.zy.yaoproject.widget.titlebar.TitleBarLeftClick;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ClassActivity extends BaseActivity {

    @BindView(R.id.titleBar)
    TitleBar titleBar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    private int page = 1;
    private String classifyId;
    private ClassInfoAdapter adapter;
    private List<ClassInfoEntity> entityList = new ArrayList<>();

    @Override
    protected int bindLayout() {
        return R.layout.activity_class;
    }

    @Override
    protected void initView() {
        super.initView();
        classifyId = getIntent().getStringExtra("classifyId");
        titleBar.setTitleBarClickListener(new TitleBarLeftClick() {
            @Override
            public void onSimpleLeftClick() {
                finish();
            }
        });
        initRecyclerView();
        initRefresh(refreshLayout, () -> {
            if (entityList.size() > 0) {
                entityList.clear();
            }
            requestData();
        });
    }

    @Override
    protected void requestData() {
        super.requestData();
        RxRetrofit.getApi()
                .getClassInfo(classifyId, page, null)
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new JsonActivityObserver(this) {
                    @Override
                    public void onSuccess(JSONObject jsonObject) {
                        super.onSuccess(jsonObject);
                        entityList.addAll(JsonUtils.parseClassInfo(jsonObject));
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    private void initRecyclerView() {
        adapter = new ClassInfoAdapter(R.layout.item_class_info, entityList);
        recyclerView.setLayoutManager(new NsLinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                DrugDetailActivity.startIntent(context, entityList.get(position).getClassifyId(),entityList.get(position).getDrugName());
            }
        });
    }

    public static void startIntent(Context context, String classifyId) {
        Intent intent = new Intent(context, ClassActivity.class);
        intent.putExtra("classifyId", classifyId);
        context.startActivity(intent);
    }
}
