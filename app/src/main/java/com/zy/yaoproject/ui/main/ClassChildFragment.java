package com.zy.yaoproject.ui.main;

import android.view.View;
import android.widget.TextView;

import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import com.zy.yaoproject.R;
import com.zy.yaoproject.base.fragment.BaseFragment;
import com.zy.yaoproject.entity.ClassifyChildEntity;
import com.zy.yaoproject.ui.ClassActivity;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by muzi on 2018/2/1.
 * 727784430@qq.com
 * 分类-子类
 */

public class ClassChildFragment extends BaseFragment {

    @BindView(R.id.tagFlowLayout)
    TagFlowLayout tagFlowLayout;

    private TagAdapter adapter;
    private ArrayList<ClassifyChildEntity> entityArrayList;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_class_child;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        entityArrayList = getArguments().getParcelableArrayList("data");
        initRecyclerView();
    }

    /**
     * 初始化tag
     */
    private void initRecyclerView() {
        adapter = new TagAdapter(entityArrayList) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                View view = inflaterView(R.layout.item_class_child, parent);
                TextView textView = view.findViewById(R.id.item_text);
                textView.setText(entityArrayList.get(position).getClassify());
                return view;
            }
        };
        tagFlowLayout.setAdapter(adapter);
        tagFlowLayout.setOnTagClickListener((view, position, parent) -> {
            ClassActivity.startIntent(context, entityArrayList.get(position).getClassifyId());
            return false;
        });
    }

}
