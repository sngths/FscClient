package com.tianxing.presenter.main;

/**
 * Created by tianxing on 16/7/18.
 * 作业查看界面 数据请求接口
 */
public abstract class AssignmentPresenter implements AssignmentListPresenter{



    /**
     * 取得对应位置的班级ID
     * */
    public abstract String getClassID(int position);


    /**
     * 取得ListFragment数目
     * */
    public abstract Integer getClassCount();

    /**
     * 根据classID取得position
     * */
    public abstract Integer getPosition(String classID);

    /**
     * 取得对应位置页面标题
     * */
    public String getPageTitle(int position){
        return getClassTitle(position);
    }

    public String getPageTitle(String classID){
        return getClassTitle(classID);
    }
}
