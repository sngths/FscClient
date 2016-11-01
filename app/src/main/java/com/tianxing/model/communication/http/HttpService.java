package com.tianxing.model.communication.http;

import com.tianxing.entity.transfer.receive.AssignmentDownload;
import com.tianxing.entity.transfer.send.AssignmentUpload;
import com.tianxing.entity.http.json.ImageFile;
import com.tianxing.entity.transfer.receive.LoginInfo;
import com.tianxing.entity.transfer.receive.LoginResponse;
import com.tianxing.entity.transfer.receive.StudentInfoResponse;
import com.tianxing.entity.transfer.receive.TeacherInfoResponse;
import com.tianxing.entity.transfer.send.UsernameAndPassword;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by tianxing on 16/7/20.
 * Retrofit请求接口
 */
public interface HttpService {


    /**
     * 登录
     * */
    @POST("login")
    @Headers("Content-Type: application/json")
    Observable<LoginInfo> login(@Body UsernameAndPassword usernamendPassword);



    /**
     * 用户登录
     * */
    @POST("mlogin")
    @Headers("Content-Type: application/json")
    Observable<LoginResponse> userLogin(@Body UsernameAndPassword usernamendPassword);

    /**
     * 请求学生信息
     * */
    @GET("student/info")
    @Headers("Content-Type: application/json")
    Observable<StudentInfoResponse> studentInfoRequest(@Header("token") String token);
    /**
     * 请求老师信息
     * */
    @GET("teacher/info")
    @Headers("Content-Type: application/json")
    Observable<TeacherInfoResponse> teacherInfoRequest(@Header("token") String token);
    /**
     * 请求单个班级的作业列表
     */
    @GET("assignment/list")
    @Headers("Content-Type: application/json")
    Observable<Response<List<AssignmentDownload>>> reqestAssignmentList(@Header("token") String token, @Query("classID") String classID, @Query("serialNumber") String serialNumber);



    /**
     * 上传单个作业
     * */
    @POST("assignment/upload")
    @Headers("Content-Type: application/json")
    Observable<Response<Void>> uploadAssignment(@Header("token") String token, @Body AssignmentUpload assignment);


    /**
     * 单个文件上传  "\"file\"; filename=\"image.png\" "
     * */
    @Multipart
    @POST("upload")
    //@Headers("Content-Type: multipart/form-data;boundary=95416089-b2fd-4eab-9a14-166bb9c5788b")
    //@Headers("Content-Type: multipart/form-data")
    Observable<Response<ImageFile>> uploadFile(@Part(value = "file\"; filename=\"image.png\" ") RequestBody requestBody);


    /**
     * 上传一张图片
     * */
    @Multipart
    @POST("upload/image")
    Observable<Response<ImageFile>> uploadImage(@PartMap Map<String, RequestBody> params);

}
