package com.zy.yaoproject.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zy.yaoproject.R;
import com.zy.yaoproject.bean.LogisticsBean;

import java.util.List;

/**
 * Created by muzi on 2018/4/19.
 * 727784430@qq.com
 */
public class LogisticsAdapter extends BaseQuickAdapter<LogisticsBean.DataBeanX, BaseViewHolder> {

    public LogisticsAdapter(int layoutResId, @Nullable List<LogisticsBean.DataBeanX> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LogisticsBean.DataBeanX item) {
        helper.setText(R.id.tabText, item.getKeshiName());
    }
}
