package com.tianxing.entity.info;

/**
 * Created by tianxing on 16/7/13.
 * 用户信息类
 */
public class UserInfo implements InfoEntity{
    private String userName;
    private String nickName;
    private String userIconUrl;


    public String getUserName() {
        return userName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getUserIconUrl() {
        return userIconUrl;
    }

}
