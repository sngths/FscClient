package com.tianxing.model.communication.http.service;

import com.tianxing.pojo.http.ResponseWrapper;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by tianxing on 2017/4/6.
 *
 */

public interface AccountService extends Service{



    /**
     * 账户认证 取得refresh_token和账户基本信息
     * */
    @POST("login/result")
    Observable<Response<ResponseWrapper>> authenticate();


    /**
     * 账户授权 通过上传refresh_token 取得新token
     * */
    @POST("login/token")
    Observable<Response<ResponseWrapper>> authorize();


    /**
     * 取得老师用户的账户信息
     * */
    @GET("account/teacher")
    Observable<Response<ResponseWrapper>> teacherAccount();


    /**
     * 取得学生用户的账户信息
     * */
    @GET("account/student")
    Observable<Response<ResponseWrapper>> studentAccount();


}
