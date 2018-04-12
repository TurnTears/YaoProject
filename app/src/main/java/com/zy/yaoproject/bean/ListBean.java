package com.zy.yaoproject.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by muzi on 2018/4/11.
 * 727784430@qq.com
 */

public class ListBean implements Parcelable {

    /**
     * id : 1
     * name : 泌尿外科
     * neeadBean : [{"name":"检查手套","unit":"付"},{"name":"消毒片","unit":"瓶"},{"name":"一次性注射针","unit":"支"}]
     */

    private String id;
    private String name;
    private List<NeeadBean> neeadBean;

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

    public List<NeeadBean> getNeeadBean() {
        return neeadBean;
    }

    public void setNeeadBean(List<NeeadBean> neeadBean) {
        this.neeadBean = neeadBean;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeList(this.neeadBean);
    }

    public ListBean() {
    }

    protected ListBean(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.neeadBean = new ArrayList<NeeadBean>();
        in.readList(this.neeadBean, NeeadBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<ListBean> CREATOR = new Parcelable.Creator<ListBean>() {
        @Override
        public ListBean createFromParcel(Parcel source) {
            return new ListBean(source);
        }

        @Override
        public ListBean[] newArray(int size) {
            return new ListBean[size];
        }
    };
}
