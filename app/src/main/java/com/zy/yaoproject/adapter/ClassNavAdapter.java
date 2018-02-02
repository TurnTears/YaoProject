package com.zy.yaoproject.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zy.yaoproject.R;
import com.zy.yaoproject.entity.ClassifyEntity;

import java.util.List;

/**
 * Created by muzi on 2018/2/1.
 * 727784430@qq.com
 */

public class ClassNavAdapter extends BaseQuickAdapter<ClassifyEntity,BaseViewHolder> {

    private Context context;

    public ClassNavAdapter( Context context,int layoutResId, @Nullable List<ClassifyEntity> data) {
        super(layoutResId, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ClassifyEntity item) {
        helper.setText(R.id.tabText, item.getClassify());
        if (item.isSelect()) {
            helper.setTextColor(R.id.tabText, ContextCompat.getColor(context, R.color.themeColor));
        } else {
            helper.setTextColor(R.id.tabText, ContextCompat.getColor(context, R.color.defaultTextColor));
        }
        helper.setVisible(R.id.tabView, item.isSelect());
    }
}
