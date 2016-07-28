package com.tianxing.ui.activity;

/**
 * Created by tianxing on 16/7/14.
 * 主Activity提供的操作接口  Fragment的启动返回
 */
public interface MainView {




    /**
     * 启动一个作业详情界面
     * */
    void startAssignmentDetailFragment(Integer classID, Integer position);

    /**
     * 启动回复详情界面
     * */


    /**
     * 启动作业发布界面
     * */
    void startAssignmentReleaseFragment();



    /**
     * 返回 操作
     * */
    void popBack();
}
