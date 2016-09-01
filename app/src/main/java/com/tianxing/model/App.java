package com.tianxing.model;

import com.tianxing.model.communication.HttpClient;
import com.tianxing.model.communication.XmppClient;
import com.tianxing.model.communication.http.FscHttpClient;
import com.tianxing.model.communication.xmpp.FscXmppClient;
import com.tianxing.model.data.AssignmentDataPool;
import com.tianxing.model.data.Config;
import com.tianxing.model.data.ContactsDataPool;
import com.tianxing.model.data.MessageDataPool;
import com.tianxing.model.data.ReceivedMessageProcess;

/**
 * Created by tianxing on 16/7/8.
 */
public class App {

    private static App model = null;
    private Config config;

    private AssignmentPool assignmentPool;
    private ContactsPool contactsPool;
    private MessagePool messagePool;

    private HttpClient httpClient;
    private XmppClient xmppClient;

    private App(){}


    /**
     *
     * */
    public static App getInstance(){
        if (model == null){
            model = new App();
        }
        return model;
    }


    /**
     * 全局初始化
     * */
    public void initialize(Config config){
        this.config = config;
        assignmentPool = new AssignmentDataPool();//初始化作业查看数据池
        contactsPool = new ContactsDataPool();
        messagePool = new MessageDataPool();
        httpClient = new FscHttpClient();
        xmppClient = new FscXmppClient(new ReceivedMessageProcess(messagePool));
    }

    public Config getConfig() {
        return config;
    }

    public AssignmentPool getAssignmentPool() {
        return assignmentPool;
    }

    public ContactsPool getContactsPool() {
        return contactsPool;
    }

    public MessagePool getMessagePool() {
        return messagePool;
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }

    public XmppClient getXmppClient() {
        return xmppClient;
    }
}
