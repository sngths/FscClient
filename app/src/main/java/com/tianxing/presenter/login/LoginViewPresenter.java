package com.tianxing.presenter.login;

import android.util.Log;

import com.tianxing.entity.info.StudentInfo;
import com.tianxing.entity.info.TeacherInfo;
import com.tianxing.entity.transfer.receive.LoginInfo;
import com.tianxing.entity.info.ClassInfo;
import com.tianxing.entity.info.GroupInfo;
import com.tianxing.entity.info.UserInfo;
import com.tianxing.entity.transfer.receive.LoginResponse;
import com.tianxing.entity.transfer.receive.StudentInfoResponse;
import com.tianxing.entity.transfer.receive.TeacherInfoResponse;
import com.tianxing.model.App;
import com.tianxing.model.AssignmentPool;
import com.tianxing.model.ContactsPool;
import com.tianxing.model.MessagePool;
import com.tianxing.model.communication.HttpClient;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by tianxing on 16/9/1.
 */
public class LoginViewPresenter implements LoginPresenter {
    private static final String TAG = "LoginViewPresenter";


    private LoginView view;
    private HttpClient client;
    private ContactsPool contactsPool;
    private AssignmentPool assignmentPool;
    private MessagePool messagePool;

    private LoginResponse mLoginResponse;

    public LoginViewPresenter(LoginView view) {
        this.view = view;
        client = App.getInstance().getHttpClient();
        contactsPool = App.getInstance().getContactsPool();
        assignmentPool = App.getInstance().getAssignmentPool();
        messagePool = App.getInstance().getMessagePool();
    }

    /**
     * 登陆
     *
     * @param username
     * @param password
     */
    @Override
    public void login(String username, String password) {
        client.userLogin(username, password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LoginResponse>() {
                    @Override
                    public void onCompleted() {
                        //登陆完成 请求用户信息
                        Log.e(TAG, "登陆完成 开始请求用户个人信息  " + "用户类型：" + mLoginResponse.getUserType());
                        Log.e(TAG, "token:" + mLoginResponse.getToken() + "  时间：" + mLoginResponse.getDate());
                        if (mLoginResponse.getUserType().equals("teacher")){
                            requestTeacherInfo();
                        }else if (mLoginResponse.getUserType().equals("student")){
                            requestStudentInfo();
                        }else {

                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(LoginResponse loginResponse) {
                        mLoginResponse = loginResponse;
                    }
                });









        /*client.Login(username, password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LoginInfo>() {
                    @Override
                    public void onCompleted() {
                        //登陆完成 通知界面跳转
                        view.startMainActivity();
                        Log.e(TAG, "登陆成功");
                    }

                    @Override
                    public void onError(Throwable e) {
                        //登陆出错
                        Log.e(TAG, "登陆失败   " + e.getMessage());
                    }

                    @Override
                    public void onNext(LoginInfo loginInfo) {
                        //在主线程 保存登陆后获取到的数据
                        //xmpp服务器信息
                        Log.e(TAG, "取得消息");
                        App.getInstance().setXmppServerInfo(loginInfo.getXmppServerInfo());
                        //群组
                        for (GroupInfo groupInfo : loginInfo.getGroups()) {
                            contactsPool.putGroupInfo(groupInfo);
                        }
                        //好友
                        for (UserInfo userInfo : loginInfo.getFriends()) {
                            contactsPool.putFriendInfo(userInfo);
                        }
                        //班级
                        for (ClassInfo classInfo : loginInfo.getClasses()) {
                            contactsPool.putClassInfo(classInfo);
                            assignmentPool.createClassData(classInfo.getName());
                        }
                    }
                });*/
    }



    /**
     * 获取老师用户信息 并启动老师主界面
     * */
    private void requestTeacherInfo(){
        Log.e(TAG, "开始请求老师个人信息");
        client.requestTeacherInfo()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<TeacherInfoResponse>() {
                    @Override
                    public void onCompleted() {
                        //请求完成 启动主界面
                        view.startMainActivity();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(TeacherInfoResponse teacherInfoResponse) {
                        //保存请求到的数据
                        Log.e(TAG, "保存请求到的数据");
                        saveTeacherInfo(teacherInfoResponse);
                    }
                });
    }

    /**
     * 获取学生用户信息 并启动学生主界面
     * */
    private void requestStudentInfo(){
        client.requestStudentInfo().observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<StudentInfoResponse>() {
                    @Override
                    public void onCompleted() {
                        //启动学生界面
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(StudentInfoResponse studentInfoResponse) {
                        //保存学生个人信息
                        saveStudentInfo(studentInfoResponse);
                    }
                });
    }


    /**
     * 保存学生相关信息
     * */
    private void saveStudentInfo(StudentInfoResponse response){

    }


    /**
     * 保存老师相关信息
     * */
    private void saveTeacherInfo(TeacherInfoResponse response){
        //xmpp服务器信息
        App.getInstance().setXmppServerInfo(response.getXmppServerInfo());

        //好友
        for (UserInfo userInfo : response.getFriends()) {
            contactsPool.putFriendInfo(userInfo);
            messagePool.putUser(userInfo.getUserName());
        }
        //班级
        for (ClassInfo classInfo : response.getClasses()) {
            contactsPool.putClassInfo(classInfo);
            assignmentPool.createClassData(classInfo);
            contactsPool.putGroupInfo(classInfo);
            //创建消息池的 用户和房间
            messagePool.putRoom(classInfo.getRoomID());
            for (UserInfo userInfo:classInfo.getStudents()) {
                messagePool.putUser(userInfo.getUserName());
            }
            for (UserInfo userInfo: classInfo.getTeachers()) {
                messagePool.putUser(userInfo.getUserName());
            }
        }
        //群组
        for (GroupInfo groupInfo : response.getGroups()) {
            contactsPool.putGroupInfo(groupInfo);

            messagePool.putRoom(groupInfo.getRoomID());
        }
    }

}
