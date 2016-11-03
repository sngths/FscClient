package com.tianxing.presenter.child.teacher;

import com.tianxing.entity.info.StudentInfo;
import com.tianxing.entity.transfer.receive.ReplyReceived;

import retrofit2.Response;
import rx.Observable;

/**
 * Created by tianxing on 16/11/3.
 */

public interface AssignmentCommentPresenter {



    /**
     * 请求一条作业回复数据
     * */
    Observable<Response<ReplyReceived>> requestReply();


    /**
     * 请求一条作业的批阅数据
     * */
    Observable<Response<Void>> requestComment();

    /**
     * 取得一个学生信息
     * */
    StudentInfo getStudentInfo(String studentID);
}
