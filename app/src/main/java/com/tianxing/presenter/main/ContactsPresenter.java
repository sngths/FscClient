package com.tianxing.presenter.main;

/**
 * Created by tianxing on 16/7/18.
 */
public interface ContactsPresenter {

    int VIEW_TYPE_HEADER = 0;
    int VIEW_TYPE_ITEM = 1;





    /**
     * 取得列表项数 包括标题项
     * */
    int getItemCount();


    /**
     * 取得父项位置 标题栏位置
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


    /**
     * 取得群组名称
     * */
    String getRoomName(int position);


    /**
     * 取得会话用户名称
     * */
    String getUsername(int parentPosition, int childPosition);


    /**
     * 取得用户名称
     * */



    /**
     * 取得当前连接状态
     * */


    /**
     * 添加连接状态监听
     * */

}
