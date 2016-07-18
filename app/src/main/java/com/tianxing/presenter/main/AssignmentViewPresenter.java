package com.tianxing.presenter.main;

import com.tianxing.ui.AssingmentView;

import java.util.ArrayList;

/**
 * Created by tianxing on 16/7/15.
 * 提供作业界面数据请求接口
 */
public class AssignmentViewPresenter implements AssignmentPresenter{


    /**
     * 假定数据
     * */
    private ArrayList<String> classIDs = new ArrayList<>(3);




    private AssingmentView view;

    public AssignmentViewPresenter(AssingmentView view){
        this.view = view;
    }

    /**
     * 取得单个班级作业列表中作业条数
     *
     * @param classID
     */
    @Override
    public Integer getAssignemntCount(String classID) {
        return null;
    }



    /**
     * 取得对应位置的班级ID
     *
     * @param position
     */
    @Override
    public String getClassID(int position) {
        return null;
    }

    /**
     * 取得ListFragment数目
     */
    @Override
    public Integer getAssignmentListFragmentCount() {
        return null;
    }

    /**
     * 取得对应位置页面标题
     *
     * @param position
     */
    @Override
    public String getPageTitle(int position) {
        return null;
    }


    /**
     * 请求刷新作业数据
     *
     * @param classI
     */
    @Override
    public void requestAssignment(String classI) {
        //开始从网络请求数据

    }
}
