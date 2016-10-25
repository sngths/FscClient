package com.tianxing.model.communication.http;

import com.tianxing.entity.assignment.Assignment;
import com.tianxing.entity.http.json.ImageFile;
import com.tianxing.entity.http.json.LoginInfo;
import com.tianxing.entity.http.json.UsernameAndPassword;
import com.tianxing.entity.info.PersonalInfo;
import com.tianxing.model.communication.HttpClient;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by tianxing on 16/7/5.
 * 为了方便测试 不包含安卓线程下的操作
 */
public class FscHttpClient implements HttpClient {

    private HttpConfiguration configuration;
    private HttpService service;


    private String token = "000000";
    private String refreshToken;


    public FscHttpClient() {
        this.configuration = HttpConfiguration.getInstance();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(configuration.getBaseUrl())
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        service = retrofit.create(HttpService.class);
    }


    @Override
    public String getToken() {
        return token;
    }

    @Override
    public String getRefreshToken() {
        return refreshToken;
    }



    /**
     * 登录
     *  @param username
     * @param password
     */
    @Override
    public Observable<LoginInfo> Login(String username, String password) {
        UsernameAndPassword usernameAndPassword = new UsernameAndPassword();
        usernameAndPassword.setUsername(username);
        usernameAndPassword.setPassword(password);
        return service.login(usernameAndPassword)
                .subscribeOn(Schedulers.io())
                .doOnNext(new Action1<LoginInfo>() {
                    @Override
                    public void call(LoginInfo loginInfo) {
                        //保存Token 和 RefreshToken
                        token = loginInfo.getToken();
                        refreshToken = loginInfo.getRefreshToken();
                    }
                });

    }


    /**
     * 认证
     *
     * @param refreshToken
     */
    @Override
    public void authenticate(String refreshToken) {

    }



    /**
     * 请求用户信息 包含班级信息
     */
    @Override
    public PersonalInfo getPersonalInfo() {
        return null;
    }

    /**
     * 请求单个班级作业数据
     *
     * @param classID
     * @param serialNumber
     */
    @Override
    public Observable<Response<List<Assignment>>> requestAssignmentList(String classID, Long serialNumber) {
        return service.reqestAssignmentList(token, classID, serialNumber).subscribeOn(Schedulers.io());
    }

    /**
     * 上传一个作业
     */
    @Override
    public Observable<Response<Void>> uploadAssignment(Assignment assignment) {
        return service.uploadAssignment(token, assignment).subscribeOn(Schedulers.io());
    }

    /**
     * 上传图像
     *
     * @param filePath
     */
    @Override
    public Observable<Response<ImageFile>> uploadFile(String filePath) {

        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), new File(filePath));
         return service.uploadFile(requestBody);

    }


}
