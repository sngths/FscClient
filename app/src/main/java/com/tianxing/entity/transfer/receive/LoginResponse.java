package com.tianxing.entity.transfer.receive;

/**
 * Created by tianxing on 16/10/29.
 * 用户登录后的返回信息
 */

public class LoginResponse {
    private String token;
    private String userType;
    private String date;

    public String getToken() {
        return token;
    }

    public String getUserType() {
        return userType;
    }

    public String getDate() {
        return date;
    }
}
