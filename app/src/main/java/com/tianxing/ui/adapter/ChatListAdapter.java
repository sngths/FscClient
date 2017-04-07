package com.tianxing.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tianxing.deprecated.entity.message.ChatMessage;
import com.tianxing.deprecated.entity.message.Message;
import com.tianxing.fscteachersedition.R;
import com.tianxing.presenter.child.ChatPresenter;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

/**
 * Created by tianxing on 16/8/3.
 * 单人会话列表适配器
 */
public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.mViewHold>{
    private static final String TAG = "ChatListAdapter";


    private LayoutInflater inflater;
    private ChatPresenter presenter;

    public ChatListAdapter(Context context, ChatPresenter presenter){
        inflater = LayoutInflater.from(context);
        this.presenter = presenter;
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }


    @Override
    public mViewHold onCreateViewHolder(ViewGroup parent, int viewType) {

        mViewHold viewHold;
        ChatMessage message = presenter.getChatMessage(viewType);
        //Log.e(TAG, "创建ViewHolder " + "position " + viewType + " locally " + message.isLocally() + " contentType " + message.getContentType());
        int contentType = message.getContentType();
        if (message.isLocally()){
            //右边视图
            if (contentType == Message.TYPE_CONTENT_TEXT){
                viewHold = new mViewHold(inflater.inflate(R.layout.chat_message_right_text, parent, false));
            }else if (contentType == Message.TYPE_CONTENT_IMAGE){
                viewHold = new mViewHold(inflater.inflate(R.layout.chat_message_right_picture, parent, false));
            }else {
                viewHold = new mViewHold(inflater.inflate(R.layout.chat_message_right_audio, parent, false));
            }
        }else {
            if (contentType == Message.TYPE_CONTENT_TEXT){
                viewHold = new mViewHold(inflater.inflate(R.layout.chat_message_left_text, parent, false));
            }else if (contentType == Message.TYPE_CONTENT_IMAGE){
                viewHold = new mViewHold(inflater.inflate(R.layout.chat_message_left_picture, parent, false));
            }else {
                viewHold = new mViewHold(inflater.inflate(R.layout.chat_message_left_audio, parent, false));
            }
        }
        return viewHold;
    }


    @Override
    public void onBindViewHolder(mViewHold holder, int position) {
        holder.setUserIcon();
        if (presenter.getChatMessage(position).getContentType() == Message.TYPE_CONTENT_TEXT){
            holder.setTextContent(presenter.getChatMessage(position).getContent());
        }
    }


    @Override
    public int getItemCount() {
        return presenter.getMessageCount();
    }

    class mViewHold extends RecyclerView.ViewHolder{

        private ImageButton icon;
        private View view;

        public mViewHold(View itemView) {
            super(itemView);
            this.view = itemView;
            icon = (ImageButton) itemView.findViewById(R.id.imageView_icon);
        }

        /**
         * 设置头像
         * */
        void setUserIcon(){
            Picasso.with(inflater.getContext()).load(R.mipmap.user_icon_40dp).transform(new CropCircleTransformation()).into(icon);
        }

        /**
         * 设置文本内容
         * */
        void setTextContent(String content){
            View textView = view.findViewById(R.id.textView_message);
            if (textView != null){
                ((TextView)textView).setText(content);
            }

        }
    }
}
