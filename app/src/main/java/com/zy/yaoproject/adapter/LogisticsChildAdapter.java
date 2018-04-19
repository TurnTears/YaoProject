package com.zy.yaoproject.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zy.yaoproject.R;
import com.zy.yaoproject.bean.LogisticsBean;
import com.zy.yaoproject.utils.DateUtils;

import java.util.List;

/**
 * Created by muzi on 2018/4/19.
 * 727784430@qq.com
 */
public class LogisticsChildAdapter extends BaseQuickAdapter<LogisticsBean.DataBeanX.DataBean, BaseViewHolder> {

    public LogisticsChildAdapter(int layoutResId, @Nullable List<LogisticsBean.DataBeanX.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LogisticsBean.DataBeanX.DataBean item) {
        String time = DateUtils.stampToDate(String.valueOf(item.getCreateDate()));
        helper.setText(R.id.text_time, time);

        switch (item.getDistributionFlag()) {
            case "0":
                helper.setText(R.id.text_state, "待配送");
                break;
            case "1":
                helper.setText(R.id.text_state, "已配送");
                break;
        }

        LogisticsBean.DataBeanX.DataBean.TypeNameBean typeName = item.getTypeName();
        helper.setText(R.id.text_name, typeName.getName());
        helper.setText(R.id.text_num, item.getFundNum() + "/" + typeName.getUnit());
    }
}
