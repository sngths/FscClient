package com.tianxing.ui;

/**
 * Created by tianxing on 16/7/14.
 * 包含所有作业界面图形操作接口
 */
public interface AssignmentView {


    /**
     * 刷新作业数据
     * */
    void refreshAssignment(String classId);


    /***
     * 取消下拉状态
     * */
    void refreshRelease(String classID);
}
