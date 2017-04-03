package com.tianxing.model.communication.xmpp;

import android.util.Pair;

import com.tianxing.exception.UnconnectedException;
import com.tianxing.deprecated.XmppClient;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by tianxing on 16/8/15.
 * 创建之前要先通过Http 登陆获取 xmpp相关的配置信息
 */
public class FscXmppClient implements XmppClient {


    private XmppConnection connection;

    public FscXmppClient(MessageListener listener, XmppServerInfo serverInfo) {
        connection = new FscXmppConnection(listener);
        connection.Initialize(serverInfo);

    }


    /**
     * 登录
     *
     * @param username
     *
     * @param password
     */
    @Override
    public void login(String username, String password) {
        Pair<String, String> a = new Pair<>(username, password);

        Observable.just(a)
                .observeOn(Schedulers.io())
                .map(new Func1<Pair<String, String>, Object>() {
                    @Override
                    public Object call(Pair<String, String> stringStringPair) {
                        try {
                            connection.connect();
                        } catch (UnconnectedException e) {
                            e.printStackTrace();
                        }
                        connection.login(stringStringPair.first, stringStringPair.second);
                        return null;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                    }

                    @Override
                    public void onNext(Object o) {

                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    /**
     * 取得单人会话
     *
     * @param username
     */
    @Override
    public ChatSession createChatSession(String username) {

        return new ChatSession(connection, username);
    }

    /**
     * 取得多人会话
     *
     * @param roomName
     */
    @Override
    public MultiUserChatSession createMultiUserChatSession(String roomName) {
        return new MultiUserChatSession(connection, roomName);
    }
}
