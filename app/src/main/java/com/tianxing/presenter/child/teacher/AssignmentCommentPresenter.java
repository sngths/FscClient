package com.tianxing.presenter.child.teacher;

import com.tianxing.deprecated.entity.info.StudentInfo;
import com.tianxing.pojo.transfer.Comment;
import com.tianxing.pojo.transfer.receive.ReplyReceived;

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

    /**
     * 上传一条评语
     * */
    Observable<Response<Void>> uploadComment(Comment comment);
}
