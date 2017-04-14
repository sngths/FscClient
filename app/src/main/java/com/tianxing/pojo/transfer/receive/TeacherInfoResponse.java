package com.tianxing.pojo.transfer.receive;

import com.tianxing.deprecated.entity.info.ClassInfo;
import com.tianxing.deprecated.entity.info.GroupInfo;
import com.tianxing.deprecated.entity.info.TeacherInfo;
import com.tianxing.deprecated.entity.info.UserInfo;
import com.tianxing.model.communication.xmpp.XmppServerInfo;

import java.util.List;

/**
 * Created by tianxing on 16/10/29.
 * 从服务器端获取的老师用户的个人信息
 */

public class TeacherInfoResponse {
    private XmppServerInfo xmppServerInfo;//xmpp通信相关
    private TeacherInfo teacherInfo;//老师的个人信息
    private List<GroupInfo> groups;//群组信息
    private List<UserInfo> friends;//好友信息
    private List<ClassInfo> classes ;//班级信息

    public XmppServerInfo getXmppServerInfo() {
        return xmppServerInfo;
    }

    public TeacherInfo getTeacherInfo() {
        return teacherInfo;
    }

    public List<GroupInfo> getGroups() {
        return groups;
    }

    public List<UserInfo> getFriends() {
        return friends;
    }

    public List<ClassInfo> getClasses() {
        return classes;
    }
}
