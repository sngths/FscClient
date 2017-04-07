package com.tianxing.data;

import com.tianxing.deprecated.data.MessagePool;
import com.tianxing.deprecated.entity.message.ChatMessage;
import com.tianxing.deprecated.entity.message.GroupChatMessage;
import com.tianxing.model.communication.xmpp.MessageListener;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by tianxing on 16/8/24.
 *
 */
public class ReceivedMessageProcess implements MessageListener {

    private MessagePool pool;

    public ReceivedMessageProcess(MessagePool pool){
        this.pool = pool;
    }


    /**
     * 收到单人消息
     *
     * @param username
     * @param message
     */
    @Override
    public void onChatMessageReceived(final String username, final ChatMessage message) {
        Observable.create(new Observable.OnSubscribe<ChatMessage>() {
            @Override
            public void call(Subscriber<? super ChatMessage> subscriber) {
                subscriber.onNext(message);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<ChatMessage>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ChatMessage message) {
                pool.putChatMessage(username, message);
            }
        });
    }

    /**
     * 收到群聊消息
     *
     * @param roomName
     * @param message
     */
    @Override
    public void onGroupChatMessageReceived(final String roomName, final GroupChatMessage message) {
        Observable.create(new Observable.OnSubscribe<GroupChatMessage>() {
            @Override
            public void call(Subscriber<? super GroupChatMessage> subscriber) {
                subscriber.onNext(message);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GroupChatMessage>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(GroupChatMessage message) {
                pool.putGroupChatMessage(roomName, message);
            }
        });
    }
}
