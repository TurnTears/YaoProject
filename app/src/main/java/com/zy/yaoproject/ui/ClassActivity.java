package com.zy.yaoproject.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.zy.yaoproject.R;
import com.zy.yaoproject.base.activity.BaseActivity;
import com.zy.yaoproject.widget.titlebar.TitleBar;

import butterknife.BindView;

public class ClassActivity extends BaseActivity {

    @BindView(R.id.titleBar)
    TitleBar titleBar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    @Override
    protected int bindLayout() {
        return R.layout.activity_class;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    public static void startIntent(Context context, String classifyId) {
        Intent intent = new Intent(context, ClassActivity.class);
        intent.putExtra("classifyId", classifyId);
        context.startActivity(intent);
    }
}
