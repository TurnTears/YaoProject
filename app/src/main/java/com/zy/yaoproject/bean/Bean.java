package com.zy.yaoproject.bean;

/**
 * Created by muzi on 2018/3/31.
 * 727784430@qq.com
 */
public class Bean {

    private String name;

    private String unit;//单位

    public Bean() {
    }

    public Bean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
