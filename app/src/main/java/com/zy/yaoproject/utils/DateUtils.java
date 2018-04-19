package com.zy.yaoproject.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by muzi on 2018/4/19.
 * 727784430@qq.com
 */
public class DateUtils {


    public static String stampToDate(long s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(s);
        res = simpleDateFormat.format(date);
        return res;
    }

}
