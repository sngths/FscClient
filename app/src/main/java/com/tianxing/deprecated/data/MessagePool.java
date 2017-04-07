package com.tianxing.deprecated.data;

import com.tianxing.deprecated.entity.message.ChatMessage;
import com.tianxing.deprecated.entity.message.GroupChatMessage;

import java.util.Observer;

/**
 * Created by tianxing on 16/8/18.
 */
public interface MessagePool {






    /**
     * 添加一个单人用户
     * */
    void putUser(String username);

    /**
     * 添加一个房间
     * */
    void putRoom(String roomName);



    /**
     * 添加一条单人会话消息
     * */
    void putChatMessage(String username, ChatMessage message);



    /**
     * 添加一条群组会话消息
     * */
    void putGroupChatMessage(String roomName, GroupChatMessage message);

    /**
     * 添加消息观察 监听消息变化
     * */
    void addMessageObserver(Observer observer);

    /**
     * 移除消息观察
     * */
    void deleteMessageObserver(Observer observer);


    /**
     * 获取单人会话消息条数
     * */
    int getChatMessageCount(String username);

    /**
     * 取得多人会话消息条数
     * */
    int getGroupChatMessageCount(String roomName);

    /**
     * 取得一条单人会话消息
     * */
    ChatMessage getChatMessage(String username, int position);

    /**
     * 取得一条多人会话消息
     * */
    GroupChatMessage getGroupChatMessage(String roomName, int position);


}
