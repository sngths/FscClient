package com.tianxing.model.data;

import com.tianxing.entity.info.ClassInfo;
import com.tianxing.entity.info.GroupInfo;
import com.tianxing.entity.info.UserInfo;
import com.tianxing.model.ContactsPool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianxing on 16/7/22.
 *
 */
public class ContactsDataPool implements ContactsPool {

    private List<GroupInfo> Groups;//群会话列表
    private List<UserInfo> Friends;//好友列表
    private List<ClassInfo> Classes;//班级列表



    public ContactsDataPool(){
        Groups = new ArrayList<>();
        Friends = new ArrayList<>();
        Classes = new ArrayList<>();

        //存入一些联系人数据  这些数据在登录完成后 从服务器获取
        //群
        GroupInfo groupInfo = new GroupInfo();
        groupInfo.setRoomName("c1d1");
        putGroupInfo(groupInfo);
        //好友
        putFriendInfo(new UserInfo("user1"));
        putFriendInfo(new UserInfo("user2"));
        putFriendInfo(new UserInfo("user3"));
        putFriendInfo(new UserInfo("user4"));
        putFriendInfo(new UserInfo("user5"));
        //学生
        ClassInfo classInfo = new ClassInfo();
        classInfo.putUserInfo(new UserInfo("user6"));
        classInfo.putUserInfo(new UserInfo("user7"));
        classInfo.putUserInfo(new UserInfo("user8"));
        classInfo.putUserInfo(new UserInfo("user9"));
        classInfo.putUserInfo(new UserInfo("user10"));
        putClassInfo(classInfo);

    }


    /**
     * 取得对应位置标题
     *
     * @param parentPosition
     * @param childPosition
     */
    @Override
    public String getTitle(int parentPosition, int childPosition) {
        if (childPosition == -1){
            if (parentPosition == 0){
                return "班级群";
            }else if (parentPosition == 1){
                return "好友";
            }else {
                return "班级" + String.valueOf(parentPosition - 1);
            }
        }else {
            if (parentPosition == 0){
                return "班级群" + String.valueOf(childPosition + 1);
            }else if (parentPosition == 1){
                return "好友" + String.valueOf(childPosition + 1);
            }else {
                return "学生" + String.valueOf(childPosition + 1);
            }
        }
    }

    /**
     * 取得群组数目
     */
    @Override
    public Integer getGroupCount() {
        return Groups.size();
    }

    /**
     * 取得对应位置的一个群组信息
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
