package com.tianxing.ui.fragment.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;
import com.tianxing.fscteachersedition.R;
import com.tianxing.presenter.child.ChatPresenter;
import com.tianxing.presenter.child.ChatViewPresenter;
import com.tianxing.ui.adapter.ChatListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by tianxing on 16/7/26.
 * 单人会话窗口
 */
public class ChatFragment extends BaseBackFragment implements ChatView {
    private static final String TAG = "ChatFragment";
    private String userName; //联系人姓名
    private Unbinder unbinder;

    @BindView(R.id.toolbar_child_Fragment)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.imageButton_send)
    Button buttonSend;
    @BindView(R.id.imageButton_more)
    ImageButton buttonMore;
    @BindView(R.id.editText_input)
    EditText editTextInput;


    //输入选择框
    @BindView(R.id.linearLayout_item_select)
    LinearLayout linearLayoutItemSelect;
    @BindView(R.id.imageButton_picture)
    ImageButton buttonPicture;
    @BindView(R.id.imageButton_capture)
    ImageButton buttonCapture;
    @BindView(R.id.imageButton_record)
    ImageButton buttonRecord;

    private ChatPresenter presenter;
    private ChatListAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //当前用户联系人
        if (savedInstanceState != null) {
            userName = savedInstanceState.getString("userName");
        }
        userName = getArguments().getString("userName");
        presenter = new ChatViewPresenter(this, userName);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        unbinder = ButterKnife.bind(this, view);
        toolBarInit(toolbar);
        setToolBarTitle("联系人");
        linearLayoutItemSelect.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ChatListAdapter(getContext(), presenter);
        recyclerView.setAdapter(adapter);

        //设置图标
        Picasso.with(getContext()).load(R.mipmap.chatview_button_picture).into(buttonPicture);
        Picasso.with(getContext()).load(R.mipmap.chatview_button_capture).into(buttonCapture);
        Picasso.with(getContext()).load(R.mipmap.chatview_button_recording).into(buttonRecord);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("userName", userName);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    /**
     * 各个按钮添加点击监听
     */
    @OnClick(R.id.imageButton_send)
    public void send() {
        if (!editTextInput.getText().toString().equals("")) {
            //发送消息
            presenter.sendTextMessage(editTextInput.getText().toString());
            editTextInput.setText("");
        }
    }

    @OnClick(R.id.imageButton_more)
    public void more() {
        if (linearLayoutItemSelect.getVisibility() == View.VISIBLE) {
            linearLayoutItemSelect.setVisibility(View.GONE);
        } else {
            linearLayoutItemSelect.setVisibility(View.VISIBLE);
        }
        hideKeyboard();
    }

    @OnClick(R.id.imageButton_picture)
    public void picture() {
        //选择图片

    }

    @OnClick(R.id.imageButton_capture)
    public void capture() {
        //启动拍照

    }

    @OnClick(R.id.imageButton_record)
    public void record() {
        //开始录音

    }

    /**
     * 消息列表数据更新
     */
    @Override
    public void onMessageListUpdate() {
        adapter.notifyDataSetChanged();
        //滚动到底部
        recyclerView.scrollToPosition(presenter.getMessageCount() - 1);
    }
}
