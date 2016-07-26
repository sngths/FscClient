package com.tianxing.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.tianxing.fscteachersedition.R;
import com.tianxing.ui.fragment.child.AssignemntReleaseFragment;
import com.tianxing.ui.fragment.main.MainFragment;

/**
 * Created by tianxing on 16/7/5.
 */
public class MainActivity extends BaseActivity {




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout, new MainFragment(), MainFragment.TAG).commit();

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在被销毁时保存状态



    }


    /**
     * 启动作业发布界面
     * */
    public void startAssignmentReleaseFragment(){
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frameLayout, new AssignemntReleaseFragment(), AssignemntReleaseFragment.TAG)
                .hide(getSupportFragmentManager().findFragmentByTag(MainFragment.TAG))
                .addToBackStack(null)
                .commit();
    }


    /**
     * 启动作业详情界面
     * */
    public void startAssignmentDetailFragment(){

    }


}
