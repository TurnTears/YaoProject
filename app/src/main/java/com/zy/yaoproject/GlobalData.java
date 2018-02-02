package com.zy.yaoproject;


import com.zy.yaoproject.entity.IconFontEntity;

import java.text.DecimalFormat;

/**
 * Created by muzi on 2017/12/26.
 * 727784430@qq.com
 * 全局数据
 */

public class GlobalData {

    //iconfont字体
    public static IconFontEntity iconFontEntity = new IconFontEntity();

    /**
     * 保留两个小数
     * 构造方法的字符格式这里如果小数不足2位,会以0补足.
     */
    public static DecimalFormat decimalFormat = new DecimalFormat("0.00");

    /**
     * 格式化金额，保留两位小数
     *
     * @param o
     * @return
     */
    public static String formatPrice(double o) {
        return decimalFormat.format(o);
    }

}
