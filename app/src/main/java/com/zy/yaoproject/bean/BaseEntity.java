package com.zy.yaoproject.bean;

/**
 * Created by muzi on 2017/12/4.
 * 727784430@qq.com
 */

public class BaseEntity {


    /**
     * msg : ok
     * code : 200
     */

    private String msg;
    private int code;

    public BaseEntity() {
    }

    public BaseEntity(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                '}';
    }
}
