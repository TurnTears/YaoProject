package com.zy.yaoproject.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zy.yaoproject.R;
import com.zy.yaoproject.entity.ClassInfoEntity;

import java.util.List;

/**
 * Created by muzi on 2018/2/2.
 * 727784430@qq.com
 */

public class ClassInfoAdapter extends BaseQuickAdapter<ClassInfoEntity, BaseViewHolder> {

    public ClassInfoAdapter(int layoutResId, @Nullable List<ClassInfoEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ClassInfoEntity item) {
        helper.setText(R.id.item_name, item.getDrugName());
        helper.setText(R.id.item_price, item.getPrice());
        helper.setText(R.id.item_manu, item.getManu());
        helper.setText(R.id.item_pzwh, item.getPzwh());
    }
}
