package com.zy.yaoproject.entity;

import java.util.List;

/**
 * Created by muzi on 2018/1/31.
 * 727784430@qq.com
 */

public class ClassifyEntity {

    /**
     * ret_code : 0
     * flag : true
     * data : [{"id":"55c761e35d84145c548a9a76","type":"感冒发热"}]
     */

    private int ret_code;
    private boolean flag;
    private List<DataBean> data;

    public int getRet_code() {
        return ret_code;
    }

    public void setRet_code(int ret_code) {
        this.ret_code = ret_code;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 55c761e35d84145c548a9a76
         * type : 感冒发热
         */

        private String id;
        private String type;
        private boolean isSelect;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }
    }
}
