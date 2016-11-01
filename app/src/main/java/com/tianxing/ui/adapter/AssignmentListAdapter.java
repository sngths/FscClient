package com.tianxing.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tianxing.entity.transfer.receive.AssignmentDownload;
import com.tianxing.entity.http.json.ImageFile;
import com.tianxing.fscteachersedition.R;
import com.tianxing.presenter.main.AssignmentListPresenter;
import com.tianxing.ui.listener.AssignmentItemOnClickListerner;
import com.tianxing.util.ScreenSize;

import java.util.List;

/**
 * Created by tianxing on 16/7/13.
 * 作业列表适配器
 */
public class AssignmentListAdapter extends Adapter<AssignmentListAdapter.mViewHold> {
    private static final String TAG = "AssignmentListAdp";
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
        // 取得作业数据 修改View视图
        AssignmentDownload assignment = presenter.getAssignment(calssID, position);
        //打印作业信息
        holder.setTitle(assignment.getTitle());
        holder.setDate(assignment.getDate());
        holder.setContent(assignment.getContent());
        holder.setImage(assignment.getImages());
    }



    @Override
    public int getItemCount() {
        return presenter.getAssignemntCount(calssID);
    }





    /**
     * 子项点击监听
     * */
    public void setItemOnClickListener(AssignmentItemOnClickListerner listener){
        this.listerner = listener;
    }


    class mViewHold extends ViewHolder{
        private View view;
        private TextView title;
        private TextView date;
        private TextView time;
        private TextView content;
        private LinearLayout imageFrame;

        public mViewHold(View itemView) {
            super(itemView);
            view = itemView;
            title = (TextView) itemView.findViewById(R.id.textView_title);
            date = (TextView) itemView.findViewById(R.id.textView_date);
            time = (TextView) itemView.findViewById(R.id.textView_time);
            content = (TextView) itemView.findViewById(R.id.textView_content);
            imageFrame = (LinearLayout) itemView.findViewById(R.id.linearLayout_image_frame);
        }

        //View的修改函数
        /**
         * 设置标题
         * */
        public void setTitle(String title){
            if (title == null){
                this.title.setVisibility(View.INVISIBLE);
            }else {
                this.title.setText(title);
            }
        }
        /**
         * 设置内容
         * */
        public void setContent(String content){
            if (content == null){
                this.content.setVisibility(View.INVISIBLE);
            }else {
                this.content.setText(content);
            }
        }

        /**
         * 设置时间
         * @param date  格式为： 星期日 2016.10.30 10:13:49 上午 CST
         * */
        public void setDate(String date){
            Log.e(TAG, "日期：" + date);
            String[] dateArray = date.split(" ");
            this.date.setText(dateArray[1]);
            this.time.setText("");
        }

        /**
         * 设置图片 根据图片数目设置布局
         * */
        public void setImage(List<ImageFile> images){
            if (images == null){
                return;
            }
            imageFrame.removeAllViews();
            if (images.size() == 1){
                ImageButton imageButton = new ImageButton(view.getContext());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ScreenSize.dp2px(240), ScreenSize.dp2px(180));
                params.setMargins(8, 0, 8, 8);
                imageButton.setLayoutParams(params);
                Picasso.with(view.getContext())
                        .load(images.get(0).getUrl())
                        .resize(ScreenSize.dp2px(240), ScreenSize.dp2px(180))
                        .centerCrop().into(imageButton);
                imageFrame.addView(imageButton);
            }else if (images.size()>1 && images.size()<=3){
                for (ImageFile imageFile:images) {
                    ImageButton imageButton = new ImageButton(view.getContext());
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ScreenSize.dp2px(96), ScreenSize.dp2px(96));
                    params.setMargins(8, 0, 8, 8);
                    imageButton.setLayoutParams(params);
                    Picasso.with(view.getContext())
                            .load(imageFile.getUrl())
                            .resize(ScreenSize.dp2px(96), ScreenSize.dp2px(96))
                            .centerCrop().into(imageButton);
                    imageFrame.addView(imageButton);
                }
            }else if (images.size()>3){
                for (int i = 0; i < 3; i++) {
                    ImageButton imageButton = new ImageButton(view.getContext());
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ScreenSize.dp2px(96), ScreenSize.dp2px(96));
                    params.setMargins(8, 0, 8, 8);
                    imageButton.setLayoutParams(params);
                    Picasso.with(view.getContext())
                            .load(images.get(i).getUrl())
                            .resize(ScreenSize.dp2px(96), ScreenSize.dp2px(96))
                            .centerCrop().into(imageButton);
                    imageFrame.addView(imageButton);
                }
            }
        }

    }

}
