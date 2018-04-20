package com.zy.yaoproject.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zy.yaoproject.R;
import com.zy.yaoproject.bean.NeeadBean;
import com.zy.yaoproject.widget.AddSubView;

import java.util.List;


public class DepartmentChildAdapter extends BaseQuickAdapter<NeeadBean, BaseViewHolder> {


    public DepartmentChildAdapter(int layoutResId, @Nullable List<NeeadBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NeeadBean item) {
        helper.setText(R.id.text_name, item.getName() + "/" + item.getUnit());
        helper.addOnClickListener(R.id.numberView);
        AddSubView addSubView = helper.getView(R.id.numberView);
        addSubView.setListener((isAdd, number) -> {
            if (listener != null) {
                listener.onChange(isAdd, number);
            }
        });
        if (!item.isSelect()) {
            addSubView.reset();
        }
    }

    private AddSubView.OnChangeListener listener;

    public void setListener(AddSubView.OnChangeListener listener) {
        this.listener = listener;
    }

}
