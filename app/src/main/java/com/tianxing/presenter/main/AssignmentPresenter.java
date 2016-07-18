package com.tianxing.presenter.main;

/**
 * Created by tianxing on 16/7/18.
 * 作业查看界面 数据请求接口
 */
public interface AssignmentPresenter extends AssignmentListPresenter{



    /**
     * 取得对应位置的班级ID
     * */
    String getClassID(int position);


    /**
     * 取得ListFragment数目
     * */
    Integer getAssignmentListFragmentCount();

    /**
     * 取得对应位置页面标题
     * */
    String getPageTitle(int position);
}
