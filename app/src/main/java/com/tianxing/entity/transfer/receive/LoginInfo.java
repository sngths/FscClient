package com.tianxing.entity.transfer.receive;

import com.tianxing.deprecated.entity.info.ClassInfo;
import com.tianxing.deprecated.entity.info.GroupInfo;
import com.tianxing.deprecated.entity.info.TeacherInfo;
import com.tianxing.deprecated.entity.info.UserInfo;
import com.tianxing.model.communication.xmpp.XmppServerInfo;

import java.util.ArrayList;

/**
 * Created by tianxing on 16/9/6.
 */
public class LoginInfo {
    private String refreshToken = "";
    private String token = "";

    private XmppServerInfo xmppServerInfo;

    private TeacherInfo teacherInfo;

    private ArrayList<GroupInfo> groups;

    private ArrayList<ClassInfo> classes;
    private ArrayList<UserInfo> friends;




    public String getRefreshToken() {
        return refreshToken;
    }

    public String getToken() {
        return token;
    }

    public XmppServerInfo getXmppServerInfo() {
        return xmppServerInfo;
    }

    public ArrayList<GroupInfo> getGroups() {
        return groups;
    }

    public TeacherInfo getTeacherInfo() {
        return teacherInfo;
    }

    public ArrayList<ClassInfo> getClasses() {
        return classes;
    }

    public ArrayList<UserInfo> getFriends() {
        return friends;
    }
}
