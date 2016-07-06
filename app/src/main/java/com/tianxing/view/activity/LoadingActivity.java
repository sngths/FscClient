package com.tianxing.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;

import com.tianxing.fscteachersedition.R;

/**
 * Created by tianxing on 16/7/5.
 */
public class LoadingActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
    }




    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //super.onKeyDown(keyCode, event);
        if (keyCode == KeyEvent.KEYCODE_BACK){

        }
        return true;
    }
}
