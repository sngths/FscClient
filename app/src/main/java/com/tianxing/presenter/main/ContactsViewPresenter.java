package com.tianxing.presenter.main;

import com.tianxing.entity.info.ClassInfo;
import com.tianxing.entity.info.GroupInfo;
import com.tianxing.entity.info.UserInfo;
import com.tianxing.model.App;
import com.tianxing.model.ContactsPool;
import com.tianxing.ui.fragment.main.ContactsView;

import java.util.List;

/**
 * Created by tianxing on 16/7/21.
 */
public class ContactsViewPresenter implements ContactsPresenter {

    private ContactsView view;
    private ContactsPool contactsPool;


    public ContactsViewPresenter(ContactsView view){
        this.view = view;
        contactsPool = App.getInstance().getContactsPool();
        //存入一些预设数据
        contactsPool.putGroupInfo(new GroupInfo());
        contactsPool.putGroupInfo(new GroupInfo());
        contactsPool.putGroupInfo(new GroupInfo());
        contactsPool.putGroupInfo(new GroupInfo());
        contactsPool.putFriendInfo(new UserInfo());
        contactsPool.putFriendInfo(new UserInfo());
        contactsPool.putFriendInfo(new UserInfo());
        contactsPool.putFriendInfo(new UserInfo());
        contactsPool.putClassInfo(new ClassInfo());
        contactsPool.putClassInfo(new ClassInfo());

    }


    /**
     * 取得群组列表
     */
    @Override
    public List<GroupInfo> getGroupInfos() {
        return contactsPool.getGroupInfoList();
    }

    /**
     * 取得好友列表
     */
    @Override
    public List<UserInfo> getFriendsInfo() {

        return contactsPool.getFriendList();
    }

    /**
     * 取得班级数目
     */
    @Override
    public Integer getClassCount() {
        return contactsPool.getClassCount();
    }

    /**
     * 取得对应位置的班级学生列表
     *
     * @param position
     */
    @Override
    public List<UserInfo> getStudentList(Integer position) {
        return contactsPool.getStudentList(position);
    }
}
