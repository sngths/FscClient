package com.tianxing.presenter.child;

import com.tianxing.entity.message.ChatMessage;

/**
 * Created by tianxing on 16/8/2.
 */
public interface ChatPresenter {



    /**
     * 取得相应会话的消息条数
     * */
    int getMessageCount();

    ChatMessage getChatMessage(int position);



    /**
     * 添加变化消息监听
     * */



    /**
     * 发送一条消息
     * */
    void sendTextMessage(String message);
    void sendImageMessage(String message);
    void sendAudioMessage(String message);


    /**
     * 销毁
     * */
    void onDestroyView();
}
