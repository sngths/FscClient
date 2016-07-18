package com.tianxing.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tianxing.fscteachersedition.R;
import com.tianxing.presenter.main.AssignmentListPresenter;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by tianxing on 16/7/13.
 */
public class AssignmentListAdapter extends Adapter<AssignmentListAdapter.mViewHold> {

    private LayoutInflater inflater;
    private AssignmentListPresenter listPresenter;

    private static AtomicInteger a = new AtomicInteger(1);

    private String calssID;

    public AssignmentListAdapter(Context context, AssignmentListPresenter listPresenter, String classID){
        a.incrementAndGet();
        this.calssID = classID;
        inflater = LayoutInflater.from(context);
        this.listPresenter = listPresenter;
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
        return listPresenter.getAssignemntCount(calssID);
    }



    class mViewHold extends ViewHolder{
        public mViewHold(View itemView) {
            super(itemView);
        }
    }
}
