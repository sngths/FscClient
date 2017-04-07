package com.tianxing.deprecated.data;

import com.tianxing.deprecated.entity.message.ChatMessage;
import com.tianxing.deprecated.entity.message.GroupChatMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by tianxing on 16/8/18.
 *
 */
public class MessageDataPool extends Observable implements MessagePool {
    private static final String TAG = "MessageDataPool";

    private Map<String, ArrayList<ChatMessage>> chatMessagePool = new HashMap<>();
    private Map<String, ArrayList<GroupChatMessage>> groupChatMessagePool = new HashMap<>();

    public MessageDataPool(){
        //启动时存入一些好友数据 好友关系列表在登录完成后 从服务器获取


        /*putUser("user1");
        putUser("user2");
        putUser("user3");
        putUser("user4");
        putUser("user5");
        putUser("user6");
        putUser("user7");
        putUser("user8");
        putUser("user9");
        putUser("user10");

        putRoom("c1d1");*/

    }

    /**
     * 添加一个单人用户
     *
     * @param username
     */
    @Override
    public void putUser(String username) {
        chatMessagePool.put(username, new ArrayList<ChatMessage>());
    }

    /**
     * 添加一个房间
     *
     * @param roomName
     */
    @Override
    public void putRoom(String roomName) {
        groupChatMessagePool.put(roomName, new ArrayList<GroupChatMessage>());
    }

    /**
     * 添加一条单人会话消息
     *
     * @param message
     */
    @Override
    public void putChatMessage(String username, ChatMessage message) {
        ArrayList<ChatMessage> chatMessageArrayList = chatMessagePool.get(username);
        if (chatMessageArrayList != null){
            chatMessageArrayList.add(message);
        }
        //通知UI层
        setChanged();
        notifyObservers(username);
    }

    /**
     * 添加一条毒人会话消息
     *
     * @param message
     */
    @Override
    public void putGroupChatMessage(String roomName, GroupChatMessage message) {
        ArrayList<GroupChatMessage> groupChatMessageArrayList = groupChatMessagePool.get(roomName);
        if (groupChatMessageArrayList != null){
            groupChatMessageArrayList.add(message);
        }
        //通知UI层
        setChanged();
        notifyObservers(roomName);

    }

    /**
     * 添加消息观察 监听消息变化
     *
     * @param observer
     */
    @Override
    public void addMessageObserver(Observer observer) {
        addObserver(observer);
    }

    /**
     * 移除消息观察
     *
     * @param observer
     */
    @Override
    public void deleteMessageObserver(Observer observer) {
        deleteObserver(observer);
    }

    /**
     * 获取单人会话消息条数
     *
     * @param username
     */
    @Override
    public int getChatMessageCount(String username) {
        return chatMessagePool.get(username).size();
    }

    /**
     * 取得多人会话消息条数
     *
     * @param roomName
     */
    @Override
    public int getGroupChatMessageCount(String roomName) {
        return groupChatMessagePool.get(roomName).size();
    }

    /**
     * 取得一条单人会话消息
     *
     * @param username
     * @param position
     */
    @Override
    public ChatMessage getChatMessage(String username, int position) {
        return chatMessagePool.get(username).get(position);
    }

    /**
     * 取得一条多人会话消息
     *
     * @param roomName
     * @param position
     */
    @Override
    public GroupChatMessage getGroupChatMessage(String roomName, int position) {
        return groupChatMessagePool.get(roomName).get(position);
    }

}
