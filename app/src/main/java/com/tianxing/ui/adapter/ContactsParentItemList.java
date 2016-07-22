package com.tianxing.ui.adapter;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.tianxing.entity.info.InfoEntity;

import java.util.List;

/**
 * Created by tianxing on 16/7/21.
 */
public class ContactsParentItemList<T extends InfoEntity> implements ParentListItem {


    private List<T> infoEntities;
    public ContactsParentItemList(List<T> infoEntities){
        this.infoEntities = infoEntities;
    }

    @Override
    public List<T> getChildItemList() {
        return infoEntities;
    }



    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
