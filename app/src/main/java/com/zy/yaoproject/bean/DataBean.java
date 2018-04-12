package com.zy.yaoproject.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by muzi on 2018/4/11.
 * 727784430@qq.com
 */

public class DataBean implements Parcelable {

    /**
     * id : 1
     * list : [{"id":"1","name":"泌尿外科","neeadBean":[{"name":"检查手套","unit":"付"},{"name":"消毒片","unit":"瓶"},{"name":"一次性注射针","unit":"支"}]},{"id":"2","name":"耳鼻喉科","neeadBean":[{"name":"碘伏","unit":"瓶"},{"name":"纱布叠片","unit":"片"},{"name":"一次性口罩","unit":"只"}]},{"id":"3","name":"眼科","neeadBean":[{"name":"绷带","unit":"卷"},{"name":"耦合剂","unit":"瓶"},{"name":"引眼科手术巾","unit":"条"}]}]
     * name : 门诊部
     */

    private String id;
    private String name;
    private List<ListBean> list;

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

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeList(this.list);
    }

    public DataBean() {
    }

    protected DataBean(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.list = new ArrayList<ListBean>();
        in.readList(this.list, ListBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
        @Override
        public DataBean createFromParcel(Parcel source) {
            return new DataBean(source);
        }

        @Override
        public DataBean[] newArray(int size) {
            return new DataBean[size];
        }
    };
}
