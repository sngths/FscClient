package com.tianxing.model.communication;

import com.tianxing.entity.transfer.receive.AssignmentDownload;
import com.tianxing.entity.transfer.send.AssignmentUpload;
import com.tianxing.entity.http.json.ImageFile;
import com.tianxing.entity.info.StudentInfo;
import com.tianxing.entity.transfer.receive.LoginInfo;
import com.tianxing.entity.info.PersonalInfo;
import com.tianxing.entity.transfer.receive.LoginResponse;
import com.tianxing.entity.transfer.receive.StudentInfoResponse;
import com.tianxing.entity.transfer.receive.TeacherInfoResponse;

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
     * 用户登录
     * */
    Observable<LoginResponse> userLogin(String username, String password);


    /**
     * 请求学生信息
     * */
    Observable<StudentInfoResponse> requestStudentInfo();

    /**
     * 请求老师信息
     * */
    Observable<TeacherInfoResponse> requestTeacherInfo();


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
    Observable<Response<List<AssignmentDownload>>> requestAssignmentList(String classID, String serialNumber);



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



    /**
     * 请求一条作业的回复学生列表
     * */
    Observable<Response<List<StudentInfo>>> requestReplyStudentList(String AssignmentID);


    /**
     * 请求一条作业的一个学生的回复
     * */

}
