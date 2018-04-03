package com.zy.yaoproject.bean;

import java.util.List;

/**
 * Created by muzi on 2018/3/31.
 * 727784430@qq.com
 */
public class RoomBean  extends Bean{

    private List<NeedBean> beanList;

    public RoomBean() {
    }

    public RoomBean(String name, List<NeedBean> beanList) {
        super(name);
        this.beanList = beanList;
    }

    public List<NeedBean> getBeanList() {
        return beanList;
    }

    public void setBeanList(List<NeedBean> beanList) {
        this.beanList = beanList;
    }
}
