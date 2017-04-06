package com.tianxing.model.communication.http;

import com.tianxing.model.communication.HttpClient;
import com.tianxing.model.communication.http.api.RetrofitApi;
import com.tianxing.model.communication.http.api.RxApi;
import com.tianxing.model.communication.http.service.Service;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by tianxing on 2017/3/31.
 *
 */

public class HttpClientImpl implements HttpClient {

    private final Retrofit retrofit;

    public HttpClientImpl(String baseUrl) {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }


    /**
     * 取得服务接口
     *
     * @param service
     */
    @Override
    public <T extends Service> T getService(Class<T> service) {
        return retrofit.create(service);
    }
}