package com.tianxing.model.communication.http.service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by tianxing on 2017/3/31.
 *
 */

public interface Service {

    @GET("/")
    @Headers("Content-Type: application/json")
    Call<ResponseBody> connect();

    @GET("/")
    @Headers("Content-Type: application/json")
    <T> Call<T> connect(Class<T> responseType);
}
