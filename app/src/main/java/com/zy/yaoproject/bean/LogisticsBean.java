package com.zy.yaoproject.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by muzi on 2018/4/19.
 * 727784430@qq.com
 */
public class LogisticsBean extends BaseBean {


    private List<DataBeanX> data;

    public List<DataBeanX> getData() {
        return data;
    }

    public void setData(List<DataBeanX> data) {
        this.data = data;
    }

    public static class DataBeanX implements Parcelable {
        /**
         * data : [{"createDate":1523980800000,"distributionFlag":"0","fundNum":"1","id":"5bc88e0e4be8421ca2f16d178411eb66","typeName":{"name":"傻狗鹏","unit":"条"},"user":{"id":"1","name":"admin"}}]
         * keshiName : 泌尿外科
         * typeId : 15a0b8dad21d4152bbb9b033b1b31e92
         */

        private String keshiName;
        private String typeId;
        private List<DataBean> data;

        public String getKeshiName() {
            return keshiName;
        }

        public void setKeshiName(String keshiName) {
            this.keshiName = keshiName;
        }

        public String getTypeId() {
            return typeId;
        }

        public void setTypeId(String typeId) {
            this.typeId = typeId;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean implements Parcelable {
            /**
             * createDate : 1523980800000
             * distributionFlag : 0
             * fundNum : 1
             * id : 5bc88e0e4be8421ca2f16d178411eb66
             * typeName : {"name":"傻狗鹏","unit":"条"}
             * user : {"id":"1","name":"admin"}
             */

            private long createDate;
            private String distributionFlag;
            private String fundNum;
            private String id;
            private TypeNameBean typeName;
            private UserBean user;

            public long getCreateDate() {
                return createDate;
            }

            public void setCreateDate(long createDate) {
                this.createDate = createDate;
            }

            public String getDistributionFlag() {
                return distributionFlag;
            }

            public void setDistributionFlag(String distributionFlag) {
                this.distributionFlag = distributionFlag;
            }

            public String getFundNum() {
                return fundNum;
            }

            public void setFundNum(String fundNum) {
                this.fundNum = fundNum;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public TypeNameBean getTypeName() {
                return typeName;
            }

            public void setTypeName(TypeNameBean typeName) {
                this.typeName = typeName;
            }

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
            }

            public static class TypeNameBean implements Parcelable {
                /**
                 * name : 傻狗鹏
                 * unit : 条
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

                public TypeNameBean() {
                }

                protected TypeNameBean(Parcel in) {
                    this.name = in.readString();
                    this.unit = in.readString();
                }

                public static final Creator<TypeNameBean> CREATOR = new Creator<TypeNameBean>() {
                    @Override
                    public TypeNameBean createFromParcel(Parcel source) {
                        return new TypeNameBean(source);
                    }

                    @Override
                    public TypeNameBean[] newArray(int size) {
                        return new TypeNameBean[size];
                    }
                };
            }

            public static class UserBean implements Parcelable {
                /**
                 * id : 1
                 * name : admin
                 */

                private String id;
                private String name;

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

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.id);
                    dest.writeString(this.name);
                }

                public UserBean() {
                }

                protected UserBean(Parcel in) {
                    this.id = in.readString();
                    this.name = in.readString();
                }

                public static final Creator<UserBean> CREATOR = new Creator<UserBean>() {
                    @Override
                    public UserBean createFromParcel(Parcel source) {
                        return new UserBean(source);
                    }

                    @Override
                    public UserBean[] newArray(int size) {
                        return new UserBean[size];
                    }
                };
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeLong(this.createDate);
                dest.writeString(this.distributionFlag);
                dest.writeString(this.fundNum);
                dest.writeString(this.id);
                dest.writeParcelable(this.typeName, flags);
                dest.writeParcelable(this.user, flags);
            }

            public DataBean() {
            }

            protected DataBean(Parcel in) {
                this.createDate = in.readLong();
                this.distributionFlag = in.readString();
                this.fundNum = in.readString();
                this.id = in.readString();
                this.typeName = in.readParcelable(TypeNameBean.class.getClassLoader());
                this.user = in.readParcelable(UserBean.class.getClassLoader());
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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.keshiName);
            dest.writeString(this.typeId);
            dest.writeTypedList(this.data);
        }

        public DataBeanX() {
        }

        protected DataBeanX(Parcel in) {
            this.keshiName = in.readString();
            this.typeId = in.readString();
            this.data = in.createTypedArrayList(DataBean.CREATOR);
        }

        public static final Parcelable.Creator<DataBeanX> CREATOR = new Parcelable.Creator<DataBeanX>() {
            @Override
            public DataBeanX createFromParcel(Parcel source) {
                return new DataBeanX(source);
            }

            @Override
            public DataBeanX[] newArray(int size) {
                return new DataBeanX[size];
            }
        };
    }
}
