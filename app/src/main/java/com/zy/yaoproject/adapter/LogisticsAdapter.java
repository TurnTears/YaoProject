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
public class LogisticsAdapter extends BaseQuickAdapter<LogisticsBean.DataBean.ListBean, BaseViewHolder> {

    public LogisticsAdapter(int layoutResId, @Nullable List<LogisticsBean.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LogisticsBean.DataBean.ListBean item) {
        helper.setText(R.id.tabText, item.getName());
        if (onBinding != null) {
            onBinding.onBinding();
        }
    }

    private OnBinding onBinding;

    public void setOnBinding(OnBinding onBinding) {
        this.onBinding = onBinding;
    }

    public interface OnBinding {
        void onBinding();
    }
}
