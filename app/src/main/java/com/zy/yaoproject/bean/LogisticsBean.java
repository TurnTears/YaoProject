package com.zy.yaoproject.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by muzi on 2018/4/19.
 * 727784430@qq.com
 */
public class LogisticsBean extends BaseBean {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {
        /**
         * id : 1
         * list : [{"id":"2","name":"耳鼻喉科","neeadBean":[{"id":"2fb9282932e345fdaa44a1677a7bf20d","name":"一次性口罩","number":"3","peisongFlag":"0","unit":"只"},{"id":"4ed31ee315574f5f8a6e29a723bcf7ba","name":"碘伏","number":"2","peisongFlag":"0","unit":"瓶"},{"id":"926860bc34284a89bc07d409174655ca","name":"纱布叠片","number":"3","peisongFlag":"0","unit":"片"}]},{"id":"3","name":"眼科","neeadBean":[{"id":"5bf16b39eb7b44d9bc6a629eaef9292c","name":"眼镜","number":"1","peisongFlag":"1","unit":"个"}]}]
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

        public static class ListBean implements Parcelable {
            /**
             * id : 2
             * name : 耳鼻喉科
             * neeadBean : [{"id":"2fb9282932e345fdaa44a1677a7bf20d","name":"一次性口罩","number":"3","peisongFlag":"0","unit":"只"},{"id":"4ed31ee315574f5f8a6e29a723bcf7ba","name":"碘伏","number":"2","peisongFlag":"0","unit":"瓶"},{"id":"926860bc34284a89bc07d409174655ca","name":"纱布叠片","number":"3","peisongFlag":"0","unit":"片"}]
             */

            private String id;
            private String name;
            private List<NeeadBeanBean> neeadBean;

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

            public List<NeeadBeanBean> getNeeadBean() {
                return neeadBean;
            }

            public void setNeeadBean(List<NeeadBeanBean> neeadBean) {
                this.neeadBean = neeadBean;
            }

            public static class NeeadBeanBean implements Parcelable {
                /**
                 * id : 2fb9282932e345fdaa44a1677a7bf20d
                 * name : 一次性口罩
                 * number : 3
                 * peisongFlag : 0
                 * unit : 只
                 */

                private String id;
                private String name;
                private String number;
                private String peisongFlag;
                private String unit;

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

                public String getNumber() {
                    return number;
                }

                public void setNumber(String number) {
                    this.number = number;
                }

                public String getPeisongFlag() {
                    return peisongFlag;
                }

                public void setPeisongFlag(String peisongFlag) {
                    this.peisongFlag = peisongFlag;
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
                    dest.writeString(this.id);
                    dest.writeString(this.name);
                    dest.writeString(this.number);
                    dest.writeString(this.peisongFlag);
                    dest.writeString(this.unit);
                }

                public NeeadBeanBean() {
                }

                protected NeeadBeanBean(Parcel in) {
                    this.id = in.readString();
                    this.name = in.readString();
                    this.number = in.readString();
                    this.peisongFlag = in.readString();
                    this.unit = in.readString();
                }

                public static final Parcelable.Creator<NeeadBeanBean> CREATOR = new Parcelable.Creator<NeeadBeanBean>() {
                    @Override
                    public NeeadBeanBean createFromParcel(Parcel source) {
                        return new NeeadBeanBean(source);
                    }

                    @Override
                    public NeeadBeanBean[] newArray(int size) {
                        return new NeeadBeanBean[size];
                    }
                };
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.id);
                dest.writeString(this.name);
                dest.writeTypedList(this.neeadBean);
            }

            public ListBean() {
            }

            protected ListBean(Parcel in) {
                this.id = in.readString();
                this.name = in.readString();
                this.neeadBean = in.createTypedArrayList(NeeadBeanBean.CREATOR);
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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.name);
            dest.writeTypedList(this.list);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.id = in.readString();
            this.name = in.readString();
            this.list = in.createTypedArrayList(ListBean.CREATOR);
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
}
