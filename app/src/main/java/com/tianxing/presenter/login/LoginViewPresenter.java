package com.tianxing.presenter.login;

/**
 * Created by tianxing on 16/9/1.
 *
 */
public class LoginViewPresenter implements LoginPresenter{


    private LoginView view;

    public LoginViewPresenter(LoginView view){
        this.view = view;
    }

    /**
     * 登陆
     *
     * @param username
     * @param password
     */
    @Override
    public void login(String username, String password) {

    }
}
