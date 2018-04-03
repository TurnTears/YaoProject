package com.zy.yaoproject.bean;

import java.util.List;

/**
 * Created by muzi on 2018/3/31.
 * 727784430@qq.com
 */
public class FloorBean  extends Bean{

    private List<RoomBean> beanList;

    public FloorBean() {
    }

    public FloorBean(String name, List<RoomBean> beanList) {
        super(name);
        this.beanList = beanList;
    }

    public List<RoomBean> getBeanList() {
        return beanList;
    }

    public void setBeanList(List<RoomBean> beanList) {
        this.beanList = beanList;
    }
}
