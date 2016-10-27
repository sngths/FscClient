package com.tianxing.model.communication;

import com.tianxing.entity.assignment.Assignment;
import com.tianxing.entity.assignment.AssignmentUpload;
import com.tianxing.entity.http.json.ImageFile;
import com.tianxing.entity.http.json.LoginInfo;
import com.tianxing.entity.info.PersonalInfo;

import java.util.List;

import retrofit2.Response;
import rx.Observable;

/**
 * Created by tianxing on 16/7/19.
 *
 */
public interface HttpClient {



    /**
     * 登录
     * */
    Observable<LoginInfo> Login(String username, String password);


    /**
     * 认证
     * */
    void authenticate(String refreshToken);

    String getToken();

    String getRefreshToken();


    /**
     * 请求用户信息 包含班级信息
     * */
    PersonalInfo getPersonalInfo();





    /**
     * 请求单个班级作业数据
     * */
    Observable<Response<List<Assignment>>> requestAssignmentList(String classID, Long serialNumber);



    /**
     * 上传一个作业
     * */
    Observable<Response<Void>> uploadAssignment(AssignmentUpload assignment);



    /**
     * 上传文件
     * */
    Observable<Response<ImageFile>> uploadFile(String filePath);


    /**
     * 上传一张图片
     * */
    Observable<Response<ImageFile>> uploadImage(String imagePath);


    /***
     * 上传一组图片
     * */
    Observable<Response<ImageFile>> uploadImageSet(List<String> imagePath);

}
