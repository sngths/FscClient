package com.tianxing.presenter.child;

import com.tianxing.entity.transfer.receive.AssignmentDownload;
import com.tianxing.entity.info.ClassInfo;
import com.tianxing.entity.info.StudentInfo;

import java.util.List;

import rx.Observable;

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


    /**
     * 载入学生列表
     * */
    Observable<List<StudentInfo>> loadStudentList();



}
