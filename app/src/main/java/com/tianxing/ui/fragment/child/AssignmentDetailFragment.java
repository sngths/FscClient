package com.tianxing.ui.fragment.child;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tianxing.entity.assignment.AssignmentDownload;
import com.tianxing.entity.http.json.ImageFile;
import com.tianxing.fscteachersedition.R;
import com.tianxing.presenter.child.AssignmentDetailPresenter;
import com.tianxing.presenter.child.AssignmentDetailViewPresenter;
import com.tianxing.util.ScreenSize;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by tianxing on 16/11/1.
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
        View view = inflater.inflate(R.layout.fragment_assignment_detail, container, false);
        unbinder = ButterKnife.bind(this, view);
        toolBarInit(toolbar);
        setToolBarTitle("作业详情");
        setAssignmentViewData(inflater.getContext());
        classTitle.setText(assignment.getClassName());
        return view;
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
}
