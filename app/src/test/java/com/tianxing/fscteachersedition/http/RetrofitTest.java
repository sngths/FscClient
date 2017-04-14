package com.tianxing.fscteachersedition.http;

import org.junit.Test;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;
import rx.subjects.Subject;

/**
 * Created by tianxing on 2017/4/3.
 */

public class RetrofitTest {


    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://www.google.com")//以"/"结尾
            .addConverterFactory(JacksonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();

    @Test
    public void syncRequest() {
        ServiceApi api = retrofit.create(ServiceApi.class);

        Call<ResponseBody> call = api.get();
        try {
            Response<ResponseBody> response = call.execute();
            System.out.println(response.code());
            System.out.println(response.message());
            System.out.println(response.headers());
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void asyncRequest() {
        ServiceApi api = retrofit.create(ServiceApi.class);
        Call<ResponseBody> call = api.get();
        final Thread thread = Thread.currentThread();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                System.out.println(response.message());
                synchronized (thread) {
                    thread.notify();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                synchronized (thread) {
                    thread.notify();
                }
            }
        });
        synchronized (thread) {
            try {
                thread.wait();
            } catch (InterruptedException e) {
                call.cancel();
                e.printStackTrace();
            }
        }


    }


    @Test
    public void rxRequest(){
        final ServiceApi api = retrofit.create(ServiceApi.class);
        api.getRx().subscribeOn(Schedulers.io())
                .observeOn(Schedulers.trampoline())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {
                        synchronized (api){
                            api.notify();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {

                        try {
                            System.out.println(responseBody.string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        synchronized (api){
                            api.notify();
                        }
                    }
                });
        synchronized (api){
            try {
                api.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }






    private interface ServiceApi {

        @GET("/")
        Call<ResponseBody> get();

        @GET("/")
        Observable<ResponseBody> getRx();

        @GET("/")
        Observable<Response<String>> getResponse();//只有网络出错才抛出异常
    }
}
