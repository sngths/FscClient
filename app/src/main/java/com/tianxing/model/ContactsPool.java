package com.tianxing.model;

import com.tianxing.entity.info.ClassInfo;
import com.tianxing.entity.info.GroupInfo;
import com.tianxing.entity.info.StudentInfo;
import com.tianxing.entity.info.UserInfo;

import java.util.List;

/**
 * Created by tianxing on 16/7/22.
 * 联系人操作接口
 */
public interface ContactsPool {




    /**
     * 取得对应位置标题
     * */
    String getTitle(int parentPosition, int childPosition);



    /**
     * 取得群组数目
     * */
    Integer getGroupCount();

    /**
     * 取得对应位置的一个群组信息
     * */
    GroupInfo getGroupInfo(Integer position);

    /**
     * 取得群组列表
     * */
    List<GroupInfo> getGroupInfoList();






    /**
     * 取得好友数目
     * */
    Integer getFriendCount();

    /**
     * 取得好友列表
     * */
    List<UserInfo> getFriendList();


    /**
     * 取得对应位置的一个好友信息
     * */
    UserInfo getFriendInfo(Integer position);




    /**
     * 取得班级数目
     * */
    Integer getClassCount();


    /**
     * 取得对应位置的班级信息
     * */
    ClassInfo getClassInfo(Integer position);

    /**
     * 取得对应位置班级的学生数目
     * */
    Integer getStudentCount(Integer classPosition);

    /**
     * 取得对应位置的班级中的学生信息
     * */
    UserInfo getStudentInfo(Integer classPosition, Integer studentPosition);

    /**
     * 取得对应位置的班级的学生列表
     * */
    List<StudentInfo> getStudentList(Integer classPosition);





    /**
     * 向群组列表中添加一个群组信息 添加到列队底部
     * */
    void putGroupInfo(GroupInfo groupInfo);


    /**
     * 添加一个好友信息
     * */
    void putFriendInfo(UserInfo userInfo);

    /**
     * 刷新好友列表
     * */
    void setFriengList(List<UserInfo> friengList);

    /**
     * 添加一个班级信息
     * */
    void putClassInfo(ClassInfo classInfo);

    List<GroupInfo> getGroups();

    List<UserInfo> getFriends();

    List<ClassInfo> getClasses();



    /**
     * 根据ID 取得相应数据
     * */
    ClassInfo getClassInfo(String id);


    /**
     * 根据学生ID取出一个学生信息
     * */
    StudentInfo getStudentInfo(String studentID);


    /**
     * 存入一个学生ID-info 键值对
     * */
    void putStudentInfo(String studentID, StudentInfo studentInfo);





    /**
     * 取得消息条数
     * */



    /**
     * 取得对应位置的一条消息
     * */


    /**
     * 存入一条消息
     * */



}
