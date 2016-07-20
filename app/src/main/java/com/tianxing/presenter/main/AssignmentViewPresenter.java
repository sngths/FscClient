package com.tianxing.presenter.main;

import com.tianxing.entity.assignment.Assignment;
import com.tianxing.model.App;
import com.tianxing.model.AssignmentPool;
import com.tianxing.ui.AssingmentView;

/**
 * Created by tianxing on 16/7/15.
 * 提供作业界面数据请求接口
 */
public class AssignmentViewPresenter extends AssignmentPresenter {


    /**
     * presenter持有model的引用
     * */
    private AssignmentPool assignmentPool;




    private AssingmentView view;

    public AssignmentViewPresenter(AssingmentView view){
        this.view = view;
        assignmentPool = App.getInstance().getAssignmentPool();
    }

    /**
     * 取得单个班级作业列表中作业条数
     *
     * @param classID
     */
    @Override
    public Integer getAssignemntCount(int classID) {
        return assignmentPool.getClassData(classID).getAssigmnetsCount();
    }

    /**
     * 取得班级名称
     *
     * @param position
     */
    @Override
    public String getClassTitle(int position) {
        return  assignmentPool.getClassData(position).getTitle();
    }

    @Override
    public String getClassTitle(String classID) {
        return null;
    }

    /**
     * 取得对应位置的一条作业数据
     */
    @Override
    public Assignment getAssignment(int classID, int position) {
        return assignmentPool.getClassData(classID).getAssignment(position);
    }


    /**
     * 取得对应位置的班级ID
     *
     * @param position
     */
    @Override
    public String getClassID(int position) {
        return assignmentPool.getClassData(position).getClassID();
    }

    /**
     * 取得ListFragment数目
     */
    @Override
    public Integer getClassCount() {
        return assignmentPool.getClassCount();
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
