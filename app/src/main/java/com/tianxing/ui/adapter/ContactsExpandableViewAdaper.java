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
import com.tianxing.ui.listener.ExpandableViewOnClickListener;

import java.util.List;

/**
 * Created by tianxing on 16/7/21.
 */
public class ContactsExpandableViewAdaper extends ExpandableRecyclerAdapter<ContactsExpandableViewAdaper.mParentViewHold, ContactsExpandableViewAdaper.mChildViewHold>{



    private ExpandableViewOnClickListener listener;



    private LayoutInflater inflater;
    public ContactsExpandableViewAdaper(Context context, @NonNull List<? extends ParentListItem> parentItemList) {
        super(parentItemList);
        this.inflater = LayoutInflater.from(context);
    }


    public void setOnClickListener(ExpandableViewOnClickListener listener){
        this.listener = listener;
    }



    @Override
    public mParentViewHold onCreateParentViewHolder(ViewGroup parentViewGroup) {
        View view = inflater.inflate(R.layout.expandableview_item_parent, parentViewGroup, false);
        final mParentViewHold viewHold = new mParentViewHold(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return viewHold;
    }


    @Override
    public mChildViewHold onCreateChildViewHolder(ViewGroup childViewGroup) {
        View view = inflater.inflate(R.layout.expandableview_item_child, childViewGroup, false);
        final mChildViewHold childViewHold = new mChildViewHold(view);
        return childViewHold;
    }


    @Override
    public void onBindParentViewHolder(mParentViewHold parentViewHolder, int position, ParentListItem parentListItem) {

    }


    @Override
    public void onBindChildViewHolder(mChildViewHold childViewHolder, final int position, Object childListItem) {
        childViewHolder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnClick(position);
            }
        });
    }




    class mChildViewHold extends ChildViewHolder{

        private View view;

        public mChildViewHold(View itemView) {
            super(itemView);
            this.view = itemView;
        }

        public View getView() {
            return view;
        }
    }


    class mParentViewHold extends ParentViewHolder{
        private View view;

        public mParentViewHold(View itemView) {
            super(itemView);
            this.view = itemView;
        }

        public View getView() {
            return view;
        }
    }
}
