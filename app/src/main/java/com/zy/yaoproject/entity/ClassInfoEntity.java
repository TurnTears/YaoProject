package com.zy.yaoproject.entity;

/**
 * Created by muzi on 2018/2/2.
 * 727784430@qq.com
 */

public class ClassInfoEntity {

    /**
     * manu : 忻州中恒药业有限责任公司(国产)
     * pzwh : 国药准字H19980038
     * drugName : 司尔利阿司匹林分散片
     * drugId : 59c9aa2f0b5b76e52ff0c446
     * classifyId : 599ad2a0600b2149d689b75a
     */

    private String manu;
    private String pzwh;
    private String drugName;
    private String drugId;
    private String classifyId;
    private String price;

    public String getManu() {
        return manu;
    }

    public void setManu(String manu) {
        this.manu = manu;
    }

    public String getPzwh() {
        return pzwh;
    }

    public void setPzwh(String pzwh) {
        this.pzwh = pzwh;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getDrugId() {
        return drugId;
    }

    public void setDrugId(String drugId) {
        this.drugId = drugId;
    }

    public String getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(String classifyId) {
        this.classifyId = classifyId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
