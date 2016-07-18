package com.tianxing.presenter.main;

/**
 * Created by tianxing on 16/7/18.
 * 作业列表的Presenter接口
 */
public interface AssignmentListPresenter {

    /**
     * 取得单个班级作业列表中作业条数
     * */
    Integer getAssignemntCount(String classID);

    /**
     * 请求刷新作业数据
     * */
    void requestAssignment(String classI);
}
