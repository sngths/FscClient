package com.tianxing.model.communication.xmpp;

import com.tianxing.deprecated.entity.message.ChatMessage;
import com.tianxing.deprecated.entity.message.GroupChatMessage;

/**
 * Created by tianxing on 16/8/24.
 * 监听接收到的消息 需要转换到主线程再写入数据
 */
public interface MessageListener {


    /**
     * 收到单人消息
     * */
    void onChatMessageReceived(String username, ChatMessage message);


    /**
     * 收到群聊消息
     * */
    void onGroupChatMessageReceived(String roomName, GroupChatMessage message);


}
