package com.tianxing.presenter.child;

import com.tianxing.entity.assignment.AssignmentUpload;
import com.tianxing.entity.http.json.ImageFile;
import com.tianxing.entity.info.ClassInfo;

import java.util.List;

import retrofit2.Response;
import rx.Observable;

/**
 * Created by tianxing on 16/10/27.
 */

public interface AssignmentReleasePresenter {


    /**
     * 获取班级列表
     * */
    List<ClassInfo> getClassList();

    /**
     * 上传一张图片
     * */
    Observable<Response<ImageFile>> uploadImage(String imagePath);


    /**
     * 上传一组图片
     * */
    Observable<Response<ImageFile>> uploadImageSet(List<String> imageList);

    /***
     * 上传一条作业
     * */
    Observable<Response<Void>> uploadAssignment(AssignmentUpload assignment);
}
