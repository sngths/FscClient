package com.tianxing.presenter.main;

import com.tianxing.model.App;
import com.tianxing.deprecated.data.ContactsPool;
import com.tianxing.ui.fragment.main.ContactsView;

/**
 * Created by tianxing on 16/7/21.
 */
public class ContactsViewPresenter implements ContactsPresenter {

    private ContactsView view;
    private ContactsPool contactsPool;


    public ContactsViewPresenter(ContactsView view){
        this.view = view;
        contactsPool = App.getInstance().getContactsPool();


    }


    /**
     * 取子项组数
     * */
    private int getItemGroupCount(){
        return contactsPool.getClassCount() + 2;
    }


    /**
     * 取得每组子项数目
     * */
    private int getItemCount(int groupPosition){
        if (groupPosition == 0){
            return contactsPool.getGroupCount();
        }else if (groupPosition == 1){
            return contactsPool.getFriendCount();
        }else {
            return contactsPool.getStudentCount(groupPosition -2);
        }
    }

    /**
     * 取得列表项数 包括标题项
     */
    @Override
    public int getItemCount() {
        int groupItemCount = contactsPool.getGroupCount() + 1;
        int friendItemCount = contactsPool.getFriendCount() + 1;
        int studendItemCount = 0;
        for (int i = 0; i < contactsPool.getClassCount(); i++) {
            studendItemCount += contactsPool.getStudentCount(i);
            studendItemCount += 1;
        }
        return groupItemCount + friendItemCount + studendItemCount;
    }

    /**
     * 取得父项位置
     *
     * @param position
     */
    @Override
    public int getParentPosition(int position) {
        int parentPosition = -1;
        //计算出组和子项的位置
        int itemCount = position + 1;
        for (int i = 0; i <getItemGroupCount() ; i++) {
            if (itemCount - getItemCount(i) - 1 <= 0){
                parentPosition = i;
                break;
            }else {
                itemCount -= getItemCount(i) + 1;
            }
        }
        return parentPosition;
    }

    /**
     * 取得子项位置
     *
     * @param position
     */
    @Override
    public int getChildPosition(int position) {
        int childPosition = -1;
        //计算出组和子项的位置
        int itemCount = position + 1;
        for (int i = 0; i <getItemGroupCount() ; i++) {
            if (itemCount - getItemCount(i) - 1 <= 0){
                if (itemCount - getItemCount(i) -1 == - getItemCount(i)){
                    childPosition = -1;//表示为标题
                }else {
                    childPosition = itemCount - 2;
                }
                break;
            }else {
                itemCount -= getItemCount(i) + 1;
            }
        }
        return childPosition;
    }

    /**
     * 取得对应位置的视图类型
     *
     * @param position
     * @return 返回VIEW_TYPE_Header = 0 或 VIEW_TYPE_ITEM = 1
     */
    @Override
    public int getItemType(int position) {

        int itemCount = position + 1;
        for (int i = 0; i < getItemGroupCount(); i++) {
            if (itemCount - getItemCount(i) - 1 <= 0){
                if (itemCount - getItemCount(i) - 1 == - getItemCount(i)){
                    return VIEW_TYPE_HEADER;
                }else {
                    return VIEW_TYPE_ITEM;
                }
            }else {
                itemCount -= getItemCount(i) + 1;
            }
        }
        return -1;
    }

    /**
     * 取得对应位置的视图标题
     *
     * @param position
     */
    @Override
    public String getItemTitle(int position) {
        //取得标题
        return contactsPool.getTitle(getParentPosition(position), getChildPosition(position));
    }

    /**
     * 取得群组名称
     *
     * @param position
     */
    @Override
    public String getRoomName(int position) {
        return contactsPool.getGroupInfo(position).getRoomID();
    }

    /**
     * 取得会话用户名称
     *
     * @param parentPosition
     * @param childPosition
     */
    @Override
    public String getUsername(int parentPosition, int childPosition) {
        String userName;
        if (parentPosition == 1){
            userName = contactsPool.getFriendInfo(childPosition).getUserName();
        }else {
            userName = contactsPool.getStudentInfo(parentPosition - 2, childPosition).getUserName();
        }
        return userName;
    }
}
