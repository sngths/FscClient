package com.tianxing.ui.fragment.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tianxing.fscteachersedition.R;
import com.tianxing.presenter.login.LoginPresenter;
import com.tianxing.ui.LoginView;
import com.tianxing.presenter.login.LoginViewPresenter;
import com.tianxing.ui.activity.LoginActivity;
import com.tianxing.ui.fragment.child.BaseBackFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by tianxing on 16/7/5.
 */
public class LoginFragment extends BaseBackFragment implements LoginView {
    public static final String TAG = "Login";


    private Unbinder unbinder;
    @BindView(R.id.editText_userName)
    EditText editTextUsername;
    @BindView(R.id.editText_password)
    EditText editTextPassword;

    @BindView(R.id.button_login)
    Button loginButton;



    private LoginPresenter presenter;

    private boolean isLoading = false;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new LoginViewPresenter(this);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


    @OnClick(R.id.button_login)
    public void loginButton(View view){
        //完成登陆流程
        isLoading = true;
        loginButton.setClickable(false);
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();

        if (username.equals("") || password.equals("")){
            //显示输入提示
            Toast.makeText(getContext(), "输入用户名密码", Toast.LENGTH_SHORT).show();
            loginButton.setClickable(true);
        }else {
            //执行登陆
            presenter.login(username, password);
        }
    }

    @OnClick(R.id.button_register)
    public void registerButton(View view){
        //跳转到注册界面

    }

    /**
     * 跳转到主界面
     */
    @Override
    public void startMainActivity() {
        ((LoginActivity)getActivity()).startMainActivity();
    }

    /**
     * 登陆失败
     */
    @Override
    public void loginFailed() {
        Toast.makeText(getContext(), "登陆失败", Toast.LENGTH_SHORT).show();
        loginButton.setClickable(true);
        isLoading = false;
    }



}
