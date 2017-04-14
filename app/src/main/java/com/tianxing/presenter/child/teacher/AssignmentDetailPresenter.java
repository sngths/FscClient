package com.tianxing.presenter.child.teacher;

import com.tianxing.pojo.transfer.receive.AssignmentDownload;
import com.tianxing.deprecated.entity.info.ClassInfo;
import com.tianxing.deprecated.entity.info.StudentInfo;

import java.util.List;

import retrofit2.Response;
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
    Observable<Response<List<StudentInfo>>> loadStudentList();



}
