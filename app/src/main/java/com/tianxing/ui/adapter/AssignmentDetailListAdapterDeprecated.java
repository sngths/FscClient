package com.tianxing.ui.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tianxing.entity.transfer.receive.AssignmentDownload;
import com.tianxing.entity.http.json.ImageFile;
import com.tianxing.entity.info.StudentInfo;
import com.tianxing.fscteachersedition.R;
import com.tianxing.presenter.child.AssignmentDetailPresenter;
import com.tianxing.ui.view.PinnedSectionListView;
import com.tianxing.util.ScreenSize;

import java.util.List;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

/**
 * Created by tianxing on 16/8/11.
 */
public class AssignmentDetailListAdapterDeprecated implements PinnedSectionListView.PinnedSectionListAdapter {


    private static final int VIEW_TYPE_REPLY_TITLE = 0;
    private static final int VIEW_TYPE_ASSIGNMENT= 1;
    private static final int VIEW_TYPE_REPLY = 2;


    private LayoutInflater inflater;
    private AssignmentDetailPresenter presenter;
    public AssignmentDetailListAdapterDeprecated(Context context, AssignmentDetailPresenter presenter){
        inflater = LayoutInflater.from(context);
        this.presenter = presenter;
    }


    @Override
    public boolean isItemViewTypePinned(int viewType) {
        return viewType == VIEW_TYPE_REPLY_TITLE;
    }


    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return position == 1;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return presenter.getClassInfo().getStudentCount() + 2;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            if (position == 0){
                convertView = inflater.inflate(R.layout.item_assignment_teacher, parent, false);
            }else if (position == 1){
                convertView = inflater.inflate(R.layout.item_assignment_reply_title, parent, false);
            }else {
                convertView = inflater.inflate(R.layout.item_assignment_reply, parent, false);
            }
            viewHolder = new ViewHolder(convertView);
            viewHolder.setViewData(position);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        return viewHolder.getView();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return VIEW_TYPE_ASSIGNMENT;
        }else if (position == 1){
            return VIEW_TYPE_REPLY_TITLE;
        }else {
            return VIEW_TYPE_REPLY;
        }

    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }



    private class ViewHolder{
        private View view;

        /**
         * 构建时保存相应视图的实例
         * */
        public ViewHolder(View view){
            this.view = view;

        }

        public View getView() {
            return view;
        }

        /**
         * 修改View视图
         * */
        void setViewData(int position){
            //根据不同view类型填充数据
            AssignmentDownload assignment = presenter.getAssignment();
            if (getItemViewType(position) == VIEW_TYPE_ASSIGNMENT){
                TextView title = (TextView) view.findViewById(R.id.textView_title);
                TextView date = (TextView) view.findViewById(R.id.textView_date);
                TextView time = (TextView) view.findViewById(R.id.textView_time);
                TextView content = (TextView) view.findViewById(R.id.textView_content);
                LinearLayout frame = (LinearLayout) view.findViewById(R.id.linearLayout_image_frame);
                title.setText(assignment.getTitle());
                String[] dateArray = assignment.getDate().split(" ");
                date.setText(dateArray[1]);
                content.setText(assignment.getContent());
                //设置图片
                List<ImageFile> images = assignment.getImages();
                if (images != null){
                    frame.removeAllViews();
                    if (images.size() == 1){
                        ImageButton imageButton = new ImageButton(view.getContext());
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ScreenSize.dp2px(240), ScreenSize.dp2px(180));
                        params.setMargins(8, 0, 8, 8);
                        imageButton.setLayoutParams(params);
                        Picasso.with(view.getContext())
                                .load(images.get(0).getUrl())
                                .resize(ScreenSize.dp2px(240), ScreenSize.dp2px(180))
                                .centerCrop().into(imageButton);
                        frame.addView(imageButton);
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
                            frame.addView(imageButton);
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
                            frame.addView(imageButton);
                        }
                    }
                }


            }else if (getItemViewType(position) == VIEW_TYPE_REPLY_TITLE){
                TextView title = (TextView) view.findViewById(R.id.textView_class_title);
                title.setText(assignment.getClassName());
            }else if (getItemViewType(position) == VIEW_TYPE_REPLY){
                //设置学生列表
                List<StudentInfo> studentInfoList = presenter.getClassInfo().getStudents();

                ImageView icon = (ImageView) view.findViewById(R.id.imageView_icon);
                TextView username = (TextView) view.findViewById(R.id.textView_username);
                TextView data = (TextView) view.findViewById(R.id.textView_date);
                TextView time = (TextView) view.findViewById(R.id.textView_time);
                Picasso.with(inflater.getContext()).load(R.mipmap.user_icon_40dp).transform(new CropCircleTransformation()).into(icon);
                username.setText(studentInfoList.get(position - 2).getNickName());
            }
        }
    }
}
