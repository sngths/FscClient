package com.tianxing.model.communication;

import com.tianxing.entity.info.PersonalInfo;

/**
 * Created by tianxing on 16/7/19.
 * 所有接口都是耗时网络操作 不可在主线程下调用
 */
public interface HttpClient {



    /**
     * 登录
     * */
    void Login(String username, String password);


    /**
     * 认证
     * */
    void authenticate(String refreshToken);


    /**
     * 请求用户信息 包含班级信息
     * */
    PersonalInfo getPersonalInfo();



    /**
     * ...
     * */




    /**
     * 请求班级作业数据
     * */


}
