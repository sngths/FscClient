package com.tianxing.pojo.http.response;

/**
 * Created by tianxing on 2017/4/14.
 *
 */

public class LoginResult implements Response {

    private int userId;
    private String userName;
    private String iconUrl;
    private String userType;

    private String token;
    private String refreshToken;

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public String getUserType() {
        return userType;
    }

    public String getToken() {
        return token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
