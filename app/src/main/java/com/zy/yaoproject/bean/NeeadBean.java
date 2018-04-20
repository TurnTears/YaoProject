package com.zy.yaoproject.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by muzi on 2018/4/11.
 * 727784430@qq.com
 */

public class NeeadBean implements Parcelable {

    /**
     * id   : 1
     * name : 检查手套
     * unit : 付
     */

    private String id;
    private String name;
    private String unit;
    private boolean isSelect;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public NeeadBean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.unit);
        dest.writeByte(this.isSelect ? (byte) 1 : (byte) 0);
    }

    protected NeeadBean(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.unit = in.readString();
        this.isSelect = in.readByte() != 0;
    }

    public static final Creator<NeeadBean> CREATOR = new Creator<NeeadBean>() {
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
