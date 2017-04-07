package com.tianxing.model.communication.xmpp;

import com.tianxing.deprecated.entity.message.GroupChatMessage;
import com.tianxing.exception.UnconnectedException;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smackx.muc.MultiUserChat;

/**
 * Created by tianxing on 16/8/16.
 */
public class MultiUserChatSession {

    private XmppConnection connection;
    private MultiUserChat chat;
    private String roomName;

    public MultiUserChatSession(XmppConnection connection, String roomName){
        this.connection = connection;
        this.roomName = roomName;
        chat = connection.multiUserChatCreate(roomName);
    }


    public String getRoomName(){
        return roomName;
    }


    /**
     * 发送消息
     * */
    public void sendMessage(GroupChatMessage message){
        Message message1 = new Message();
        message1.setBody(message.getContent());
        if (message.getContentType() == GroupChatMessage.TYPE_CONTENT_TEXT){
            message1.setSubject(GroupChatMessage.XMPP_SUBJECT_TEXT);
        }else if (message.getContentType() == GroupChatMessage.TYPE_CONTENT_IMAGE){
            message1.setSubject(GroupChatMessage.XMPP_SUBJECT_IMAGE);
        }else if (message.getContentType() == GroupChatMessage.TYPE_CONTENT_AUDIO){
            message1.setSubject(GroupChatMessage.XMPP_SUBJECT_AUDIO);
        }

        try {
            chat.sendMessage(message1);
        } catch (SmackException.NotConnectedException e) {
            e.printStackTrace();
            throw new UnconnectedException(e.getMessage());
        }
    }
}
