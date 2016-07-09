package com.tianxing.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.RelativeLayout;

import com.tianxing.fscteachersedition.R;
import com.tianxing.ui.fragment.LoginFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tianxing on 16/7/7.
 *
 */
public class LoginActivity extends FragmentActivity {
    private static final String TAG = "LoginActivity";

    @BindView(R.id.relativeLayout_activity_login)
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        getSupportFragmentManager().beginTransaction().add(R.id.relativeLayout_activity_login, new LoginFragment(), LoginFragment.TAG).commit();
        Log.e(TAG, "onCreate");
    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
