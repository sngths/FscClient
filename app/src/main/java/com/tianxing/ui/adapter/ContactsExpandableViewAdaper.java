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
import com.tianxing.fscteachersedition.R;

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
        View view = inflater.inflate(R.layout.expandableview_item_parent, parentViewGroup, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return new mParentViewHold(view);
    }


    @Override
    public mChildViewHold onCreateChildViewHolder(ViewGroup childViewGroup) {
        View view = inflater.inflate(R.layout.expandableview_item_child, childViewGroup, false);
        return new mChildViewHold(view);
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
