package com.zy.yaoproject.entity;

import java.util.List;

/**
 * Created by muzi on 2018/1/31.
 * 727784430@qq.com
 */

public class ClassifyEntity {


    /**
     * classify : 急性肾小球肾炎
     * classifyId : 599ad2a0600b2149d689b757
     */

    private String classify;

    private String classifyId;

    private List<String> classList;

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(String classifyId) {
        this.classifyId = classifyId;
    }

    public List<String> getClassList() {
        return classList;
    }

    public void setClassList(List<String> classList) {
        this.classList = classList;
    }
}
