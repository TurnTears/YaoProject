package com.zy.yaoproject.entity;

import android.graphics.Typeface;

/**
 * Created by muzi on 2017/12/12.
 * 727784430@qq.com
 */

public class IconFontEntity {

    private Typeface typeface;

    private boolean isPrepare;

    public Typeface getTypeface() {
        return typeface;
    }

    public void setTypeface(Typeface typeface) {
        this.typeface = typeface;
    }

    public boolean isPrepare() {
        return isPrepare;
    }

    public void setPrepare(boolean prepare) {
        isPrepare = prepare;
    }
}
