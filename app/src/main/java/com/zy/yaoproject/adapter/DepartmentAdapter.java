package com.zy.yaoproject.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zy.yaoproject.R;
import com.zy.yaoproject.bean.ListBean;

import java.util.List;


public class DepartmentAdapter extends BaseQuickAdapter<ListBean, BaseViewHolder> {

    public DepartmentAdapter(int layoutResId, @Nullable List<ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ListBean item) {
        helper.setText(R.id.tabText, item.getName());
        if (onBinding!=null){
            onBinding.onBinding();
        }
    }

    private OnBinding onBinding;

    public void setOnBinding(OnBinding onBinding) {
        this.onBinding = onBinding;
    }

    public interface  OnBinding{
        void onBinding();
    }

}
