package com.tianxing.entity;

import com.tianxing.deprecated.entity.message.ChatMessage;
import com.tianxing.deprecated.entity.message.GroupChatMessage;
import com.tianxing.deprecated.entity.message.Message;

/**
 * Created by tianxing on 16/8/22.
 */
public class MessageFactory {

    //创建聊天消息
    public static ChatMessage CreateTextMessage(String content){
        ChatMessage message = new ChatMessage(content);
        message.setContentType(Message.TYPE_CONTENT_TEXT);
        return message;
    }
    public static ChatMessage CreateImageMessage(String content){
        ChatMessage message = new ChatMessage(content);
        message.setContentType(Message.TYPE_CONTENT_IMAGE);
        return message;
    }
    public static ChatMessage CreateAudioMessage(String content){
        ChatMessage message = new ChatMessage(content);
        message.setContentType(Message.TYPE_CONTENT_AUDIO);
        return message;
    }





    //创建群组聊天消息

    public static GroupChatMessage CreateGroupTextMessage(String content){
        GroupChatMessage message = new GroupChatMessage(content);
        message.setContentType(Message.TYPE_CONTENT_TEXT);
        return message;
    }
    public static GroupChatMessage CreateGroupImageMessage(String content){
        GroupChatMessage message = new GroupChatMessage(content);
        message.setContentType(Message.TYPE_CONTENT_IMAGE);
        return message;
    }
    public static GroupChatMessage CreateGroupAudioMessage(String content){
        GroupChatMessage message = new GroupChatMessage(content);
        message.setContentType(Message.TYPE_CONTENT_AUDIO);
        return message;
    }


    //消息转换
}
