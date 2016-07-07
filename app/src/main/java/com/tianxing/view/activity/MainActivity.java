package com.tianxing.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by tianxing on 16/7/5.
 */
public class MainActivity extends BaseActivity {




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null){
            //内存恢复重启 找出并恢复Fragment状态


        }else {
            //正常启动


        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在被销毁时保存状态



    }
}
