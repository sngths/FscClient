package com.tianxing.presenter.child;

import com.tianxing.entity.assignment.AssignmentDownload;
import com.tianxing.entity.info.ClassInfo;

/**
 * Created by tianxing on 16/7/26.
 */
public interface AssignmentDetailPresenter {



    /**
     * 取得作业类
     * */
    AssignmentDownload getAssignment();


    /**
     * 取得班级类
     * */
    ClassInfo getClassInfo();



}
