package com.tianxing.model.communication;

import com.tianxing.model.communication.http.api.RetrofitApi;
import com.tianxing.model.communication.http.api.RxApi;
import com.tianxing.model.communication.http.service.Service;

import retrofit2.Call;
import rx.Observable;

/**
 * Created by tianxing on 2017/3/30.
 * 不包含具体的 请求方法
 */

public interface HttpClient {




    /**
     * 尝试连接服务器
     * */


    /**
     * 取得网络请求接口
     * */
    <R extends RxApi> R getApiService(Class<R> service);

    /**
     * 取得Retrofit类型API
     * */
    <R extends RetrofitApi> R getRetrofitApiService(Class<R> service);

    /**
     * 取得Rx类型API
     * */
    <R extends RxApi> R getRxApiService(Class<R> service);

    <R extends RxApi, T>void getRxApi(R r);
}
