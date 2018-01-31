package com.zy.yaoproject.entity;

/**
 * Created by muzi on 2018/1/31.
 * 727784430@qq.com
 */

public class BaseEntity {

    private int showapi_res_code;

    private String showapi_res_error;


    public BaseEntity() {
    }

    public BaseEntity(int showapi_res_code, String showapi_res_error) {
        this.showapi_res_code = showapi_res_code;
        this.showapi_res_error = showapi_res_error;
    }

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }
}
