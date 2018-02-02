package com.zy.yaoproject.utils;

import com.zy.yaoproject.GlobalData;
import com.zy.yaoproject.entity.ClassInfoEntity;
import com.zy.yaoproject.entity.ClassifyChildEntity;
import com.zy.yaoproject.entity.ClassifyEntity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by muzi on 2018/2/1.
 * 727784430@qq.com
 */

public class JsonUtils {

    /**
     * 解析分类数据
     *
     * @param jsonObject
     * @return
     */
    public static HashMap<String, ArrayList<ClassifyChildEntity>> parseClass(JSONObject jsonObject) {
        HashMap<String, ArrayList<ClassifyChildEntity>> hashMap = new HashMap<>();
        JSONArray jsonArray = jsonObject.optJSONArray("data");
        if (jsonArray != null && jsonArray.length() > 0) {
            JSONObject object;
            ClassifyChildEntity entity;
            ArrayList<ClassifyChildEntity> childEntityList;
            String newclassify;
            for (int i = 0; i < jsonArray.length(); i++) {
                object = jsonArray.optJSONObject(i);
                newclassify = object.optString("class");
                childEntityList = hashMap.get(newclassify);
                if (childEntityList == null) {
                    childEntityList = new ArrayList<>();
                }
                entity = new ClassifyChildEntity();
                entity.setClassify(object.optString("classify"));
                entity.setClassifyId(object.optString("classifyId"));
                childEntityList.add(entity);
                hashMap.put(newclassify, childEntityList);
            }
        }
        return hashMap;
    }

    /**
     * 将分类数据map转list
     *
     * @param hashMap
     * @return
     */
    public static List<ClassifyEntity> mapToList(HashMap<String, ArrayList<ClassifyChildEntity>> hashMap) {
        List<ClassifyEntity> entityList = new ArrayList<>();
        ClassifyEntity entity;
        String other = "其它";
        for (String classify : hashMap.keySet()) {
            if (classify.equals(other)) {
                continue;
            }
            entity = new ClassifyEntity();
            entity.setClassify(classify);
            entity.setEntityList(hashMap.get(classify));
            entityList.add(entity);
        }
        ArrayList<ClassifyChildEntity> childEntityList = hashMap.get(other);
        if (childEntityList != null) {
            entity = new ClassifyEntity();
            entity.setClassify(other);
            entity.setEntityList(childEntityList);
            entityList.add(entity);
        }
        return entityList;
    }

    public static List<ClassifyEntity> parseClassData(JSONObject jsonObject) {
        return mapToList(parseClass(jsonObject));
    }

    /**
     * 解析药品分类信息
     *
     * @param jsonObject
     * @return
     */
    public static List<ClassInfoEntity> parseClassInfo(JSONObject jsonObject) {
        List<ClassInfoEntity> entityList = new ArrayList<>();
        ClassInfoEntity entity;
        JSONObject object;
        JSONArray jsonArray = jsonObject.optJSONArray("data");
        if (jsonArray != null && jsonArray.length() > 0) {
            for (int i = 0; i < jsonArray.length(); i++) {
                entity = new ClassInfoEntity();
                object = jsonArray.optJSONObject(i);
                entity.setClassifyId(object.optString("classifyId"));
                entity.setDrugId(object.optString("drugId"));
                entity.setDrugName(object.optString("drugName"));
                entity.setManu(object.optString("manu"));
                entity.setPzwh(object.optString("pzwh"));
                entity.setPrice(createPrice());
                entityList.add(entity);
            }
        }
        return entityList;
    }

    /**
     * 生成价格
     * <p>
     * 100以内随机数，保留两位小数
     *
     * @return
     */
    private static String createPrice() {
        String price;
        double random = Math.random() * 100;
        if (random < 5) {
            random = 9.90;
        }
        price = "￥" + GlobalData.formatPrice(random);
        return price;
    }
}