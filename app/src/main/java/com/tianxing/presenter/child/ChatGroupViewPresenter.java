package com.tianxing.presenter.child;

import com.tianxing.entity.MessageFactory;
import com.tianxing.entity.message.GroupChatMessage;
import com.tianxing.model.App;
import com.tianxing.model.MessagePool;
import com.tianxing.model.communication.XmppClient;
import com.tianxing.model.communication.xmpp.MultiUserChatSession;
import com.tianxing.ui.fragment.child.ChatGroupView;

import java.util.Observable;
import java.util.Observer;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by tianxing on 16/8/2.
 */
public class ChatGroupViewPresenter implements ChatGroupPresenter, Observer{

    private String roomName;
    private XmppClient client;
    private MultiUserChatSession session;
    private ChatGroupView view;
    private MessagePool pool;

    public ChatGroupViewPresenter(ChatGroupView view, String roomName){
        this.view = view;
        this.roomName = roomName;
        client = App.getInstance().getXmppClient();
        session = client.createMultiUserChatSession(roomName);
        pool = App.getInstance().getMessagePool();
        pool.addMessageObserver(this);
    }



    @Override
    public void update(Observable observable, Object data) {
        if (data.equals(roomName)){
            view.onMessageListUpdate();
        }
    }

    /**
     * 取得相应会话的消息条数
     */
    @Override
    public int getMessageCount() {
        return pool.getGroupChatMessageCount(roomName);
    }

    @Override
    public GroupChatMessage getChatMessage(int position) {
        return pool.getGroupChatMessage(roomName, position);
    }

    /**
     * 发送一条消息
     *
     * @param message
     */
    @Override
    public void sendTextMessage(String message) {
        GroupChatMessage groupChatMessage = MessageFactory.CreateGroupTextMessage(message);
        groupChatMessage.setLocally(true);
        pool.putGroupChatMessage(roomName, groupChatMessage);
        sendGroupChatMessage(groupChatMessage);
    }

    @Override
    public void sendImageMessage(String message) {
        GroupChatMessage groupChatMessage = MessageFactory.CreateGroupImageMessage(message);
        groupChatMessage.setLocally(true);
        pool.putGroupChatMessage(roomName, groupChatMessage);
        sendGroupChatMessage(groupChatMessage);
    }

    @Override
    public void sendAudioMessage(String message) {
        GroupChatMessage groupChatMessage = MessageFactory.CreateGroupAudioMessage(message);
        groupChatMessage.setLocally(true);
        pool.putGroupChatMessage(roomName, groupChatMessage);
        sendGroupChatMessage(groupChatMessage);
    }
    
    
    private void sendGroupChatMessage(final GroupChatMessage message){
        rx.Observable.create(new rx.Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                session.sendMessage(message);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                //发送成功
            }

            @Override
            public void onError(Throwable e) {
                //发送失败
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
        pool.deleteMessageObserver(this);
    }
}
