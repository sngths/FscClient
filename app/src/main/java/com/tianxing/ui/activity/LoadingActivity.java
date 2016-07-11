package com.tianxing.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;

import com.tianxing.fscteachersedition.R;
import com.tianxing.presenter.LoadingPresenter;

/**
 * Created by tianxing on 16/7/5.
 */
public class LoadingActivity extends BaseActivity {

    private LoadingPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        presenter = new LoadingPresenter();
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
                        Thread.currentThread().sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    appInitialize(getApplicationContext());

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //初始化完成 跳转界面
                            startNewActivity();
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



    /**
     * 应用初始化
     * */
    private void appInitialize(Context context){
        presenter.appInitializ(context);
    }


    private void startNewActivity(){
        //根据读取到的配置 进行跳转
        String refreshToken = presenter.getLocalConfig().getRefresh_token();
        if (!refreshToken.equals("")){
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }else {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }

        this.finish();
    }


}
