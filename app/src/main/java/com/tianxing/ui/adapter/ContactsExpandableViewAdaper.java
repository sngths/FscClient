package com.tianxing.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;

import java.util.List;

/**
 * Created by tianxing on 16/7/21.
 */
public class ContactsExpandableViewAdaper extends ExpandableRecyclerAdapter<ContactsExpandableViewAdaper.mParentViewHold, ContactsExpandableViewAdaper.mChildViewHold>{


    private LayoutInflater inflater;
    public ContactsExpandableViewAdaper(Context context, @NonNull List<? extends ParentListItem> parentItemList) {
        super(parentItemList);
        this.inflater = LayoutInflater.from(context);
    }


    @Override
    public mParentViewHold onCreateParentViewHolder(ViewGroup parentViewGroup) {

        return null;
    }


    @Override
    public mChildViewHold onCreateChildViewHolder(ViewGroup childViewGroup) {
        return null;
    }


    @Override
    public void onBindParentViewHolder(mParentViewHold parentViewHolder, int position, ParentListItem parentListItem) {

    }


    @Override
    public void onBindChildViewHolder(mChildViewHold childViewHolder, int position, Object childListItem) {

    }




    class mChildViewHold extends ChildViewHolder{


        public mChildViewHold(View itemView) {
            super(itemView);
        }
    }


    class mParentViewHold extends ParentViewHolder{


        public mParentViewHold(View itemView) {
            super(itemView);
        }
    }
}
