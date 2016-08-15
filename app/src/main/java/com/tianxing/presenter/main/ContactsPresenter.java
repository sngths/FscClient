package com.tianxing.presenter.main;

/**
 * Created by tianxing on 16/7/18.
 */
public interface ContactsPresenter {

    int VIEW_TYPE_HEADER = 0;
    int VIEW_TYPE_ITEM = 1;


    /**
     * 取得群组数目
     * */
    //Integer getGroupCount();


    /**
     * 取得群组列表
     * */
    //List<GroupInfo> getGroupInfos();


    /**
     * 取得好友数目
     * */
    //Integer getFriendsCount();


    /**
     * 取得好友列表
     * */
    //List<UserInfo> getFriendsInfo();


    /**
     * 取得班级数目
     * */
    //Integer getClassCount();


    /**
     * 取得对应位置的班级信息
     * */


    /**
     * 取得对应位置的班级学生列表
     * */

    //List<UserInfo> getStudentList(Integer position);


    /**
     * 取得对应位置的子项数目
     * */
    //Integer getChildItemCount(Integer parentPosition);




    /**
     * 取得列表项数 包括标题项
     * */
    int getItemCount();


    /**
     * 取得父项位置
     * */
    int getParentPosition(int position);

    /**
     * 取得子项位置
     * */
    int getChildPosition(int position);

    /**
     * 取得对应位置的视图类型
     * */
    int getItemType(int position);


    /**
     * 取得对应位置的视图标题
     * */
    String getItemTitle(int position);

}
