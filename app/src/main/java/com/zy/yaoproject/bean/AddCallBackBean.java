package com.zy.yaoproject.bean;

import java.util.List;

/**
 * Created by muzi on 2018/4/19.
 * 727784430@qq.com
 */
public class AddCallBackBean extends BaseBean {


    /**
     * count : 1
     * ids : ["675544a4a49c4cd6a6ab290886e85c79"]
     */

    private int count;
    private List<String> ids;

    public AddCallBackBean() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }
}
