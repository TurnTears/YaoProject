package com.zy.yaoproject.entity;

/**
 * Created by muzi on 2018/1/31.
 * 727784430@qq.com
 */

public class Result<T> {

    private int showapi_res_code;

    private String showapi_res_error;

    private boolean isSuccessful;

    private T showapi_res_body;

    public Result() {
    }

    public Result(int showapi_res_code, String showapi_res_error) {
        this.showapi_res_code = showapi_res_code;
        this.showapi_res_error = showapi_res_error;
    }

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
        isSuccessful = showapi_res_code == 0;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public T getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(T showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }
}
