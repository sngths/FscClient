package com.tianxing.entity.info;

/**
 * Created by tianxing on 16/7/13.
 * 用户信息类
 */
public class UserInfo implements InfoEntity{
    private String Username = "";

    public UserInfo(String username){
        this.Username = username;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }
}
