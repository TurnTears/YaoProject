package com.zy.yaoproject.entity;

import java.util.ArrayList;

/**
 * Created by muzi on 2018/2/2.
 * 727784430@qq.com
 */

public class ClassifyEntity {

    private String classify;

    private boolean isSelect;

    private ArrayList<ClassifyChildEntity> entityList;

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public ArrayList<ClassifyChildEntity> getEntityList() {
        return entityList;
    }

    public void setEntityList(ArrayList<ClassifyChildEntity> entityList) {
        this.entityList = entityList;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
