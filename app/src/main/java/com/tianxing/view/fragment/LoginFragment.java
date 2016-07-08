package com.tianxing.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tianxing.fscteachersedition.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by tianxing on 16/7/5.
 */
public class LoginFragment extends BaseFragment{
    public static final String TAG = "Login";


    private Unbinder unbinder;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    public void login(View view){

    }

    @OnClick(R.id.button_register)
    public void register(View view){

    }
}
