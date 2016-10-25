package com.tianxing.model.communication.xmpp;

/**
 * Created by tianxing on 16/9/8.
 * xmpp服务器 相关信息
 */
public class XmppServerInfo {

    private String serviceName = "raspberrypi3";
    private String host = "172.24.1.1";
    private Integer port = 5222;
    private String resource = "Smack";
    private String roomServiceName = "conference.raspberrypi3";
    private String username = "teacher1";
    private String password = "123456";


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

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


}
