package com.tianxing.model.communication.http;

import com.tianxing.entity.assignment.AssignmentDownload;
import com.tianxing.entity.assignment.AssignmentUpload;
import com.tianxing.entity.http.json.ImageFile;
import com.tianxing.entity.http.json.LoginInfo;
import com.tianxing.entity.http.json.UsernameAndPassword;

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


    @POST("login")
    @Headers("Content-Type: application/json")
    Observable<LoginInfo> login(@Body UsernameAndPassword usernameAndPassword);


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
