package com.tianxing.deprecated.entity.message;

/**
 * Created by tianxing on 16/8/22.
 */
public interface Message {
    int TYPE_CHAT = 1;
    int TYPE_GROUP_CHAT = 2;

    int TYPE_CONTENT_TEXT = 1;
    int TYPE_CONTENT_IMAGE = 2;
    int TYPE_CONTENT_AUDIO = 3;

    String XMPP_SUBJECT_TEXT = "text";
    String XMPP_SUBJECT_IMAGE = "image";
    String XMPP_SUBJECT_AUDIO= "audio";


    int getMessageType();

    int getContentType();

    String getContent();

    void setContentType(int type);

    /**
     * 是否为本地消息 本地创建的消息用于发送
     * @return 本地创建的消息 返回true  从远程接收到的消息  返回false
     * */
    boolean isLocally();
}
