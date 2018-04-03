package com.zy.yaoproject.bean;

/**
 * Created by muzi on 2018/3/31.
 * 727784430@qq.com
 */
public class NeedBean extends Bean {

    private int num;

    public NeedBean() {
    }

    public NeedBean(String name, int num) {
        super(name);
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
