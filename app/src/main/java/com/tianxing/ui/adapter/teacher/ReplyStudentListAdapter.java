package com.tianxing.ui.adapter.teacher;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tianxing.deprecated.entity.info.StudentInfo;
import com.tianxing.fscteachersedition.R;
import com.tianxing.ui.listener.ReplyListItemOnClickListener;

import java.util.List;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

/**
 * Created by tianxing on 16/11/3.
 */

public class ReplyStudentListAdapter extends RecyclerView.Adapter<ReplyStudentListAdapter.mViewHolder> {



    private List<StudentInfo> studentInfos;
    private LayoutInflater inflater;

    private ReplyListItemOnClickListener listener;

    public ReplyStudentListAdapter(LayoutInflater inflater, List<StudentInfo> studentInfos){
        this.studentInfos = studentInfos;
        this.inflater = inflater;
    }



    @Override
    public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = inflater.inflate(R.layout.item_reply_user_teacher, parent, false);
        final mViewHolder hold = new mViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击位置
                if (listener != null){
                    listener.OnClick(hold.getAdapterPosition());
                }
            }
        });
        return hold;
    }


    @Override
    public void onBindViewHolder(mViewHolder holder, int position) {
        //修改视图
        holder.setData(studentInfos.get(position));
    }




    @Override
    public int getItemCount() {
        return studentInfos.size();
    }



    /**
     * 子项点击监听
     * */
    public void setItemOnClickListener(ReplyListItemOnClickListener listener){
        this.listener = listener;
    }


    class mViewHolder extends RecyclerView.ViewHolder{

        private ImageView icon;
        private TextView username;
        private TextView date;
        private TextView time;
        private TextView contect;

        mViewHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.imageView_icon);
            username = (TextView) itemView.findViewById(R.id.textView_username);
            date = (TextView) itemView.findViewById(R.id.textView_date);
            time = (TextView) itemView.findViewById(R.id.textView_time);
            contect = (TextView) itemView.findViewById(R.id.textView_content);
        }


        /**
         * 设置数据
         * */
        void setData(StudentInfo studentInfo){
            Picasso.with(inflater.getContext()).load(R.mipmap.user_icon_40dp).transform(new CropCircleTransformation()).into(icon);
            username.setText(studentInfo.getNickName());
        }
    }
}
