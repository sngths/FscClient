package com.tianxing.ui.fragment.child.student;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.squareup.picasso.Picasso;
import com.tianxing.entity.http.json.ImageFile;
import com.tianxing.entity.transfer.receive.AssignmentDownload;
import com.tianxing.entity.transfer.receive.ReplyReceived;
import com.tianxing.entity.transfer.send.ReplyUpload;
import com.tianxing.fscteachersedition.R;
import com.tianxing.presenter.child.student.AssignmentDetailPresenter;
import com.tianxing.presenter.child.student.AssignmentDetailViewPresenter;
import com.tianxing.ui.fragment.child.BaseBackFragment;
import com.tianxing.util.ScreenSize;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

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


    private AssignmentDetailPresenter presenter;
    private AssignmentDownload assignment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new AssignmentDetailViewPresenter(getArguments().getString("classID"), getArguments().getInt("position"));
        assignment = presenter.getAssignment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_assignment_detail_student, container, false);
        unbinder = ButterKnife.bind(this, view);
        toolBarInit(toolbar);
        setToolBarTitle("作业详情");
        setAssignmentViewData(inflater.getContext());
        //classTitle.setText(assignment.getClassName());
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        //开始获取学生回复
        requestReply();
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
                        }else {
                            //请求错误点击重试
                            setRepuestError();
                        }
                    }

                    @Override
                    public void onNext(Response<ReplyReceived> replyReceivedResponse) {
                        Log.e("DetailPresenter", "收到请求的作业回复");
                        setReplyView();

                    }
                });
    }


    /**
     * 显示载入界面
     * */
    private void setLoadingView(){
        replyFrame.removeAllViews();
        View view = getLayoutInflater(getArguments()).inflate(R.layout.view_loading, null);
        replyFrame.addView(view);
        replyFrame.invalidate();

    }


    /**
     * 显示输入框
     * */
    private void setInputView(){
        replyFrame.removeAllViews();
        View view = getLayoutInflater(getArguments()).inflate(R.layout.view_reply_input, null);
        //设置点击监听
        final EditText input = (EditText) view.findViewById(R.id.editText_content);
        //处理图片添加


        Button commit = (Button) view.findViewById(R.id.button_commit) ;
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //开始回复作业
                ReplyUpload upload = new ReplyUpload();
                upload.setAssignmentID(assignment.getId());
                upload.setContent(input.getText().toString());
                upload.setTitle("");
                upload.setImages(new ArrayList<Image>());
                //开始上传
                presenter.uploadReply(upload).subscribe(new Subscriber<Response<Void>>() {
                    @Override
                    public void onCompleted() {
                        Log.e("DetailFragment", "上传成功");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Response<Void> voidResponse) {

                    }
                });
            }
        });



        replyFrame.addView(view);
        replyFrame.invalidate();
    }


    /**
     * 提示请求失败点击重试
     * */
    private void setRepuestError(){
        replyFrame.removeAllViews();
        View view = getLayoutInflater(getArguments()).inflate(R.layout.view_reload, null);
        replyFrame.addView(view);
        replyFrame.invalidate();
    }




    /**
     * 显示 回复界面数据
     * */
    private void setReplyView(){
        replyFrame.removeAllViews();
    }

}
