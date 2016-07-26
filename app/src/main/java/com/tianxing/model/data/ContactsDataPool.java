package com.tianxing.model.data;

import com.tianxing.entity.info.ClassInfo;
import com.tianxing.entity.info.GroupInfo;
import com.tianxing.entity.info.UserInfo;
import com.tianxing.model.ContactsPool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianxing on 16/7/22.
 */
public class ContactsDataPool implements ContactsPool {

    private List<GroupInfo> Groups;//群会话列表
    private List<UserInfo> Friends;//好友列表
    private List<ClassInfo> Classes;//班级列表








    public ContactsDataPool(){
        Groups = new ArrayList<>();
        Friends = new ArrayList<>();
        Classes = new ArrayList<>();

    }


    /**
     * 取得群组数目
     */
    @Override
    public Integer getGroupCount() {
        return Groups.size();
    }

    /**
     * 取得对应位置的一个群主信息
     *
     * @param position
     */
    @Override
    public GroupInfo getGroupInfo(Integer position) {
        return Groups.get(position);
    }

    /**
     * 取得群组列表
     */
    @Override
    public List<GroupInfo> getGroupInfoList() {
        return Groups;
    }

    /**
     * 向群组列表中添加一个群主信息 添加到列队底部
     *
     * @param groupInfo
     */
    @Override
    public void putGroupInfo(GroupInfo groupInfo) {
        Groups.add(groupInfo);
    }

    /**
     * 添加一个好友信息
     *
     * @param userInfo
     */
    @Override
    public void putFriendInfo(UserInfo userInfo) {
        Friends.add(userInfo);
    }

    /**
     * 刷新好友列表
     *
     * @param friengList
     */
    @Override
    public void setFriengList(List<UserInfo> friengList) {
        this.Friends = friengList;
    }

    /**
     * 添加一个班级信息
     *
     * @param classInfo
     */
    @Override
    public void putClassInfo(ClassInfo classInfo) {
        Classes.add(classInfo);
    }



    /**
     * 取得好友数目
     */
    @Override
    public Integer getFriendCount() {
        return Friends.size();
    }

    /**
     * 取得好友列表
     */
    @Override
    public List<UserInfo> getFriendList() {
        return Friends;
    }

    /**
     * 取得对应位置的一个好友信息
     *
     * @param position
     */
    @Override
    public UserInfo getFriendInfo(Integer position) {
        return Friends.get(position);
    }

    /**
     * 取得班级数目
     */
    @Override
    public Integer getClassCount() {
        return Classes.size();
    }

    /**
     * 取得对应位置的班级信息
     *
     * @param position
     */
    @Override
    public ClassInfo getClassInfo(Integer position) {
        return Classes.get(position);
    }

    /**
     * 取得对应位置班级的学生数目
     *
     * @param classPosition
     */
    @Override
    public Integer getStudentCount(Integer classPosition) {
        return Classes.get(classPosition).getStudentCount();
    }

    /**
     * 取得对应位置的班级中的学生信息
     *
     * @param classPosition
     * @param studentPosition
     */
    @Override
    public UserInfo getStudentInfo(Integer classPosition, Integer studentPosition) {
        return Classes.get(classPosition).getStudentInfo(studentPosition);
    }

    /**
     * 取得对应位置的班级的学生列表
     *
     * @param classPosition
     */
    @Override
    public List<UserInfo> getStudentList(Integer classPosition) {
        return Classes.get(classPosition).getStudentList();
    }
}
