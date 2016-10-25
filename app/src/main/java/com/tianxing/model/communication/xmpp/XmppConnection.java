package com.tianxing.model.communication.xmpp;

import com.tianxing.exception.UnconnectedException;

import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smackx.muc.MultiUserChat;

/**
 * Created by tianxing on 16/8/15.
 * 即时通讯接口 实现同步网络操作
 */
interface XmppConnection extends ConnectionListener{






    /**
     * 连接初始化 从服务端获取Xmpp连接参数 初始化时添加消息监听
     * */
    void Initialize(XmppServerInfo serverInfo);



    /**
     * 是否已连接
     * */


    /**
     * 是否已登录
     * */

    /**
     * 取得服务器名称
     * */
    String getServiceName();


    /**
     * 取得房间服务器名称
     * */
    String getRoomServiceName();

    /**
     * 连接服务器
     * */
    void connect() throws UnconnectedException;



    /**
     * 登录  包括加入房间
     * */
    void login(String username, String password);


    /**
     * 创建单人会话
     * */
    Chat chatCreate(String username);


    /**
     * 创建多人会话
     * */
    MultiUserChat multiUserChatCreate(String roomName);


    /**
     * 添加连接状态监听
     * */



    /**
     * 添加消息监听
     * */


}
