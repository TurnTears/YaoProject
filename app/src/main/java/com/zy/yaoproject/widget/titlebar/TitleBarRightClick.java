package com.zy.yaoproject.widget.titlebar;

/**
 * Created by muzi on 2018/1/4.
 * 727784430@qq.com
 */

public abstract class TitleBarRightClick extends TitleBarSimpleClick {

    public abstract void onSimpleRightClick();

    @Override
    public void onRightListener() {
        onSimpleRightClick();
    }

    @Override
    public void onLeftListener() {
    }
}
