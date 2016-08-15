package com.tianxing.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tianxing.fscteachersedition.R;
import com.tianxing.presenter.main.AssignmentListPresenter;
import com.tianxing.ui.listener.AssignmentItemOnClickListerner;

/**
 * Created by tianxing on 16/7/13.
 * 作业列表适配器
 */
public class AssignmentListAdapter extends Adapter<AssignmentListAdapter.mViewHold> {

    private LayoutInflater inflater;
    private AssignmentListPresenter presenter;

    private AssignmentItemOnClickListerner listerner;


    private Integer calssID;//班级ID 即班级信息在列表中保存的位置

    public AssignmentListAdapter(Context context, AssignmentListPresenter presenter, int classID){
        this.calssID = classID;
        inflater = LayoutInflater.from(context);
        this.presenter = presenter;
    }

    @Override
    public mViewHold onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = inflater.inflate(R.layout.item_assignment, parent, false);
        final mViewHold hold = new mViewHold(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listerner != null){
                    listerner.OnClick(calssID, hold.getAdapterPosition());
                }
            }
        });
        return hold;
    }


    @Override
    public void onBindViewHolder(mViewHold holder, int position) {
        //修改View视图 取得作业数据
        presenter.getAssignment(calssID, position);
    }



    @Override
    public int getItemCount() {
        return presenter.getAssignemntCount(calssID);
    }



    class mViewHold extends ViewHolder{
        public mViewHold(View itemView) {
            super(itemView);
        }

        //View的修改函数

    }


    /**
     * 子项点击监听
     * */
    public void setItemOnClickListener(AssignmentItemOnClickListerner listener){
        this.listerner = listener;
    }
}
