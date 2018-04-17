package com.zy.yaoproject.bean;

/**
 * Created by muzi on 2018/4/17.
 * 727784430@qq.com
 */

public class BusNeedBean {


    /**
     * fundNum : 3
     * typeId : 74e3592872b849dbaa2e0eb11f48f5ad
     */

    private String fundNum;
    private String typeId;

    public BusNeedBean() {
    }

    public BusNeedBean(String fundNum, String typeId) {
        this.fundNum = fundNum;
        this.typeId = typeId;
    }

    public String getFundNum() {
        return fundNum;
    }

    public void setFundNum(String fundNum) {
        this.fundNum = fundNum;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    @Override
    public String toString() {
        return "BusNeedBean{" +
                "fundNum='" + fundNum + '\'' +
                ", typeId='" + typeId + '\'' +
                '}';
    }
}
