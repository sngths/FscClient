package com.tianxing.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tianxing.fscteachersedition.R;

/**
 * Created by tianxing on 16/7/13.
 */
public class AssignmentListAdapter extends Adapter<AssignmentListAdapter.mViewHold> {

    private LayoutInflater inflater;



    public AssignmentListAdapter(Context context){
        inflater = LayoutInflater.from(context);
    }

    @Override
    public mViewHold onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = inflater.inflate(R.layout.cardview_assignment, parent, false);
        mViewHold hold = new mViewHold(view);
        return hold;
    }


    @Override
    public void onBindViewHolder(mViewHold holder, int position) {
        //修改View视图
    }



    @Override
    public int getItemCount() {
        return 30;
    }



    class mViewHold extends ViewHolder{
        public mViewHold(View itemView) {
            super(itemView);
        }
    }
}
