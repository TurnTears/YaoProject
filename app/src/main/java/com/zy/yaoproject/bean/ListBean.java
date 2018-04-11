package com.zy.yaoproject.bean;

import java.util.List;

/**
 * Created by muzi on 2018/4/11.
 * 727784430@qq.com
 */

public class ListBean {

    /**
     * id : 1
     * name : 泌尿外科
     * neeadBean : [{"name":"检查手套","unit":"付"},{"name":"消毒片","unit":"瓶"},{"name":"一次性注射针","unit":"支"}]
     */

    private String id;
    private String name;
    private List<NeeadBean> neeadBean;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<NeeadBean> getNeeadBean() {
        return neeadBean;
    }

    public void setNeeadBean(List<NeeadBean> neeadBean) {
        this.neeadBean = neeadBean;
    }

}
