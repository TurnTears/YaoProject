package com.zy.yaoproject.bean;


public class LoginBean extends BaseBean {


    /**
     * depart_flag : yisheng
     * user : {"delFlag":"0","departmentId":"1","id":"1","loginName":"admin","name":"admin","password":"123456","typeId":"1"}
     */

    private String depart_flag;
    private UserBean user;

    public String getDepart_flag() {
        return depart_flag;
    }

    public void setDepart_flag(String depart_flag) {
        this.depart_flag = depart_flag;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class UserBean {
        /**
         * delFlag : 0
         * departmentId : 1
         * id : 1
         * loginName : admin
         * name : admin
         * password : 123456
         * typeId : 1
         */

        private String delFlag;
        private String departmentId;
        private String id;
        private String loginName;
        private String name;
        private String password;
        private String typeId;

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }

        public String getDepartmentId() {
            return departmentId;
        }

        public void setDepartmentId(String departmentId) {
            this.departmentId = departmentId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getTypeId() {
            return typeId;
        }

        public void setTypeId(String typeId) {
            this.typeId = typeId;
        }
    }
}
