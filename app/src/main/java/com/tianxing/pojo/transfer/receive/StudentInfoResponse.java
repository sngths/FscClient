package com.tianxing.pojo.transfer.receive;

import com.tianxing.deprecated.entity.info.ClassInfo;
import com.tianxing.deprecated.entity.info.GroupInfo;
import com.tianxing.deprecated.entity.info.StudentInfo;
import com.tianxing.deprecated.entity.info.UserInfo;
import com.tianxing.model.communication.xmpp.XmppServerInfo;

import java.util.List;

/**
 * Created by tianxing on 16/10/29.
 * 学生用户登录成功后从服务器获取到的信息
 */

public class StudentInfoResponse {
    private XmppServerInfo xmppServerInfo;//xmpp通信相关
    private StudentInfo studentInfo;//老师的个人信息
    private List<GroupInfo> groups;//群组信息
    private List<UserInfo> friends;//好友信息
    private ClassInfo classInfo;//班级信息

    public XmppServerInfo getXmppServerInfo() {
        return xmppServerInfo;
    }

    public StudentInfo getStudentInfo() {
        return studentInfo;
    }

    public List<GroupInfo> getGroups() {
        return groups;
    }

    public List<UserInfo> getFriends() {
        return friends;
    }

    public ClassInfo getClassInfo() {
        return classInfo;
    }
}
