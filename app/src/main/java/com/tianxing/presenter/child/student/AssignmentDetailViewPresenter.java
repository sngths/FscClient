package com.tianxing.presenter.child.student;

import com.tianxing.entity.info.ClassInfo;
import com.tianxing.entity.transfer.receive.AssignmentDownload;
import com.tianxing.entity.transfer.receive.ReplyReceived;
import com.tianxing.entity.transfer.send.ReplyUpload;
import com.tianxing.model.App;
import com.tianxing.model.AssignmentPool;
import com.tianxing.model.communication.HttpClient;
import com.tianxing.model.user.StudentUser;

import retrofit2.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by tianxing on 16/11/2.
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
     * 取得作业
     */
    @Override
    public AssignmentDownload getAssignment() {
        return assignment;
    }

    /**
     * 取得班级信息
     */
    @Override
    public ClassInfo getClassInfo() {
        return classInfo;
    }

    /**
     * 请求回复数据
     *
     * @return 为空则表示作业尚未回复
     */
    @Override
    public Observable<Response<ReplyReceived>> requestReply() {
        return client.requestReply(assignment.getId(), ((StudentUser)App.getInstance().getCurrentUser()).getInfo().getUserName())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /***
     * 发送回复
     *
     * @param reply
     */
    @Override
    public Observable<Response<Void>> uploadReply(ReplyUpload reply) {
        return client.uploadReply(reply).subscribeOn(AndroidSchedulers.mainThread());
    }
}
