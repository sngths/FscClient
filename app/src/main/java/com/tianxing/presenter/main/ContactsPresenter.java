package com.tianxing.presenter.main;

import com.tianxing.entity.info.GroupInfo;
import com.tianxing.entity.info.UserInfo;

import java.util.List;

/**
 * Created by tianxing on 16/7/18.
 */
public interface ContactsPresenter {




    /**
     * 取得群组列表
     * */
    List<GroupInfo> getGroupInfos();

    /**
     * 取得好友列表
     * */
    List<UserInfo> getFriendsInfo();


    /**
     * 取得班级数目
     * */
    Integer getClassCount();


    /**
     * 取得对应位置的班级信息
     * */


    /**
     * 取得对应位置的班级学生列表
     * */

    List<UserInfo> getStudentList(Integer position);

}
