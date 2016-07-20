package com.tianxing.model.communication.http;

import com.tianxing.entity.info.PersonalInfo;
import com.tianxing.model.communication.HttpClient;

/**
 * Created by tianxing on 16/7/5.
 */
public class FscHttpClient implements HttpClient{


    /**
     * 登录
     *
     * @param username
     * @param password
     */
    @Override
    public void Login(String username, String password) {

    }

    /**
     * 认证
     *
     * @param refreshToken
     */
    @Override
    public void authenticate(String refreshToken) {

    }

    /**
     * 请求用户信息 包含班级信息
     */
    @Override
    public PersonalInfo getPersonalInfo() {
        return null;
    }
}
