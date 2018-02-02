package com.zy.yaoproject.widget.titlebar;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zy.yaoproject.R;
import com.zy.yaoproject.utils.StringUtils;
import com.zy.yaoproject.widget.IconFont;


/**
 * Created by muzi on 2017/12/27.
 * 727784430@qq.com
 */

public class TitleBar extends RelativeLayout implements View.OnClickListener {

    private IconFont textBack, textMore;
    private TextView textTitle;
    private CharSequence backSequence, moreSequence;

    private TitleBarSimpleClick titleBarSimpleClick;

    public void setTitleBarClickListener(TitleBarSimpleClick titleBarSimpleClick) {
        this.titleBarSimpleClick = titleBarSimpleClick;
    }

    public TitleBar(Context context) {
        this(context, null);
    }

    public TitleBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.widget_title_bar, this, true);
        textBack = (IconFont) view.findViewById(R.id.title_bar_back);
        textMore = (IconFont) view.findViewById(R.id.title_bar_right);
        textTitle = (TextView) view.findViewById(R.id.title_bar_title);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);

        backSequence = typedArray.getText(R.styleable.TitleBar_icon_left_text);
        if (backSequence==null) {
            backSequence = getResources().getString(R.string.icon_back);
        }
        textBack.setText(backSequence);

        textTitle.setText(typedArray.getText(R.styleable.TitleBar_title_text));

        moreSequence = typedArray.getText(R.styleable.TitleBar_icon_right_text);
        if (!StringUtils.isEmpty(moreSequence)) {
            textMore.setVisibility(VISIBLE);
        }
        textMore.setText(moreSequence);

        typedArray.recycle();
        textBack.setOnClickListener(this);
        textMore.setOnClickListener(this);
    }

    public void setTitleText(CharSequence title) {
        textTitle.setText(title);
    }

    public void setRightText(String text) {
        if (!StringUtils.isEmpty(text)) {
            textMore.setVisibility(VISIBLE);
        }
        textMore.setText(text);
    }

    public void setLeftText(String text) {
        textBack.setText(text);
    }

    public void setLeftBtnEnable(boolean enable) {
        textBack.setEnabled(enable);
    }

    public void setRightBtnEnable(boolean enable) {
        textMore.setEnabled(enable);
    }

    public void setLeftVisiable(int visiable) {
        textBack.setVisibility(visiable);
    }

    public void setRightVisiable(int visiable) {
        textMore.setVisibility(visiable);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_bar_back:
                if (titleBarSimpleClick != null) {
                    titleBarSimpleClick.onLeftListener();
                }
                break;
            case R.id.title_bar_right:
                if (titleBarSimpleClick != null) {
                    titleBarSimpleClick.onRightListener();
                }
                break;
        }
    }
}
