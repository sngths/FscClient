package com.tianxing.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.RelativeLayout;

import com.tianxing.fscteachersedition.R;
import com.tianxing.ui.fragment.login.LoginFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tianxing on 16/7/7.
 *
 */
public class LoginActivity extends BaseActivity {
    private static final String TAG = "LoginActivity";

    @BindView(R.id.relativeLayout_activity_login)
    RelativeLayout relativeLayout;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        getSupportFragmentManager().beginTransaction().add(R.id.relativeLayout_activity_login, new LoginFragment(), LoginFragment.TAG).commit();
    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }



    /**
     * 跳转到主界面
     */
    public void startMainActivity() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    /**
     * 跳转注册界面
     * */
    public void startRegisterFragment(){

    }
}
