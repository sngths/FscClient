package com.tianxing.model.communication.xmpp;

import android.util.Log;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.ReconnectionManager;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.chat.ChatManagerListener;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smackx.muc.DiscussionHistory;
import org.jivesoftware.smackx.muc.MultiUserChat;
import org.jivesoftware.smackx.muc.MultiUserChatManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.WeakHashMap;

/**
 * Created by tianxing on 16/8/15.
 */
abstract class XmppAbstractConnection implements XmppConnection {

    private AbstractXMPPConnection connection;
    private ChatManager chatManager;
    private MultiUserChatManager multiUserChatManager;
    private XmppConfiguration configuration;


    private WeakHashMap<String, Chat> chatWeakHashMap = new WeakHashMap<>();
    private WeakHashMap<String, MultiUserChat> multiUserChatWeakHashMap = new WeakHashMap<>();

    private ArrayList<String> roomList = new ArrayList<>();


    @Override
    public void Initialize(XmppConfiguration configuration) {
        this.configuration = configuration;
        XMPPTCPConnectionConfiguration connectionConfiguration = XMPPTCPConnectionConfiguration.builder()
                .setCompressionEnabled(true)
                .setSecurityMode(ConnectionConfiguration.SecurityMode.disabled)
                .setSendPresence(true)
                .setConnectTimeout(15000)
                .setDebuggerEnabled(true)
                .setServiceName(configuration.getServiceName())
                .setHost(configuration.getHost())
                .setPort(configuration.getPort())
                .setResource(configuration.getResource())
                .build();
        connection = new XMPPTCPConnection(connectionConfiguration);
        ReconnectionManager reconnectionManager = ReconnectionManager.getInstanceFor(connection);
        reconnectionManager.enableAutomaticReconnection();
        connection.addConnectionListener(this);
        chatManager = ChatManager.getInstanceFor(connection);
        multiUserChatManager = MultiUserChatManager.getInstanceFor(connection);

    }


    @Override
    public String getServiceName() {
        return configuration.getServiceName();
    }


    @Override
    public String getRoomServiceName() {
        return configuration.getRoomServiceName();
    }

    protected void syncConnect() throws IOException, XMPPException, SmackException {
        if (!connection.isConnected()) {
            connection.connect();
        }
    }


    protected void syncLogin(String username, String password) throws IOException, XMPPException, SmackException {
        if (!connection.isConnected()) {
            connection.connect();
        }
        if (!connection.isAuthenticated()) {
            connection.login(username, password);
        }

        //添加单人会话监听

        chatManager.addChatListener(new ChatManagerListener() {
            @Override
            public void chatCreated(Chat chat, boolean createdLocally) {

                chat.addMessageListener(new ChatMessageListener() {
                    @Override
                    public void processMessage(Chat chat, Message message) {
                        onMessageReceived(chat.getParticipant().split("@")[0], message);
                    }
                });
                //保存会话
                chatWeakHashMap.put(chat.getParticipant(), chat);
            }
        });

        //所有房间添加多消息监听
        for (final String roomName : configuration.getRoomNameList()) {
            MultiUserChat multiUserChat = multiUserChatManager.getMultiUserChat(roomName + "@" + configuration.getRoomServiceName());
            multiUserChat.addMessageListener(new MessageListener() {
                @Override
                public void processMessage(Message message) {
                    onMultiUserMessageReceived(roomName, message);
                }
            });
        }
        joinRooms();
        syncNotifyAvailable();
    }


    /**
     * 加入房间 每次加入房间清空原先的数据
     */
    private void joinRooms() throws SmackException.NotConnectedException, XMPPException.XMPPErrorException, SmackException.NoResponseException {
        //清空房间数据


        for (String roomName : configuration.getRoomNameList()) {
            MultiUserChat chat = multiUserChatManager.getMultiUserChat(roomName + "@" + configuration.getRoomServiceName());
            DiscussionHistory history = new DiscussionHistory();
            history.setMaxStanzas(30);
            chat.join("群昵称", "", history, 5000);
        }

    }


    protected void syncNotifyAvailable() throws SmackException.NotConnectedException {
        Presence presence = new Presence(Presence.Type.available);
        presence.setStatus("在线状态");
        connection.sendStanza(presence);
        Log.e("TAG", "syncNotifyAvailable: 通知服务器用户上线");
    }


    /**
     * 创建单人会话
     */
    protected Chat syncChatCreate(String userName) {
        Chat chat = chatWeakHashMap.get(userName);
        if (chat != null) {
            return chat;
        }
        chat = chatManager.createChat(userName + "@" + connection.getServiceName());
        chatWeakHashMap.put(userName, chat);
        return chat;
    }


    /**
     * 创建多人会话
     */
    protected MultiUserChat syncMultiUserChatCreate(String roomName) throws SmackException.NotConnectedException, XMPPException.XMPPErrorException, SmackException.NoResponseException {
        MultiUserChat chat = multiUserChatWeakHashMap.get(roomName);
        if (chat != null) {
            return chat;
        }
        chat = multiUserChatManager.getMultiUserChat(roomName + "@" + configuration.getRoomServiceName());
        multiUserChatWeakHashMap.put(roomName, chat);
        if (!chat.isJoined()) {
            //加入房间
            DiscussionHistory history = new DiscussionHistory();
            history.setMaxStanzas(30);
            chat.join("群昵称", "", history, 5000);
        }
        return chat;
    }








    /*protected void syncSendMessage(String username, Message message) throws SmackException.NotConnectedException {
        String jid = username + "@" + connection.getServiceName();
        Chat chat = chatManager.createChat(jid, new ChatMessageListener() {
            @Override
            public void processMessage(Chat chat, Message message) {
                //收到一条消息
            }
        });
        chat.sendMessage(message);
    }*/


    protected void syncDisconnect() {
        connection.disconnect();
    }


    abstract void onMessageReceived(String username, Message message);

    abstract void onMultiUserMessageReceived(String roomName, Message message);


    @Override
    public void connected(XMPPConnection connection) {

    }

    @Override
    public void authenticated(XMPPConnection connection, boolean resumed) {

    }


    @Override
    public void connectionClosed() {

    }

    @Override
    public void connectionClosedOnError(Exception e) {

    }

    @Override
    public void reconnectionSuccessful() {

    }

    @Override
    public void reconnectingIn(int seconds) {

    }

    @Override
    public void reconnectionFailed(Exception e) {

    }

}
