package com.tianxing.model.communication;

import com.tianxing.model.communication.xmpp.ChatSession;
import com.tianxing.model.communication.xmpp.MultiUserChatSession;

/**
 * Created by tianxing on 16/8/15.
 * 在主线程下调用
 */
public interface XmppClient {




    /**
     * 登录
     * */
    void login(String username, String password);





    /**
     * 取得单人会话
     * */
    ChatSession createChatSession(String username);


    /**
     * 取得多人会话
     * */
    MultiUserChatSession createMultiUserChatSession(String roomName);




    /**
     * 添加连接状态监听 添加监听后立即返回当前的连接状态
     * */

}
