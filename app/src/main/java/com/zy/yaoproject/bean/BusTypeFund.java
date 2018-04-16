package com.zy.yaoproject.bean;

/**
 * 
 * 
 * @author zhangyao
 * 
 * @date 2018-04-03
 */
public class BusTypeFund {

    /**
     * 名字
     */
    private String name;

    /**
     * 单位
     */
    private String unit;

    /**
     * 删除标记
     */
    private String delFlag;

    /**
     * 科室id
     */
    private String typeId;

    public BusTypeFund() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }
}