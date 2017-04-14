package com.tianxing.model.communication.http.service;

import com.tianxing.pojo.http.ResponseWrapper;

import retrofit2.Response;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by tianxing on 2017/3/31.
 *
 */

public interface AssignmentService extends Service{


    @POST("login")
     Observable<Response<ResponseWrapper>> login();



}
