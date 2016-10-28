package com.tianxing.model.user;

/**
 * Created by tianxing on 16/10/28.
 * 保存登陆用户的相关信息
 */

public abstract class User {
    public enum UserType{student,teacher}

    private String username;





    public abstract UserType getUserType();

}
