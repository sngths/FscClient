package com.tianxing.presenter.child.teacher;

import com.tianxing.entity.info.StudentInfo;
import com.tianxing.entity.transfer.Comment;
import com.tianxing.entity.transfer.receive.ReplyReceived;
import com.tianxing.model.App;
import com.tianxing.deprecated.HttpClient;

import retrofit2.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by tianxing on 16/11/3.
 */

public class AssignmentCommentViewPresenter implements AssignmentCommentPresenter {



    private String assignmentID;
    private String studentID;
    private StudentInfo studentInfo;
    private HttpClient client;


    public AssignmentCommentViewPresenter(String assignmentID, String studentID){
        this.assignmentID = assignmentID;
        this.studentID = studentID;
        studentInfo = App.getInstance().getContactsPool().getStudentInfo(studentID);
        client = App.getInstance().getHttpClient();
    }


    /**
     * 请求一条作业回复数据
     */
    @Override
    public Observable<Response<ReplyReceived>> requestReply() {
        return client.requestReply(assignmentID, studentID).observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 请求一条作业的批阅数据
     */
    @Override
    public Observable<Response<Void>> requestComment() {
        return null;
    }

    /**
     * 取得一个学生信息
     *
     * @param studentID
     */
    @Override
    public StudentInfo getStudentInfo(String studentID) {
        return App.getInstance().getContactsPool().getStudentInfo(studentID);
    }

    /**
     * 上传一条评语
     *
     * @param comment
     */
    @Override
    public Observable<Response<Void>> uploadComment(Comment comment) {
        return client.uploadComment(comment).observeOn(AndroidSchedulers.mainThread());
    }
}
