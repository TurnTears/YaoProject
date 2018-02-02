package com.zy.yaoproject.entity;

import org.litepal.crud.DataSupport;

/**
 * Created by muzi on 2018/2/2.
 * 727784430@qq.com
 * 用户信息
 */

public class UserInfnEntity extends DataSupport {

    private int id;

    private String userName;//用户名

    private String psd;//密码

    private String nickName;//昵称

    private String email;//邮箱

    private String headImg;//头像

    public UserInfnEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPsd() {
        return psd;
    }

    public void setPsd(String psd) {
        this.psd = psd;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }
}
