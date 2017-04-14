package com.tianxing.presenter.child.student;

import com.tianxing.deprecated.entity.http.json.ImageFile;
import com.tianxing.deprecated.entity.info.ClassInfo;
import com.tianxing.pojo.transfer.receive.AssignmentDownload;
import com.tianxing.pojo.transfer.receive.ReplyReceived;
import com.tianxing.pojo.transfer.send.ReplyUpload;

import java.util.List;

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
    Observable<Response<ReplyReceived>> uploadReply(ReplyUpload reply);


    /**
     * 上传一张图片
     * */
    Observable<Response<ImageFile>> uploadImage(String imageFile);


    /**
     * 上传一组图片
     * */
    Observable<Response<ImageFile>> uploadImageSet(List<String> imageFiles);

}
