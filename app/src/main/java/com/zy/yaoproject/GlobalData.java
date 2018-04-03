package com.zy.yaoproject;


import com.zy.yaoproject.bean.FloorBean;
import com.zy.yaoproject.bean.NeedBean;
import com.zy.yaoproject.bean.RoomBean;
import com.zy.yaoproject.entity.IconFontEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 全局数据
 */
public class GlobalData {

    //iconfont字体
    public static IconFontEntity iconFontEntity = new IconFontEntity();

    //部门集合
    public static List<FloorBean> floorBeanList = new ArrayList<>();

    //科室集合
    public static List<RoomBean> roomBeanList = new ArrayList<>();

    static {

    }


    private RoomBean roomBean;
    private NeedBean bean;
    private List<NeedBean> beans;

    private void initRoomeData() {
        roomBean = new RoomBean();
        roomBean.setName("泌尿外科");
        beans = new ArrayList<>();
        bean = new NeedBean();
        bean.setName("检查手套");
        bean.setUnit("付");
        beans.add(bean);
        bean = new NeedBean();
        bean.setName("消毒片");
        bean.setUnit("瓶");
        beans.add(bean);
        bean = new NeedBean();
        bean.setName("一次性注射针");
        bean.setUnit("支");
        beans.add(bean);
        roomBean.setBeanList(beans);


    }

}
