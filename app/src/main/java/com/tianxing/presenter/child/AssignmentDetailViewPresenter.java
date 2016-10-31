package com.tianxing.presenter.child;

import com.tianxing.entity.assignment.AssignmentDownload;
import com.tianxing.entity.info.ClassInfo;
import com.tianxing.model.App;
import com.tianxing.model.AssignmentPool;

/**
 * Created by tianxing on 16/7/26.
 *
 */
public class AssignmentDetailViewPresenter implements AssignmentDetailPresenter {


    private AssignmentPool assignmentPool;
    private AssignmentDownload assignment;
    private ClassInfo classInfo;//作业所属的班级信息



    public AssignmentDetailViewPresenter(String classID, int position){
        assignmentPool = App.getInstance().getAssignmentPool();
        assignment = assignmentPool.getClassData(classID).getAssignment(position);
        classInfo = App.getInstance().getContactsPool().getClassInfo(classID);
    }


    /**
     * 根classID和position取得作业类
     *
     */
    @Override
    public AssignmentDownload getAssignment() {
        return assignment;
    }

    /**
     * 取得班级类
     */
    @Override
    public ClassInfo getClassInfo() {
        return classInfo;
    }
}
