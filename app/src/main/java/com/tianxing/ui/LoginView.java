package com.tianxing.ui;

/**
 * Created by tianxing on 16/9/1.
 */
public interface LoginView {


    /**
     * 跳转到主界面
     * */
    void startMainActivity();



    /**
     * 登陆失败
     * */
    void loginFailed();
}
