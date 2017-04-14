package com.tianxing.model.communication.xmpp;

import android.util.Log;

import com.tianxing.pojo.MessageFactory;
import com.tianxing.exception.UnconnectedException;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smackx.muc.MultiUserChat;

import java.io.IOException;

/**
 * Created by tianxing on 16/8/17.
 * 处理异常抛出
 */
class FscXmppConnection extends XmppAbstractConnection{

    private static final String TAG = "FscXmppConnection";


    private MessageListener listener;

    public FscXmppConnection(MessageListener listener){
        this.listener = listener;
    }



    /**
     * 连接服务器
     */
    @Override
    public void connect() throws UnconnectedException {
        try {
            syncConnect();
            Log.e("FscXmppConnection", "连接成功");
        } catch (IOException | XMPPException | SmackException e) {
            e.printStackTrace();
            //连接失败
            throw new UnconnectedException();
        }
    }

    /**
     * 登录  包括加入房间
     */
    @Override
    public void login(String username, String password) {
        try {
            syncLogin(username, password);
            Log.e("FscXmppConnection", "登录成功");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XMPPException e) {
            e.printStackTrace();
        } catch (SmackException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建单人会话
     *
     * @param username
     */
    @Override
    public Chat chatCreate(String username) {
        return syncChatCreate(username);
    }

    /**
     * 创建多人会话
     *
     * @param roomName
     */
    @Override
    public MultiUserChat multiUserChatCreate(String roomName) {
        try {
            return syncMultiUserChatCreate(roomName);
        } catch (SmackException.NotConnectedException e) {
            e.printStackTrace();
        } catch (XMPPException.XMPPErrorException e) {
            e.printStackTrace();
        } catch (SmackException.NoResponseException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    void onMessageReceived(String username, Message message) {
        //创建消息类 转到主线程 写入消息
        if (!message.getSubject().equals("")){
            Log.e(TAG, "收到一条单人消息 消息内容：" + message.getBody() + "来自:" + username);
            listener.onChatMessageReceived(username, MessageFactory.CreateTextMessage(message.getBody()));
        }


    }

    @Override
    void onMultiUserMessageReceived(String roomName, Message message) {
        //创建消息类 转到主线程 写入消息
        if (!message.getSubject().equals("")){
            Log.e(TAG, "收到一条多人消息 消息内容：" + message.getBody());
            listener.onGroupChatMessageReceived(roomName, MessageFactory.CreateGroupTextMessage(message.getBody()));
        }
    }
}
