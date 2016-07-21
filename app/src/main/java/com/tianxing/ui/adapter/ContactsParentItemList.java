package com.tianxing.ui.adapter;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;

import java.util.List;

/**
 * Created by tianxing on 16/7/21.
 */
public class ContactsParentItemList implements ParentListItem {


    public ContactsParentItemList(){

    }

    @Override
    public List<?> getChildItemList() {
        return null;
    }



    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
