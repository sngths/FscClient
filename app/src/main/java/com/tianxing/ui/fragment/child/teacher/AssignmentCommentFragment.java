package com.tianxing.ui.fragment.child.teacher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.tianxing.deprecated.entity.http.json.ImageFile;
import com.tianxing.entity.transfer.Comment;
import com.tianxing.entity.transfer.receive.ReplyReceived;
import com.tianxing.fscteachersedition.R;
import com.tianxing.presenter.child.teacher.AssignmentCommentPresenter;
import com.tianxing.presenter.child.teacher.AssignmentCommentViewPresenter;
import com.tianxing.ui.fragment.child.BaseBackFragment;
import com.tianxing.util.ScreenSize;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import retrofit2.Response;
import rx.Subscriber;

/**
 * Created by tianxing on 16/7/28.
 * 老师批阅作业界面
 */
public class AssignmentCommentFragment extends BaseBackFragment implements AssignmentCommentView, View.OnClickListener{


    private Unbinder unbinder;
    @BindView(R.id.toolbar_child_Fragment)
    Toolbar toolbar;

    @BindView(R.id.frameLayout_reply)
    FrameLayout replyFrame;
    @BindView(R.id.frameLayout_comment)
    FrameLayout commentFrame;

    private LayoutInflater inflater;

    private AssignmentCommentPresenter presenter;

    private ReplyReceived reply;

    private Integer score;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new AssignmentCommentViewPresenter(getArguments().getString("assignmentID"), getArguments().getString("studentID"));
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_assignment_comment, container, false);
        unbinder = ButterKnife.bind(this, view);
        toolBarInit(toolbar);
        setToolBarTitle("批阅");
        this.inflater = inflater;
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //开始请求作业回复 和批阅 数据
        requestReply();
        //setCommentInputView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }




    @Override
    public void onClick(View v) {

    }




    /**
     * 请求作业回复
     * */
    private void requestReply(){
        replyFrame.removeAllViews();
        setReplyLoadingView();
        presenter.requestReply().subscribe(new Subscriber<Response<ReplyReceived>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Response<ReplyReceived> replyReceivedResponse) {
                reply = replyReceivedResponse.body();
                setReplyView(replyReceivedResponse.body());
                if (reply.getComment() != null){
                    setCommentView(reply.getComment());
                }else {
                    setCommentInputView();
                }
            }
        });


    }


    /**
     * 请求作业批阅信息
     * */
    private void requestComment(){

    }


    /**
     * 显示回复数据loading界面
     * */
    private void setReplyLoadingView(){
        replyFrame.removeAllViews();
        View view = inflater.inflate(R.layout.view_loading, null);
        replyFrame.addView(view);
        replyFrame.invalidate();
    }


    /**
     * 显示评语数据loading界面
     * */
    private void setCommentLoadingView(){
        commentFrame.removeAllViews();
        View view = inflater.inflate(R.layout.view_loading, null);
        commentFrame.addView(view);
        commentFrame.invalidate();
    }


    /**
     * 显示回复数据界面
     * */
    private void setReplyView(ReplyReceived reply){
        replyFrame.removeAllViews();
        View view = inflater.inflate(R.layout.view_reply_teacher, null);
        ImageButton icon = (ImageButton) view.findViewById(R.id.imageButton_icon);
        TextView username = (TextView) view.findViewById(R.id.textView_username);
        TextView data = (TextView) view.findViewById(R.id.textView_date);
        TextView time = (TextView) view.findViewById(R.id.textView_time);
        TextView content = (TextView) view.findViewById(R.id.textView_content);
        LinearLayout imageFrame = (LinearLayout) view.findViewById(R.id.linearLayout_image_frame1);

        Picasso.with(getContext()).load(R.mipmap.user_icon_40dp).transform(new CropCircleTransformation()).into(icon);
        username.setText(presenter.getStudentInfo(reply.getStudentID()).getNickName());
        String[] dateArray = reply.getDate().split(" ");
        data.setText(dateArray[1]);
        content.setText(reply.getContent());
        //设置图片
        List<ImageFile> images = reply.getImages();
        if (images != null) {
            imageFrame.removeAllViews();
            if (images.size() == 1) {
                ImageButton imageButton = new ImageButton(getContext());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ScreenSize.dp2px(240), ScreenSize.dp2px(180));
                params.setMargins(8, 0, 8, 8);
                imageButton.setLayoutParams(params);
                Picasso.with(getContext())
                        .load(images.get(0).getUrl())
                        .resize(ScreenSize.dp2px(240), ScreenSize.dp2px(180))
                        .centerCrop().into(imageButton);
                imageFrame.addView(imageButton);
            } else if (images.size() > 1 && images.size() <= 3) {
                for (ImageFile imageFile : images) {
                    ImageButton imageButton = new ImageButton(getContext());
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ScreenSize.dp2px(96), ScreenSize.dp2px(96));
                    params.setMargins(8, 0, 8, 8);
                    imageButton.setLayoutParams(params);
                    Picasso.with(getContext())
                            .load(imageFile.getUrl())
                            .resize(ScreenSize.dp2px(96), ScreenSize.dp2px(96))
                            .centerCrop().into(imageButton);
                    imageFrame.addView(imageButton);
                }
            } else if (images.size() > 3) {
                for (int i = 0; i < 3; i++) {
                    ImageButton imageButton = new ImageButton(getContext());
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ScreenSize.dp2px(96), ScreenSize.dp2px(96));
                    params.setMargins(8, 0, 8, 8);
                    imageButton.setLayoutParams(params);
                    Picasso.with(getContext())
                            .load(images.get(i).getUrl())
                            .resize(ScreenSize.dp2px(96), ScreenSize.dp2px(96))
                            .centerCrop().into(imageButton);
                    imageFrame.addView(imageButton);
                }
            }
        }



        replyFrame.addView(view);
        replyFrame.invalidate();
    }

    /**
     * 载入评论输入界面
     * */
    private void setCommentInputView(){
        commentFrame.removeAllViews();
        View view = inflater.inflate(R.layout.view_comment_input_teacher, null);
        final CheckBox checkBox1 = (CheckBox) view.findViewById(R.id.checkBox1);
        final CheckBox checkBox2 = (CheckBox) view.findViewById(R.id.checkBox2);
        final CheckBox checkBox3 = (CheckBox) view.findViewById(R.id.checkBox3);
        final CheckBox checkBox4 = (CheckBox) view.findViewById(R.id.checkBox4);
        final CheckBox checkBox5 = (CheckBox) view.findViewById(R.id.checkBox5);
        final EditText editTextComment = (EditText) view.findViewById(R.id.editText_comment);
        Button commitButton = (Button) view.findViewById(R.id.button_commit);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == checkBox1){
                    checkBox1.setChecked(true);
                    checkBox2.setChecked(false);
                    checkBox3.setChecked(false);
                    checkBox4.setChecked(false);
                    checkBox5.setChecked(false);
                    score = 1;
                }else if (v == checkBox2){
                    checkBox1.setChecked(true);
                    checkBox2.setChecked(true);
                    checkBox3.setChecked(false);
                    checkBox4.setChecked(false);
                    checkBox5.setChecked(false);
                    score = 2;
                }else if (v == checkBox3){
                    checkBox1.setChecked(true);
                    checkBox2.setChecked(true);
                    checkBox3.setChecked(true);
                    checkBox4.setChecked(false);
                    checkBox5.setChecked(false);
                    score = 3;
                }else if (v == checkBox4){
                    checkBox1.setChecked(true);
                    checkBox2.setChecked(true);
                    checkBox3.setChecked(true);
                    checkBox4.setChecked(true);
                    checkBox5.setChecked(false);
                    score = 4;
                }else if (v == checkBox5){
                    checkBox1.setChecked(true);
                    checkBox2.setChecked(true);
                    checkBox3.setChecked(true);
                    checkBox4.setChecked(true);
                    checkBox5.setChecked(true);
                    score = 5;
                }
            }
        };
        checkBox1.setOnClickListener(listener);
        checkBox2.setOnClickListener(listener);
        checkBox3.setOnClickListener(listener);
        checkBox4.setOnClickListener(listener);
        checkBox5.setOnClickListener(listener);
        commitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //提交评论
                if (reply == null){
                    Toast.makeText(getContext(), "等待回复内容载入", Toast.LENGTH_SHORT).show();
                }else if(score == null){
                    Toast.makeText(getContext(), "请输入分数", Toast.LENGTH_SHORT).show();
                }else if(editTextComment.getText().toString().replace(" ", "").equals("")){
                    Toast.makeText(getContext(), "请输入评语", Toast.LENGTH_SHORT).show();
                }else {
                    Comment comment = new Comment();
                    comment.setReplyID(reply.getId());
                    comment.setScore(score);
                    comment.setContent(editTextComment.getText().toString());
                    uploadComment(comment);
                }

            }
        });


        commentFrame.addView(view);
        commentFrame.invalidate();
    }

    /**
     * 载入评语界面
     * */
    private void setCommentView(Comment comment){
        commentFrame.removeAllViews();
        View view = inflater.inflate(R.layout.view_comment_teacher, null);
        CheckBox checkBox1 = (CheckBox) view.findViewById(R.id.checkBox1);
        CheckBox checkBox2 = (CheckBox) view.findViewById(R.id.checkBox2);
        CheckBox checkBox3 = (CheckBox) view.findViewById(R.id.checkBox3);
        CheckBox checkBox4 = (CheckBox) view.findViewById(R.id.checkBox4);
        CheckBox checkBox5 = (CheckBox) view.findViewById(R.id.checkBox5);
        CheckBox[] checkBoxes = {checkBox1, checkBox2, checkBox3, checkBox4, checkBox5};
        TextView textView = (TextView) view.findViewById(R.id.textView_comment);
        for (int i = 0; i < 5; i++) {
            checkBoxes[i].setClickable(false);
        }
        for (int i = 0; i < comment.getScore(); i++) {
            checkBoxes[i].setChecked(true);
        }
        textView.setText(comment.getContent());
        commentFrame.addView(view);
        commentFrame.invalidate();
    }

    /**
     * 上传评语
     * */
    private void uploadComment(final Comment comment){
        //载入界面
        presenter.uploadComment(comment).subscribe(new Subscriber<Response<Void>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                //失败显示输入界面
                setCommentInputView();
            }

            @Override
            public void onNext(Response<Void> voidResponse) {
                //上传成功
                setCommentView(comment);
            }
        });
    }

}
