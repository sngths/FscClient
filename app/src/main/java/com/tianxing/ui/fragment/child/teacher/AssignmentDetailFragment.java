package com.tianxing.ui.fragment.child.teacher;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tianxing.entity.http.json.ImageFile;
import com.tianxing.entity.info.StudentInfo;
import com.tianxing.entity.transfer.receive.AssignmentDownload;
import com.tianxing.fscteachersedition.R;
import com.tianxing.model.App;
import com.tianxing.model.ContactsPool;
import com.tianxing.presenter.child.teacher.AssignmentDetailPresenter;
import com.tianxing.presenter.child.teacher.AssignmentDetailViewPresenter;
import com.tianxing.ui.activity.MainView;
import com.tianxing.ui.adapter.teacher.ReplyStudentListAdapter;
import com.tianxing.ui.fragment.child.BaseBackFragment;
import com.tianxing.ui.listener.ReplyListItemOnClickListener;
import com.tianxing.util.ScreenSize;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Response;
import rx.Subscriber;

/**
 * Created by tianxing on 16/11/1.
 * 教师端作业详情界面
 */

public class AssignmentDetailFragment extends BaseBackFragment implements AssignmentDetailView {


    private Unbinder unbinder;
    @BindView(R.id.toolbar_child_Fragment)
    Toolbar toolbar;
    //作业信息
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
    @BindView(R.id.textView_class_title)
    TextView classTitle;

    //学生列表框
    @BindView(R.id.frameLayout_reply_list)
    FrameLayout frame;


    private AssignmentDetailPresenter presenter;
    private AssignmentDownload assignment;

    private LayoutInflater inflater;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new AssignmentDetailViewPresenter(getArguments().getString("classID"), getArguments().getInt("position"));
        assignment = presenter.getAssignment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_assignment_detail_teacher, container, false);
        this.inflater = inflater;
        unbinder = ButterKnife.bind(this, view);
        toolBarInit(toolbar);
        setToolBarTitle("作业详情");
        setAssignmentViewData(inflater.getContext());
        classTitle.setText("已提交作业学生：");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //开始获取学生列表
        loadStudentList();
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

    /**
     * 设置作业数据
     */
    private void setAssignmentViewData(Context context) {
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
     * 开始载入学生列表学生列表
     * */
    private void loadStudentList(){
        if (frame == null){
            return;
        }
        setLoadingView();
        presenter.loadStudentList().subscribe(new Subscriber<Response<List<StudentInfo>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(Response<List<StudentInfo>> listResponse) {
                Log.e("Detail", "请求到回复学生列表 " + listResponse.body().size());
                //保存学生信息键值对
                ContactsPool pool = App.getInstance().getContactsPool();
                for (StudentInfo info: listResponse.body()) {
                    pool.putStudentInfo(info.getUserName(), info);
                }
                setReplyList(listResponse.body());
            }
        });
    }



    /**
     * 加载loading视图
     * */
    private void setLoadingView(){
        if (frame == null){
            return;
        }
        if (frame.getChildCount() > 0){
            frame.removeAllViews();
        }
        View view = inflater.inflate(R.layout.view_loading, null);
        frame.addView(view);
        frame.invalidate();
    }

    /**
     * 加载学生列表
     * */
    private void setReplyList(final List<StudentInfo> studentInfos){
        if (frame == null){
            return;
        }
        View view = inflater.inflate(R.layout.view_reply_student_list_teacher, null);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_student_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ReplyStudentListAdapter adapter = new ReplyStudentListAdapter(inflater, studentInfos);
        recyclerView.setAdapter(adapter);
        adapter.setItemOnClickListener(new ReplyListItemOnClickListener() {
            @Override
            public void OnClick(int position) {
                //启动批阅界面
                ((MainView)getActivity()).startAssignmentCommentFragment(assignment.getId(), studentInfos.get(position).getUserName());
            }
        });
        if (frame.getChildCount() > 0){
            frame.removeAllViews();
        }

        frame.addView(view);
        frame.invalidate();
    }

}
