package com.zy.yaoproject.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by muzi on 2018/4/11.
 * 727784430@qq.com
 */

public class NeeadBean implements Parcelable {

    /**
     * name : 检查手套
     * unit : 付
     */

    private String name;
    private String unit;

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.unit);
    }

    public NeeadBean() {
    }

    protected NeeadBean(Parcel in) {
        this.name = in.readString();
        this.unit = in.readString();
    }

    public static final Parcelable.Creator<NeeadBean> CREATOR = new Parcelable.Creator<NeeadBean>() {
        @Override
        public NeeadBean createFromParcel(Parcel source) {
            return new NeeadBean(source);
        }

        @Override
        public NeeadBean[] newArray(int size) {
            return new NeeadBean[size];
        }
    };
}
