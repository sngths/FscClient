package com.tianxing.ui.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tianxing.entity.assignment.AssignmentDownload;
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
public class AssignmentDetailListAdapter implements PinnedSectionListView.PinnedSectionListAdapter {


    private static final int VIEW_TYPE_REPLY_TITLE = 0;
    private static final int VIEW_TYPE_ASSIGNMENT= 1;
    private static final int VIEW_TYPE_REPLY = 2;


    private LayoutInflater inflater;
    private AssignmentDetailPresenter presenter;
    public AssignmentDetailListAdapter(Context context, AssignmentDetailPresenter presenter){
        inflater = LayoutInflater.from(context);
        this.presenter = presenter;
    }


    /**
     * This method shall return 'true' if views of given type has to be pinned.
     *
     * @param viewType
     */
    @Override
    public boolean isItemViewTypePinned(int viewType) {
        return viewType == VIEW_TYPE_REPLY_TITLE;
    }

    /**
     * Indicates whether all the items in this adapter are enabled. If the
     * value returned by this method changes over time, there is no guarantee
     * it will take effect.  If true, it means all items are selectable and
     * clickable (there is no separator.)
     *
     * @return True if all items are enabled, false otherwise.
     * @see #isEnabled(int)
     */
    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    /**
     * Returns true if the item at the specified position is not a separator.
     * (A separator is a non-selectable, non-clickable item).
     * <p/>
     * The result is unspecified if position is invalid. An {@link ArrayIndexOutOfBoundsException}
     * should be thrown in that case for fast failure.
     *
     * @param position Index of the item
     * @return True if the item is not a separator
     * @see #areAllItemsEnabled()
     */
    @Override
    public boolean isEnabled(int position) {
        return position == 1;
    }

    /**
     * Register an observer that is called when changes happen to the data used by this adapter.
     *
     * @param observer the object that gets notified when the data set changes.
     */
    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    /**
     * Unregister an observer that has previously been registered with this
     * adapter via {@link #registerDataSetObserver}.
     *
     * @param observer the object to unregister.
     */
    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return presenter.getClassInfo().getStudentCount() + 2;
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return null;
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Indicates whether the item ids are stable across changes to the
     * underlying data.
     *
     * @return True if the same id always refers to the same object.
     */
    @Override
    public boolean hasStableIds() {
        return false;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            if (position == 0){
                convertView = inflater.inflate(R.layout.item_assignment, parent, false);
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

    /**
     * Get the type of View that will be created by {@link #getView} for the specified item.
     *
     * @param position The position of the item within the adapter's data set whose view type we
     *                 want.
     * @return An integer representing the type of View. Two views should share the same type if one
     * can be converted to the other in {@link #getView}. Note: Integers must be in the
     * range 0 to {@link #getViewTypeCount} - 1. {@link #IGNORE_ITEM_VIEW_TYPE} can
     * also be returned.
     * @see #IGNORE_ITEM_VIEW_TYPE
     */
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

    /**
     * <p>
     * Returns the number of types of Views that will be created by
     * {@link #getView}. Each type represents a set of views that can be
     * converted in {@link #getView}. If the adapter always returns the same
     * type of View for all items, this method should return 1.
     * </p>
     * <p>
     * This method will only be called when when the adapter is set on the
     * the {@link AdapterView}.
     * </p>
     *
     * @return The number of types of Views that will be created by this adapter
     */
    @Override
    public int getViewTypeCount() {
        return 3;
    }

    /**
     * @return true if this adapter doesn't contain any data.  This is used to determine
     * whether the empty view should be displayed.  A typical implementation will return
     * getCount() == 0 but since getCount() includes the headers and footers, specialized
     * adapters might want a different behavior.
     */
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
