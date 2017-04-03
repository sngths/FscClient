package com.tianxing.model.communication.http;

import com.tianxing.model.communication.HttpClient;
import com.tianxing.model.communication.http.api.RetrofitApi;
import com.tianxing.model.communication.http.api.RxApi;

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
     * 取得网络请求接口
     *
     * @param service
     */
    @Override
    public <R extends RxApi> R getApiService(Class<R> service) {
        return retrofit.create(service);
    }

    /**
     * 取得Retrofit类型API
     *
     * @param service
     */
    @Override
    public <R extends RetrofitApi> R getRetrofitApiService(Class<R> service) {
        return retrofit.create(service);
    }

    /**
     * 取得Rx类型API
     *
     * @param service
     */
    @Override
    public <R extends RxApi> R getRxApiService(Class<R> service) {
        return retrofit.create(service);
    }

    @Override
    public <R extends RxApi, T> void getRxApi(R r) {

    }


}