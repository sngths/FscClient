package com.tianxing.presenter.main;

import com.tianxing.entity.assignment.AssignmentDownload;

/**
 * Created by tianxing on 16/7/18.
 * 作业列表的Presenter接口
 */
public interface AssignmentListPresenter {

    /**
     * 取得单个班级作业列表中作业条数
     * */
    Integer getAssignemntCount(int classID);


    /**
     * 取得班级名称
     * */
    String getClassTitle(int position);
    String getClassTitle(String classID);


    /**
     * 取得对应位置的一条作业数据
     * */
    AssignmentDownload getAssignment(int classID, int position);

    /**
     * 请求刷新作业数据
     * @param classID 班级信息在列表中的位置
     * @param sid 作业中的最大id
     * */
    void requestAssignment(int classID , int sid);
}
