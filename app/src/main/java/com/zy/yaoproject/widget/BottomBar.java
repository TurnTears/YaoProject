package com.zy.yaoproject.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zy.yaoproject.R;


/**
 * Created by muzi on 2017/12/27.
 * 727784430@qq.com
 */

public class BottomBar extends LinearLayout implements View.OnClickListener {
    private IconFont tab0Img;
    private TextView tab0Text;
    private View tab0;
    private IconFont tab1Img;
    private TextView tab1Text;
    private View tab1;
    private IconFont tab2Img;
    private TextView tab2Text;
    private View tab2;
    private IconFont tab3Img;
    private TextView tab3Text;
    private View tab3;

    private Context context;
    private int curreTab, nextTab;
    private IconFont[] iconFonts = new IconFont[4];
    private TextView[] textViews = new TextView[4];

    private onTabClick onTabClick;

    public void setOnTabClick(BottomBar.onTabClick onTabClick) {
        this.onTabClick = onTabClick;
    }

    public BottomBar(Context context) {
        this(context, null);
    }

    public BottomBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        LayoutInflater.from(context).inflate(R.layout.widget_bottom_bar, this, true);

        tab0Img = findViewById(R.id.tab0_img);
        tab1Img = findViewById(R.id.tab1_img);
        tab2Img = findViewById(R.id.tab2_img);
        tab3Img = findViewById(R.id.tab3_img);

        tab0Text = findViewById(R.id.tab0_text);
        tab1Text = findViewById(R.id.tab1_text);
        tab2Text = findViewById(R.id.tab2_text);
        tab3Text = findViewById(R.id.tab3_text);

        tab0 = findViewById(R.id.tab0);
        tab1 = findViewById(R.id.tab1);
        tab2 = findViewById(R.id.tab2);
        tab3 = findViewById(R.id.tab3);

        tab0.setOnClickListener(this);
        tab1.setOnClickListener(this);
        tab2.setOnClickListener(this);
        tab3.setOnClickListener(this);

        iconFonts[0] = tab0Img;
        iconFonts[1] = tab1Img;
        iconFonts[2] = tab2Img;
        iconFonts[3] = tab3Img;

        textViews[0] = tab0Text;
        textViews[1] = tab1Text;
        textViews[2] = tab2Text;
        textViews[3] = tab3Text;

        //设置默认tab
        curreTab = nextTab = 0;
        setSelectColor(iconFonts[nextTab], textViews[nextTab]);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tab0:
                nextTab = 0;
                break;
            case R.id.tab1:
                nextTab = 1;
                break;
            case R.id.tab2:
                nextTab = 2;
                break;
            case R.id.tab3:
                nextTab = 3;
                break;
        }
        change();
    }


    private void change() {
        //防止多次点击
        if (curreTab == nextTab) {
            return;
        }
        //切换tab
        if (onTabClick != null) {
            onTabClick.onSelectTab(nextTab, curreTab);
        }
        setUnSelectColor();
        setSelectColor(iconFonts[nextTab], textViews[nextTab]);
        setUnSelectColor(iconFonts[curreTab], textViews[curreTab]);
        curreTab = nextTab;
    }

    private void setUnSelectColor(TextView... textViews) {
        for (TextView textView : textViews) {
            textView.setTextColor(ContextCompat.getColor(context, R.color.tabUnSelectColor));
        }
    }

    private void setSelectColor(TextView... textViews) {
        for (TextView textView : textViews) {
            textView.setTextColor(ContextCompat.getColor(context, R.color.themeColor));
        }
    }

    public interface onTabClick {
        void onSelectTab(int nextPosition, int currePosition);
    }
}
