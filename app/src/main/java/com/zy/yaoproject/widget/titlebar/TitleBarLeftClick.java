package com.zy.yaoproject.widget.titlebar;

/**
 * Created by muzi on 2018/1/4.
 * 727784430@qq.com
 */

public abstract class TitleBarLeftClick extends TitleBarSimpleClick {

    public abstract void onSimpleLeftClick();

    @Override
    public void onLeftListener() {
        onSimpleLeftClick();
    }

    @Override
    public void onRightListener() {

    }
}
