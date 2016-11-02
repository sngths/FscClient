package com.tianxing.presenter.child.student;

import com.tianxing.entity.info.ClassInfo;
import com.tianxing.entity.transfer.receive.AssignmentDownload;
import com.tianxing.entity.transfer.receive.ReplyReceived;
import com.tianxing.entity.transfer.send.ReplyUpload;

import retrofit2.Response;
import rx.Observable;

/**
 * Created by tianxing on 16/11/2.
 */

public interface AssignmentDetailPresenter {




    /**
     * 取得作业
     * */
    AssignmentDownload getAssignment();

    /**
     * 取得班级信息
     * */
    ClassInfo getClassInfo();


    /**
     * 请求回复数据
     * @return 为空则表示作业尚未回复
     * */
    Observable<Response<ReplyReceived>> requestReply();


    /***
     * 发送回复
     * */
    Observable<Response<Void>> uploadReply(ReplyUpload reply);
}