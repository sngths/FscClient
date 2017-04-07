package com.tianxing.presenter.child;

import android.util.Log;

import com.tianxing.entity.MessageFactory;
import com.tianxing.deprecated.entity.message.ChatMessage;
import com.tianxing.model.App;
import com.tianxing.deprecated.data.MessagePool;
import com.tianxing.deprecated.XmppClient;
import com.tianxing.model.communication.xmpp.ChatSession;
import com.tianxing.ui.fragment.child.ChatView;

import java.util.Observable;
import java.util.Observer;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by tianxing on 16/8/2.
 */
public class ChatViewPresenter implements ChatPresenter, Observer {
    private static final String TAG = "ChatViewPresenter";

    private String username;
    private MessagePool messagePool;
    private XmppClient xmppClient;
    private ChatSession session;
    private ChatView view;


    public ChatViewPresenter(ChatView view, String username) {
        messagePool = App.getInstance().getMessagePool();
        xmppClient = App.getInstance().getXmppClient();
        session = xmppClient.createChatSession(username);//
        messagePool.addMessageObserver(this);
        this.view = view;
        this.username = username;
    }


    @Override
    public void update(Observable observable, Object data) {
        if (((String)data).equals(username)){
            view.onMessageListUpdate();
        }
    }

    /**
     * 取得相应会话的消息条数
     */
    @Override
    public int getMessageCount() {
        return messagePool.getChatMessageCount(username);
    }

    @Override
    public ChatMessage getChatMessage(int position) {

        return messagePool.getChatMessage(username, position);
    }

    /**
     * 发送一条消息 将消息写入数据池 转到IO线程发送 再转到主线程返回发送结果
     *
     * @param message
     */
    @Override
    public void sendTextMessage(String message) {
        //数据包装 放入数据池
        ChatMessage chatMessage = MessageFactory.CreateTextMessage(message);
        chatMessage.setLocally(true);
        messagePool.putChatMessage(username, chatMessage);
        sendChatMessage(chatMessage);
    }


    @Override
    public void sendImageMessage(String message) {
        ChatMessage chatMessage = MessageFactory.CreateImageMessage(message);
        chatMessage.setLocally(true);
        messagePool.putChatMessage(username, chatMessage);
        sendChatMessage(chatMessage);
    }

    @Override
    public void sendAudioMessage(String message) {
        ChatMessage chatMessage = MessageFactory.CreateAudioMessage(message);
        chatMessage.setLocally(true);
        messagePool.putChatMessage(username, chatMessage);
        sendChatMessage(chatMessage);
    }


    /**
     *
     * */
    private void sendChatMessage(final ChatMessage message) {
        rx.Observable
                .create(new rx.Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        session.sendMessage(message);
                        subscriber.onCompleted();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        //发送成功
                        Log.e(TAG, "发送成功");
                    }

                    @Override
                    public void onError(Throwable e) {
                        //发送失败
                        Log.e(TAG, "发送失败");
                    }

                    @Override
                    public void onNext(String s) {

                    }
                });
    }

    /**
     * 销毁
     */
    @Override
    public void onDestroyView() {
        messagePool.deleteMessageObserver(this);
    }
}
