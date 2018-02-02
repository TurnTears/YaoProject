package com.zy.yaoproject.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zy.yaoproject.R;
import com.zy.yaoproject.entity.ClassifyChildEntity;

import java.util.List;

/**
 * Created by muzi on 2018/2/2.
 * 727784430@qq.com
 */

public class ClassChildAdapter extends BaseQuickAdapter<ClassifyChildEntity, BaseViewHolder> {

    public ClassChildAdapter(int layoutResId, @Nullable List<ClassifyChildEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ClassifyChildEntity item) {
        helper.setText(R.id.item_text, item.getClassify());
    }
}
