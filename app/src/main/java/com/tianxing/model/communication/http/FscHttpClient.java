package com.tianxing.model.communication.http;

import com.tianxing.entity.transfer.Comment;
import com.tianxing.entity.transfer.receive.AssignmentDownload;
import com.tianxing.entity.transfer.receive.ReplyReceived;
import com.tianxing.entity.transfer.send.AssignmentUpload;
import com.tianxing.entity.http.json.ImageFile;
import com.tianxing.entity.info.StudentInfo;
import com.tianxing.entity.transfer.receive.LoginInfo;
import com.tianxing.entity.transfer.receive.LoginResponse;
import com.tianxing.entity.transfer.receive.StudentInfoResponse;
import com.tianxing.entity.transfer.receive.TeacherInfoResponse;
import com.tianxing.entity.transfer.send.ReplyUpload;
import com.tianxing.entity.transfer.send.UsernameAndPassword;
import com.tianxing.entity.info.PersonalInfo;
import com.tianxing.model.communication.HttpClient;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static java.io.File.separator;

/**
 * Created by tianxing on 16/7/5.
 * 为了方便测试 不包含安卓线程下的操作
 */
public class FscHttpClient implements HttpClient {

    private HttpConfiguration configuration;
    private HttpService service;


    private String token;
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
     *
     * @param username
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
     * 用户登录
     *
     * @param username
     * @param password
     */
    @Override
    public Observable<LoginResponse> userLogin(String username, String password) {
        UsernameAndPassword usernameAndPassword = new UsernameAndPassword();
        usernameAndPassword.setUsername(username);
        usernameAndPassword.setPassword(password);
        return service.userLogin(usernameAndPassword)
                .subscribeOn(Schedulers.io())
                .doOnNext(new Action1<LoginResponse>() {
                    @Override
                    public void call(LoginResponse loginResponse) {
                        //保存token
                        token = loginResponse.getToken();
                    }
                });
    }

    /**
     * 请求学生信息
     */
    @Override
    public Observable<StudentInfoResponse> requestStudentInfo() {
        return service.studentInfoRequest(token).subscribeOn(Schedulers.io());
    }

    /**
     * 请求老师信息
     */
    @Override
    public Observable<TeacherInfoResponse> requestTeacherInfo() {
        return service.teacherInfoRequest(token).subscribeOn(Schedulers.io());
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
    public Observable<Response<List<AssignmentDownload>>> requestAssignmentList(String classID, String serialNumber) {
        return service.reqestAssignmentList(token, classID, serialNumber).subscribeOn(Schedulers.io());
    }

    /**
     * 上传一个作业
     */
    @Override
    public Observable<Response<Void>> uploadAssignment(AssignmentUpload assignment) {
        return service.uploadAssignment(token, assignment).subscribeOn(Schedulers.io());
    }

    /**
     * 上传文件
     *
     * @param filePath
     */
    @Override
    public Observable<Response<ImageFile>> uploadFile(String filePath) {

        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), new File(filePath));
        return service.uploadFile(requestBody);

    }

    /**
     * 上传一张图片
     *
     * @param imagePath
     */
    @Override
    public Observable<Response<ImageFile>> uploadImage(String imagePath) {
        int separatorIndex = imagePath.lastIndexOf(separator);
        String imageName = (separatorIndex < 0) ? imagePath : imagePath.substring(separatorIndex + 1, imagePath.length());
        String name = "image\"; filename=\"" + imageName + "\"";
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), new File(imagePath));
        Map<String, RequestBody> requestBodyMap = new HashMap<>();
        requestBodyMap.put(name, requestBody);
        return service.uploadImage(requestBodyMap).subscribeOn(Schedulers.io());
    }

    /***
     * 上传一组图片
     *
     * @param imagePath
     */
    @Override
    public Observable<Response<ImageFile>> uploadImageSet(final List<String> imagePath) {

        return Observable.from(imagePath)
                .observeOn(Schedulers.io())
                .flatMap(new Func1<String, Observable<Response<ImageFile>>>() {
                    @Override
                    public Observable<Response<ImageFile>> call(String s) {
                        return uploadImage(s).subscribeOn(Schedulers.io());
                    }
                });
    }

    /**
     * 请求一条作业的回复学生列表
     *
     * @param assignmentID 该条作业的ID
     */
    @Override
    public Observable<Response<List<StudentInfo>>> requestReplyStudentList(String assignmentID) {
        return service.reqestReplyStudentList(token, assignmentID).subscribeOn(Schedulers.io());
    }

    /**
     * 请求一条作业的一个学生的回复
     *
     * @param assignmentID
     * @param studentID
     */
    @Override
    public Observable<Response<ReplyReceived>> requestReply(String assignmentID, String studentID) {
        return service.reqestReply(token, assignmentID, studentID).subscribeOn(Schedulers.io());
    }

    /**
     * 上传一条回复
     *
     * @param replyUpload
     */
    @Override
    public Observable<Response<ReplyReceived>> uploadReply(ReplyUpload replyUpload) {
        return service.uploadReply(token, replyUpload.getAssignmentID(), replyUpload).subscribeOn(Schedulers.io());
    }

    /**
     * 上传一条批阅
     *
     * @param comment
     */
    @Override
    public Observable<Response<Void>> uploadComment(Comment comment) {
        return service.uploadComment(token, comment).subscribeOn(Schedulers.io());
    }


}
