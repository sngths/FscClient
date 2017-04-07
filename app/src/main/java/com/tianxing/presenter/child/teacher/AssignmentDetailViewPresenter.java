package com.tianxing.presenter.child.teacher;

import com.tianxing.deprecated.entity.info.ClassInfo;
import com.tianxing.deprecated.entity.info.StudentInfo;
import com.tianxing.entity.transfer.receive.AssignmentDownload;
import com.tianxing.model.App;
import com.tianxing.deprecated.data.AssignmentPool;
import com.tianxing.deprecated.HttpClient;

import java.util.List;

import retrofit2.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by tianxing on 16/7/26.
 *
 */
public class AssignmentDetailViewPresenter implements AssignmentDetailPresenter {


    private AssignmentPool assignmentPool;
    private AssignmentDownload assignment;
    private ClassInfo classInfo;//作业所属的班级信息

    private HttpClient client;



    public AssignmentDetailViewPresenter(String classID, int position){
        assignmentPool = App.getInstance().getAssignmentPool();
        assignment = assignmentPool.getClassData(classID).getAssignment(position);
        classInfo = App.getInstance().getContactsPool().getClassInfo(classID);
        client = App.getInstance().getHttpClient();
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


    /**
     * 载入学生列表
     */
    @Override
    public Observable<Response<List<StudentInfo>>> loadStudentList() {
        return client.requestReplyStudentList(assignment.getId()).observeOn(AndroidSchedulers.mainThread());
    }
}
