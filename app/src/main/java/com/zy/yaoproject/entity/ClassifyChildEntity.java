package com.zy.yaoproject.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by muzi on 2018/1/31.
 * 727784430@qq.com
 */

public class ClassifyChildEntity implements Parcelable {

    private String classify;

    private String classifyId;

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.classify);
        dest.writeString(this.classifyId);
    }

    public ClassifyChildEntity() {
    }

    protected ClassifyChildEntity(Parcel in) {
        this.classify = in.readString();
        this.classifyId = in.readString();
    }

    public static final Parcelable.Creator<ClassifyChildEntity> CREATOR = new Parcelable.Creator<ClassifyChildEntity>() {
        @Override
        public ClassifyChildEntity createFromParcel(Parcel source) {
            return new ClassifyChildEntity(source);
        }

        @Override
        public ClassifyChildEntity[] newArray(int size) {
            return new ClassifyChildEntity[size];
        }
    };
}
