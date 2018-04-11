package com.zy.yaoproject.bean;


public class BaseBean {

    /**
     * msg : 账号或密码错误
     * status : 500
     */

    public BaseBean() {
    }

    public BaseBean(String msg, int status) {
        this.msg = msg;
        this.status = status;
    }

    private String msg;
    private int status;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
