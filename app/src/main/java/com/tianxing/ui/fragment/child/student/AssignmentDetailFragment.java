package com.tianxing.ui.fragment.child.student;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.squareup.picasso.Picasso;
import com.tianxing.deprecated.entity.http.json.ImageFile;
import com.tianxing.pojo.transfer.Comment;
import com.tianxing.pojo.transfer.receive.AssignmentDownload;
import com.tianxing.pojo.transfer.receive.ReplyReceived;
import com.tianxing.pojo.transfer.send.ReplyUpload;
import com.tianxing.fscteachersedition.R;
import com.tianxing.presenter.child.student.AssignmentDetailPresenter;
import com.tianxing.presenter.child.student.AssignmentDetailViewPresenter;
import com.tianxing.ui.activity.MainView;
import com.tianxing.ui.fragment.child.BaseBackFragment;
import com.tianxing.util.ScreenSize;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * Created by tianxing on 16/11/2.
 * 学生端作业详情界面
 */

public class AssignmentDetailFragment extends BaseBackFragment {


    private Unbinder unbinder;

    @BindView(R.id.toolbar_child_Fragment)
    Toolbar toolbar;

    //作业信息
    @BindView(R.id.imageButton_icon)
    ImageButton icon;
    @BindView(R.id.textView_title)
    TextView assignmentTitle;
    @BindView(R.id.textView_date)
    TextView assignmentDate;
    @BindView(R.id.textView_time)
    TextView assignmentTime;
    @BindView(R.id.textView_submit)
    TextView assignmentSubmit;//提交状态
    @BindView(R.id.textView_content)
    TextView assignmentContent;
    @BindView(R.id.linearLayout_image_frame)
    LinearLayout ImageFrame;
    //班级标题
    //@BindView(R.id.textView_class_title)
    //TextView classTitle;

    @BindView(R.id.frameLayout_reply)
    FrameLayout replyFrame;
    @BindView(R.id.frameLayout_comment)
    FrameLayout commentFrame;


    private int pictureCount = 0;
    private boolean isWaitingUpload = false;//图片压缩完成后判断 用户是否已经点击了发送


    private LayoutInflater inflater;

    private AssignmentDetailPresenter presenter;
    private AssignmentDownload assignment;

    private List<String> replyImages = new ArrayList<>();

    private int replyImageCount = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new AssignmentDetailViewPresenter(getArguments().getString("classID"), getArguments().getInt("position"));
        assignment = presenter.getAssignment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater = inflater;
        View view = inflater.inflate(R.layout.fragment_assignment_detail_student, container, false);
        unbinder = ButterKnife.bind(this, view);
        toolBarInit(toolbar);
        setToolBarTitle("作业详情");
        setAssignmentViewData(inflater.getContext());
        //classTitle.setText(assignment.getClassName());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //开始获取学生回复
        requestReply();
    }

    @Override
    public void onStart() {
        super.onStart();

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


    private void setAssignmentViewData(Context context) {
        //头像
        Picasso.with(context).load(R.mipmap.user_icon_40dp).resize(ScreenSize.dp2px(48), ScreenSize.dp2px(48)).transform(new CropCircleTransformation()).into(icon);
        //标题
        assignmentTitle.setText(assignment.getTitle());
        String[] dateArray = assignment.getDate().split(" ");
        assignmentDate.setText(dateArray[1]);
        assignmentContent.setText(assignment.getContent());
        //设置图片
        List<ImageFile> images = assignment.getImages();
        if (images != null) {
            ImageFrame.removeAllViews();
            if (images.size() == 1) {
                Log.e("Detail", "设置图片1:" + images.get(0).getUrl());
                ImageButton imageButton = new ImageButton(context);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ScreenSize.dp2px(240), ScreenSize.dp2px(180));
                params.setMargins(8, 0, 8, 8);
                imageButton.setLayoutParams(params);
                Picasso.with(context)
                        .load(images.get(0).getUrl())
                        .resize(ScreenSize.dp2px(240), ScreenSize.dp2px(180))
                        .centerCrop().into(imageButton);
                ImageFrame.addView(imageButton);
            } else if (images.size() > 1 && images.size() <= 3) {
                for (ImageFile imageFile : images) {
                    ImageButton imageButton = new ImageButton(context);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ScreenSize.dp2px(96), ScreenSize.dp2px(96));
                    params.setMargins(8, 0, 8, 8);
                    imageButton.setLayoutParams(params);
                    Picasso.with(context)
                            .load(imageFile.getUrl())
                            .resize(ScreenSize.dp2px(96), ScreenSize.dp2px(96))
                            .centerCrop().into(imageButton);
                    ImageFrame.addView(imageButton);
                }
            } else if (images.size() > 3) {
                for (int i = 0; i < 3; i++) {
                    ImageButton imageButton = new ImageButton(context);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ScreenSize.dp2px(96), ScreenSize.dp2px(96));
                    params.setMargins(8, 0, 8, 8);
                    imageButton.setLayoutParams(params);
                    Picasso.with(context)
                            .load(images.get(i).getUrl())
                            .resize(ScreenSize.dp2px(96), ScreenSize.dp2px(96))
                            .centerCrop().into(imageButton);
                    ImageFrame.addView(imageButton);
                }
            }
        }
    }


    /**
     * 获取该条作业的回复
     */
    private void requestReply() {

        //显示载入界面
        setLoadingView();
        presenter.requestReply().observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<ReplyReceived>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        if (e instanceof JsonMappingException) {
                            //显示输入界面
                            setInputView();
                        } else {
                            //请求错误点击重试
                            setRequestError();
                        }
                    }

                    @Override
                    public void onNext(Response<ReplyReceived> replyReceivedResponse) {
                        Log.e("DetailPresenter", "收到请求的作业回复");
                        if (replyFrame == null){
                            return;
                        }
                        setReplyView(replyReceivedResponse.body());
                        if (replyReceivedResponse.body().getComment() != null){
                            setCommentView(replyReceivedResponse.body().getComment());
                        }
                    }
                });
    }


    /**
     * 显示载入界面
     */
    private void setLoadingView() {
        if (replyFrame == null){
            return;
        }

        if (replyFrame.getChildCount() > 0){
            replyFrame.removeAllViews();
        }

        View view = inflater.inflate(R.layout.activity_loading, null);
        replyFrame.addView(view);
        replyFrame.invalidate();

    }


    /**
     * 显示输入框
     */
    private void setInputView() {
        if (replyFrame == null){
            return;
        }

        if (replyFrame.getChildCount() > 0){
            replyFrame.removeAllViews();
        }
        View view = inflater.inflate(R.layout.view_reply_input, null);
        //设置点击监听
        final EditText input = (EditText) view.findViewById(R.id.editText_content);
        //处理图片添加
        final ImageButton addImageButton = (ImageButton) view.findViewById(R.id.imageButton_add_reply_image);
        final LinearLayout replyImageFrame1 = (LinearLayout) view.findViewById(R.id.linearLayout_image_frame1);
        addImageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                pictureCount +=1;
                ((MainView) getActivity()).startCapture(new MainView.CaptureResult() {
                    @Override
                    public void Successed(File imageFile) {
                        //压缩图片
                        Luban.get(getContext())
                                .load(imageFile)
                                .putGear(Luban.THIRD_GEAR)
                                .setCompressListener(new OnCompressListener() {
                                    @Override
                                    public void onStart() {

                                    }

                                    @Override
                                    public void onSuccess(File file) {
                                        //压缩成功后显示图片
                                        //Log.e(TAG, "压缩成功：" + file.getAbsolutePath());
                                        replyImages.add(file.getAbsolutePath());
                                        showPicture(file);
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        pictureCount -=1;
                                    }
                                }).launch();


                    }

                    @Override
                    public void Cancelled() {

                    }

                    @Override
                    public void Failed() {

                    }
                });
            }

            /**
             * 添加一张显示图片  显示完成后判断是否在等待发布
             * */
            private void showPicture(File imageFile){
                if (replyFrame == null){
                    return;
                }
                if (replyImageCount == 0) {
                    ImageView imageView = new ImageView(getContext());
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ScreenSize.dp2px(96), ScreenSize.dp2px(96));
                    params.setMargins(8, 0, 8, 8);
                    imageView.setLayoutParams(params);
                    Picasso.with(getContext())
                            .load(imageFile)
                            .resize(ScreenSize.dp2px(96), ScreenSize.dp2px(96))
                            .centerCrop().into(imageView);
                    replyImageFrame1.addView(imageView, 0);
                    replyImageCount += 1;
                } else if (replyImageCount == 1) {
                    ImageView imageView = new ImageView(getContext());
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ScreenSize.dp2px(96), ScreenSize.dp2px(96));
                    params.setMargins(8, 0, 8, 8);
                    imageView.setLayoutParams(params);
                    Picasso.with(getContext())
                            .load(imageFile)
                            .resize(ScreenSize.dp2px(96), ScreenSize.dp2px(96))
                            .centerCrop()
                            .into(imageView);
                    replyImageFrame1.addView(imageView, 1);
                    replyImageCount += 1;
                } else if (replyImageCount == 2) {
                    ImageView imageView = new ImageView(getContext());
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ScreenSize.dp2px(96), ScreenSize.dp2px(96));
                    params.setMargins(8, 0, 8, 8);
                    imageView.setLayoutParams(params);
                    Picasso.with(getContext())
                            .load(imageFile)
                            .resize(ScreenSize.dp2px(96), ScreenSize.dp2px(96))
                            .centerCrop()
                            .into(imageView);
                    replyImageFrame1.addView(imageView, 2);
                    replyImageFrame1.removeView(addImageButton);
                    replyImageCount += 1;
                }
                replyImageFrame1.invalidate();


                if (isWaitingUpload){
                    ReplyUpload replyUpload = new ReplyUpload();
                    replyUpload.setAssignmentID(assignment.getId());
                    replyUpload.setContent(input.getText().toString());
                    replyUpload.setTitle("");
                    uploadReplyImages(replyUpload);
                }
            }
        });

        //回复提交按钮
        Button commit = (Button) view.findViewById(R.id.button_commit);
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //开始回复作业
                if (replyFrame == null){
                    return;
                }

                if (pictureCount != replyImages.size()){
                    isWaitingUpload = true;
                    return;
                }

                ReplyUpload replyUpload = new ReplyUpload();
                replyUpload.setAssignmentID(assignment.getId());
                replyUpload.setContent(input.getText().toString());
                replyUpload.setTitle("");
                uploadReplyImages(replyUpload);
            }
        });

        replyFrame.addView(view);
        replyFrame.invalidate();
    }


    /**
     * 提示请求失败点击重试
     */
    private void setRequestError() {
        if (replyFrame == null){
            return;
        }

        if (replyFrame.getChildCount() > 0){
            replyFrame.removeAllViews();
        }
        View view = inflater.inflate(R.layout.view_reload, null);
        TextView hint = (TextView) view.findViewById(R.id.textView_hint);
        Button confirm = (Button) view.findViewById(R.id.button_confirm);
        hint.setText("请求失败点击重试");
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //重新请求作业回复
                requestReply();
            }
        });
        replyFrame.addView(view);
        replyFrame.invalidate();
    }


    /**
     * 显示 回复界面数据
     */
    private void setReplyView(ReplyReceived reply) {
        if (replyFrame == null){
            return;
        }

        if (replyFrame.getChildCount() > 0){
            replyFrame.removeAllViews();
        }
        View view = inflater.inflate(R.layout.view_reply_student, null);
        //设置作业数据
        TextView date = (TextView) view.findViewById(R.id.textView_time);
        String[] dateArray = reply.getDate().split(" ");
        date.setText(dateArray[1]);
        TextView content = (TextView) view.findViewById(R.id.textView_content);
        content.setText(reply.getContent());
        //设置图片
        LinearLayout replyImageFrame = (LinearLayout) view.findViewById(R.id.linearLayout_reply_image_frame);
        List<ImageFile> images = reply.getImages();
        replyImageFrame.removeAllViews();
        if (images != null) {
            if (images.size() == 1) {
                ImageButton imageButton = new ImageButton(getContext());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ScreenSize.dp2px(240), ScreenSize.dp2px(180));
                params.setMargins(8, 0, 8, 8);
                imageButton.setLayoutParams(params);
                Picasso.with(getContext())
                        .load(images.get(0).getUrl())
                        .resize(ScreenSize.dp2px(240), ScreenSize.dp2px(180))
                        .centerCrop().into(imageButton);
                replyImageFrame.addView(imageButton);
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
                    replyImageFrame.addView(imageButton);
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
                    replyImageFrame.addView(imageButton);
                }
            }
            replyFrame.addView(view);
            replyFrame.invalidate();
        }
    }


    /**
     * 上传回复
     * */
    private void uploadReply(ReplyUpload replyUpload){
        presenter.uploadReply(replyUpload).subscribe(new Subscriber<Response<ReplyReceived>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(Response<ReplyReceived> replyReceivedResponse) {
                //上传成功
                Log.e("Detail", "回复上传成功 ");
                setReplyView(replyReceivedResponse.body());

            }
        });
    }




    /**
     * 上传回复图片
     * */
    private void uploadReplyImages(final ReplyUpload replyUpload){
        if (pictureCount != replyImages.size()){
            return;
        }

        //上传图片
        final List<ImageFile> imageFiles = new ArrayList<>();
        //设置图片
        replyUpload.setImages(imageFiles);
        if (replyImages.size() == 0) {
            //不包含图片直接上传
            uploadReply(replyUpload);
        } else if (replyImages.size() == 1) {
            //上传单个图片
            presenter.uploadImage(replyImages.get(0))
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<Response<ImageFile>>() {
                        @Override
                        public void onCompleted() {
                            //完成后开始上传作业
                            replyUpload.setImages(imageFiles);
                            uploadReply(replyUpload);
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onNext(Response<ImageFile> imageFileResponse) {
                            //保存图片信息
                            imageFiles.add(imageFileResponse.body());
                        }
                    });
        } else {
            //上传多张图片
            presenter.uploadImageSet(replyImages).subscribe(new Subscriber<Response<ImageFile>>() {
                @Override
                public void onCompleted() {
                    //完成后开始上传作业
                    uploadReply(replyUpload);
                }

                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                }

                @Override
                public void onNext(Response<ImageFile> imageFileResponse) {
                    //保存图片信息
                    imageFiles.add(imageFileResponse.body());
                }
            });
        }

        //开始上传
        Log.e("Detail", "开始上传作业");
        uploadReply(replyUpload);

    }


    /**
     * 显示批阅结果
     * */
    private void setCommentView(Comment comment){
        commentFrame.removeAllViews();
        View view = inflater.inflate(R.layout.view_comment_student, null);
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
}
