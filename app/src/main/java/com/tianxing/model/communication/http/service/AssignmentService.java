package com.tianxing.model.communication.http.service;

import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by tianxing on 2017/3/31.
 *
 */

public interface AssignmentService extends Service{


    @POST("login")
     <R> R get(R responseType, String s);



}
