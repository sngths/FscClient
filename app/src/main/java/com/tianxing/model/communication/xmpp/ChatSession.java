package com.tianxing.model.communication.xmpp;

import com.tianxing.entity.message.ChatMessage;
import com.tianxing.exception.UnconnectedException;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.packet.Message;

/**
 * Created by tianxing on 16/8/16.
 * 聊天页面
 */
public class ChatSession {


    private XmppConnection connection;
    private Chat chat;
    private String username;


    public ChatSession(XmppConnection connection, String username){
        this.connection = connection;
        this.username = username;
        chat = connection.chatCreate(username);
    }

    public String getUsername() {
        return username;
    }

    /**
     * 用户JID
     * */
    public String getJid(){
        return username + "@" + connection.getServiceName();
    }


    /**
     * 发送一条消息
     * */
    public void sendMessage(ChatMessage message){
        Message message1 = new Message();
        message1.setBody(message.getContent());
        if (message.getContentType() == ChatMessage.TYPE_CONTENT_TEXT){
            message1.setSubject(com.tianxing.entity.message.Message.XMPP_SUBJECT_TEXT);
        }else if (message.getContentType() == ChatMessage.TYPE_CONTENT_IMAGE){
            message1.setSubject(com.tianxing.entity.message.Message.XMPP_SUBJECT_IMAGE);
        }else if (message.getContentType() == ChatMessage.TYPE_CONTENT_AUDIO){
            message1.setSubject(com.tianxing.entity.message.Message.XMPP_SUBJECT_AUDIO);
        }
        try {
            chat.sendMessage(message1);
        } catch (SmackException.NotConnectedException e) {
            e.printStackTrace();
            throw  new UnconnectedException(e.getMessage());
        }

    }


    public void sendMessage(String message){
        try {
            chat.sendMessage(message);
        } catch (SmackException.NotConnectedException e) {
            e.printStackTrace();
            throw new UnconnectedException(e.getMessage());
        }
    }
}
