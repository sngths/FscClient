package com.tianxing.deprecated.entity.message;

/**
 * Created by tianxing on 16/8/19.
 */
public class ChatMessage implements Message {

    private int contentType = 1;//默认为文字消息

    private String content = "";
    private boolean locally = false;


    public ChatMessage(String content){
        this.content = content;
    }

    @Override
    public int getMessageType() {
        return TYPE_CHAT;
    }

    @Override
    public int getContentType() {
        return contentType;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public void setContentType(int type) {
        contentType = type;
    }

    /**
     * 是否为本地消息 本地创建的消息用于发送
     *
     * @return 本地创建的消息 返回true  从远程接收到的消息  返回false
     */
    @Override
    public boolean isLocally() {
        return locally;
    }

    public void setLocally(boolean locally) {
        this.locally = locally;
    }
}
