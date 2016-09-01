package com.tianxing.model.communication.xmpp;

import java.util.ArrayList;

/**
 * Created by tianxing on 16/8/15.
 * xmpp服务器配置参数
 */
public class XmppConfiguration {


    private static XmppConfiguration configuration;
    private String serviceName = "raspberrypi3";
    private String host = "172.24.1.1";
    private Integer port = 5222;
    private String resource = "Smack";
    private String username;
    private String password;
    private String roomServiceName = "conference.raspberrypi3";

    private ArrayList<String> roomNameList = new ArrayList<>();


    private XmppConfiguration(){
        roomNameList.add("c1d1");
    }


    public static XmppConfiguration getInstance(){
        if (configuration == null){
            configuration = new XmppConfiguration();
        }
        return configuration;
    }


    public String getServiceName() {
        return serviceName;
    }

    public String getHost() {
        return host;
    }

    public Integer getPort() {
        return port;
    }

    public String getResource() {
        return resource;
    }

    public String getRoomServiceName() {
        return roomServiceName;
    }

    public ArrayList<String> getRoomNameList() {
        return roomNameList;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
