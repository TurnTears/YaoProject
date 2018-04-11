package com.zy.yaoproject.utils;



public class UserInfoUtils {

    /**
     * 保存手机号
     *
     * @param phone
     */
    public static void setMob(String phone) {
        PreferenceUtil.put("logmob", phone);
    }

    public static String getMob() {
        return PreferenceUtil.getString("logmob", null);
    }

    /**
     * 保存登录密码
     *
     * @param psd
     */
    public static void setePsd(String psd) {
        PreferenceUtil.put("logpassword", psd);
    }

    public static String getPsd() {
        return PreferenceUtil.getString("logpassword", null);
    }

    /**
     * 保存登录状态
     *
     * @param isLogined
     */
    public static void setIsLogined(boolean isLogined) {
        PreferenceUtil.put("isLogined", isLogined);
        if (!isLogined)
            removeInfo();
    }

    public static boolean isLogined() {
        return PreferenceUtil.getBoolean("isLogined", false);
    }

    /**
     * 保存用户名
     *
     * @param nickName
     */
    public static void setNickName(String nickName) {
        PreferenceUtil.put("nickName", nickName);
    }

    public static String getNickName() {
        return PreferenceUtil.getString("nickName", null);
    }

    /**
     * 用户头像
     *
     * @param imgUrl
     */
    public static void setHeadPhoto(String imgUrl) {
        PreferenceUtil.put("headPhoto", imgUrl);
    }

    public static String getHeadPhoto() {
        return PreferenceUtil.getString("headPhoto", null);
    }

    /**
     * 用户id
     * 非加密
     *
     * @param uid
     */
    public static void setUid(String uid) {
        PreferenceUtil.put("uid", uid);
    }

    public static String getUid() {
        return PreferenceUtil.getString("uid", null);
    }

    /**
     * 用户id
     * 加密
     *
     * @param userDesId
     */
    public static void setUserDesId(String userDesId) {
        PreferenceUtil.put("userDesId", userDesId);
    }

    public static String getUserDesId() {
        return PreferenceUtil.getString("userDesId", null);
    }

    /**
     * 是否是第一次登录
     *
     * @param isFirst
     */
    public static void setIsFirst(boolean isFirst) {
        PreferenceUtil.put("isFirst", isFirst);
    }

    public static boolean isFirst() {
        return PreferenceUtil.getBoolean("isFirst", true);
    }


    /**
     * 删除用户信息
     */
    public static void removeInfo() {
        PreferenceUtil.remove("logpassword", "nickName", "headPhoto", "uid", "userDesId");
    }

    /**
     * 退出登录
     */
    public static void logOut() {
        UserInfoUtils.setIsLogined(false);
        removeInfo();
    }

}
