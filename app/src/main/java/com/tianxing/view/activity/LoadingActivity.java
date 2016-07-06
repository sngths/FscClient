package com.tianxing.view.activity;

import android.content.Intent;
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
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus){
            //界面启动完成 创建初始化线程
            new Thread(new Runnable() {
                @Override
                public void run() {


                    try {
                        Thread.currentThread().sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //初始化完成 跳转界面
                            startMainActivity();
                        }
                    });
                }
            }).start();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //super.onKeyDown(keyCode, event);
        //拦截返回按键
        if (keyCode == KeyEvent.KEYCODE_BACK){

        }
        return true;
    }



    private void startMainActivity(){
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        this.finish();
    }


}
