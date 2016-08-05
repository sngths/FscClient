package com.tianxing.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import com.tianxing.fscteachersedition.R;
import com.tianxing.presenter.child.ChatGroupPresenter;

import java.util.List;

/**
 * Created by tianxing on 16/8/3.
 * 多人会话列表适配器
 */
public class ChatGroupListAdapter extends RecyclerView.Adapter<ChatGroupListAdapter.mViewHold>{

    private LayoutInflater inflater;
    private ChatGroupPresenter presenter;

    public ChatGroupListAdapter(Context context, ChatGroupPresenter presenter){
        inflater = LayoutInflater.from(context);
        this.presenter = presenter;
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    /**
     * Called when RecyclerView needs a new {@link RecyclerView.ViewHolder} of the given type to represent
     * an item.
     * <p/>
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     * <p/>
     * The new ViewHolder will be used to display items of the adapter using
     * {@link #onBindViewHolder(RecyclerView.ViewHolder, int, List)}. Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary {@link View#findViewById(int)} calls.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see #getItemViewType(int)
     * @see #onBindViewHolder(RecyclerView.ViewHolder, int)
     */
    @Override
    public mViewHold onCreateViewHolder(ViewGroup parent, int viewType) {
        mViewHold viewHold;
        if (viewType == 0){
            viewHold = new mViewHold(inflater.inflate(R.layout.chat_group_message_left_audio, parent, false));
        }else if (viewType == 1){
            viewHold = new mViewHold(inflater.inflate(R.layout.chat_group_message_right_audio, parent, false));
        }else if (viewType == 2){
            viewHold = new mViewHold(inflater.inflate(R.layout.chat_group_message_left_picture, parent, false));
        }else if (viewType == 3){
            viewHold = new mViewHold(inflater.inflate(R.layout.chat_group_message_right_picture, parent, false));
        }else if (viewType == 4){
            viewHold = new mViewHold(inflater.inflate(R.layout.chat_group_message_left_text, parent, false));
        }else {
            viewHold = new mViewHold(inflater.inflate(R.layout.chat_group_message_right_text, parent, false));
        }
        return viewHold;
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link RecyclerView.ViewHolder#itemView} to reflect the item at the given
     * position.
     * <p/>
     * Note that unlike {@link ListView}, RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the <code>position</code> parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use {@link RecyclerView.ViewHolder#getAdapterPosition()} which will
     * have the updated adapter position.
     * <p/>
     * Override {@link #onBindViewHolder(RecyclerView.ViewHolder, int, List)} instead if Adapter can
     * handle effcient partial bind.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(mViewHold holder, int position) {
        //修改视图
        holder.setUserIcon();
    }

    /**
     * Returns the total number of items in the data set hold by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return 6;
    }

    class mViewHold extends RecyclerView.ViewHolder{

        private View view;
        private ImageButton icon;
        public mViewHold(View itemView) {
            super(itemView);
            this.view = itemView;
            icon = (ImageButton) view.findViewById(R.id.imageButton_icon);
        }

        /**
         * 设置头像
         * */
        void setUserIcon(){
            //Picasso.with(inflater.getContext()).load(R.mipmap.user_icon).transform(new CropCircleTransformation()).into(icon);
        }
    }
}
