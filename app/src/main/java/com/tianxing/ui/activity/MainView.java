package com.tianxing.ui.activity;

import java.io.File;

/**
 * Created by tianxing on 16/7/14.
 * 主Activity提供的操作接口  Fragment的启动返回
 */
public interface MainView {




    /**
     * 启动一个作业详情界面
     * */
    void startAssignmentDetailFragment(String classID, Integer position);

    /**
     * 启动回复详情界面
     * */
    void startAssignmentReplyFragment();


    /**
     * 启动作业发布界面
     * */
    void startAssignmentReleaseFragment();


    /**
     * 启动聊天界面
     * */
    void startChatFragment(String username);


    /**
     * 驱动群聊天界面
     * */
    void startGroupChatFragment(String roomName);

    /**
     * 返回 操作
     * */
    void popBack();

    /**
     * 启动拍照Activity
     * */
    void startCapture(CaptureResult result);

    /**
     * 拍照结果
     * */
    interface CaptureResult{
        void Successed(File imageFile);
        void Cancelled();
        void Failed();
    }
}
