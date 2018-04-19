package com.zy.yaoproject.bean;

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

    public static class DataBean {
        /**
         * createDate : 1524043318000
         * fundNum : 4
         * id : 5024d8640efe450485eb7241b82a310b
         * keshiName : 泌尿外科
         * typeId : 3
         * typeName : {"name":"一次性注射针","unit":"支"}
         * user : {"id":"1","name":"admin"}
         */

        private long createDate;
        private String fundNum;
        private String id;
        private String keshiName;
        private String typeId;
        private TypeNameBean typeName;
        private UserBean user;

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
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

        public static class TypeNameBean {
            /**
             * name : 一次性注射针
             * unit : 支
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
        }

        public static class UserBean {
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
        }
    }
}
