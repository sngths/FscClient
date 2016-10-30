package com.tianxing.ui.fragment.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.tianxing.entity.assignment.AssignmentUpload;
import com.tianxing.entity.http.json.ImageFile;
import com.tianxing.fscteachersedition.R;
import com.tianxing.presenter.child.AssignemntReleaseViewPresenter;
import com.tianxing.presenter.child.AssignmentReleasePresenter;
import com.tianxing.ui.activity.MainView;
import com.tianxing.util.ScreenSize;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Response;
import rx.Subscriber;

/**
 * Created by tianxing on 16/7/26.
 */
public class AssignmentReleaseFragment extends BaseBackFragment {
    public static final String TAG = "ReleaseFragment";

    private int imageCount = 0;
    private List<String> imageFiles = new ArrayList<>(3);

    private AssignmentReleasePresenter presenter;


    private Unbinder unbinder;
    @BindView(R.id.toolbar_child_Fragment)
    Toolbar toolbar;

    @BindView(R.id.radioGroup_class)
    RadioGroup radioGroup;

    @BindView(R.id.editText_title)
    EditText editTextTitle;
    @BindView(R.id.editText_content)
    EditText editTextContent;
    @BindView(R.id.linearLayout_image_frame)//图片框
    LinearLayout imageFrame;
    @BindView(R.id.imageButton)//拍照按钮
    ImageButton captureButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new AssignemntReleaseViewPresenter();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_assignment_release, container, false);
        unbinder = ButterKnife.bind(this, view);
        toolBarInit(toolbar);
        setToolBarTitle("发布作业");
        //添加班级选择
        setClassSelect();
        return view;
    }


    /**
     * 添加班级选择 处理单选监听
     * */
    private void setClassSelect(){
        RadioButton button1 = new RadioButton(getContext());
        button1.setId(R.id.radioButton_class1);
        RadioButton button2 = new RadioButton(getContext());
        RadioButton button3 = new RadioButton(getContext());
        button1.setText("一年级");
        button2.setText("二年级");
        button3.setText("三年级");
        radioGroup.addView(button1);
        radioGroup.addView(button2);
        radioGroup.addView(button3);

        //处理点击
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

            }
        });
    }


    /**
     * 添加图片
     */
    @OnClick(R.id.imageButton)
    public void capture() {
        ((MainView) getActivity()).startCapture(new MainView.CaptureResult() {
            @Override
            public void Successed(File imageFile) {
                showPicture(imageFile);
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
     * 在view中放置图片
     */
    private void showPicture(File imageFile) {
        if (imageCount == 0) {
            ImageView imageView = new ImageView(getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ScreenSize.dp2px(96), ScreenSize.dp2px(96));
            params.setMargins(0, 0, 0, 8);
            imageView.setLayoutParams(params);
            Picasso.with(getContext()).load(imageFile).into(imageView);
            imageFrame.addView(imageView, 0);
            imageFiles.add(imageFile.getAbsolutePath());
            imageCount += 1;
        } else if (imageCount == 1) {
            ImageView imageView = new ImageView(getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ScreenSize.dp2px(96), ScreenSize.dp2px(96));
            params.setMargins(0, 0, 0, 8);
            imageView.setLayoutParams(params);
            Picasso.with(getContext()).load(imageFile).into(imageView);
            imageFrame.addView(imageView, 1);
            imageFiles.add(imageFile.getAbsolutePath());
            imageCount += 1;
        } else if (imageCount == 2) {
            ImageView imageView = new ImageView(getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ScreenSize.dp2px(96), ScreenSize.dp2px(96));
            params.setMargins(0, 0, 0, 8);
            imageView.setLayoutParams(params);
            Picasso.with(getContext()).load(imageFile).into(imageView);
            imageFrame.addView(imageView, 2);
            imageFrame.removeView(captureButton);
            imageFiles.add(imageFile.getAbsolutePath());
            imageCount += 1;
        }
        imageFrame.invalidate();
    }


    /**
     * 发布作业
     */
    @OnClick(R.id.button_release)
    public void release() {

        String title = editTextTitle.getText().toString();
        String content = editTextContent.getText().toString();
        final List<ImageFile> images = new ArrayList<>();
        final AssignmentUpload assignmentUpload = new AssignmentUpload();
        //判断是否选择了班级

        //判断上传内容是否为空
        if (title.replace(" ", "").equals("") || content.replace(" ", "").equals("") || imageFiles.size() == 0){
            Toast.makeText(getContext(), "请输入作业内容", Toast.LENGTH_SHORT).show();
            return;
        }
        assignmentUpload.setClassID("c1g1");
        assignmentUpload.setContent(content);
        assignmentUpload.setTitle(title);
        //上传图片
        if (imageFiles.size() == 0) {

        } else if (imageFiles.size() == 1) {
            //上传单个图片
            presenter.uploadImage(imageFiles.get(0)).subscribe(new Subscriber<Response<ImageFile>>() {
                @Override
                public void onCompleted() {
                    //完成后开始上传作业
                    assignmentUpload.setImages(images);
                    uploadAssignment(assignmentUpload);
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(Response<ImageFile> imageFileResponse) {
                    //保存图片信息
                    images.add(imageFileResponse.body());
                }
            });
        } else {
            //上传多张图片
            presenter.uploadImageSet(imageFiles).subscribe(new Subscriber<Response<ImageFile>>() {
                @Override
                public void onCompleted() {
                    //完成后开始上传作业
                    Log.e(TAG, "一组图片上传完成");
                    assignmentUpload.setImages(images);
                    uploadAssignment(assignmentUpload);
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(Response<ImageFile> imageFileResponse) {
                    //保存图片信息
                    Log.e(TAG, "上传完成一张图片 " + imageFileResponse.body().getName());
                    images.add(imageFileResponse.body());
                }
            });
        }

    }



    /**
     * 上传作业
     * */
    private void uploadAssignment(AssignmentUpload assignment){
        presenter.uploadAssignment(assignment).subscribe(new Subscriber<Response<Void>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Response<Void> voidResponse) {
                if (voidResponse.code() == 200){
                    //上传完成 关闭当前窗口
                    Log.e(TAG, "作业上传成功");
                    ((MainView)getActivity()).popBack();
                }
            }
        });
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
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
