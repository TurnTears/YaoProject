package com.zy.yaoproject.utils;

import com.zy.yaoproject.entity.ClassifyEntity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by muzi on 2018/2/1.
 * 727784430@qq.com
 */

public class JsonUtils {


    /**
     * 分类数据
     *
     * @param jsonObject
     * @return
     */
    public static List<ClassifyEntity> parseClass(JSONObject jsonObject) {
        List<ClassifyEntity> classifyEntityList = new ArrayList<>();
        JSONArray jsonArray = jsonObject.optJSONArray("data");
        if (jsonArray != null && jsonArray.length() > 0) {
            JSONObject object;
            String oldClassName = null;
            String curreClassName;
            ClassifyEntity classifyEntity = null;
            List<String> stringList = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                object = jsonArray.optJSONObject(i);
                curreClassName = object.optString("class");
                if (!oldClassName.equals(curreClassName)) {
                    oldClassName = curreClassName;
                    classifyEntity = new ClassifyEntity();
                    stringList = new ArrayList<>();
                    classifyEntity.setClassList(stringList);
                    classifyEntity.setClassify(oldClassName);
                    classifyEntity.setClassifyId(object.optString("classifyId"));
                    stringList.add(object.optString("classify"));
                } else {
                    stringList.add(object.optString("classify"));
                }
                classifyEntityList.add(classifyEntity);
            }
        }
        return classifyEntityList;
    }

}
