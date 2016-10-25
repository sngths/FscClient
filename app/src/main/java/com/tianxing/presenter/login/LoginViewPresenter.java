package com.tianxing.presenter.login;

import android.util.Log;

import com.tianxing.entity.http.json.LoginInfo;
import com.tianxing.entity.info.ClassInfo;
import com.tianxing.entity.info.GroupInfo;
import com.tianxing.entity.info.UserInfo;
import com.tianxing.model.App;
import com.tianxing.model.AssignmentPool;
import com.tianxing.model.ContactsPool;
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

    public LoginViewPresenter(LoginView view) {
        this.view = view;
        client = App.getInstance().getHttpClient();
        contactsPool = App.getInstance().getContactsPool();
        assignmentPool = App.getInstance().getAssignmentPool();
    }

    /**
     * 登陆
     *
     * @param username
     * @param password
     */
    @Override
    public void login(String username, String password) {
        client.Login(username, password)
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
                            assignmentPool.createClassData(classInfo.getTitle());
                        }
                    }
                });
    }
}
