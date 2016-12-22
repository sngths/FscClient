package com.tianxing.ui.fragment.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.tianxing.entity.transfer.send.AssignmentUpload;
import com.tianxing.entity.http.json.ImageFile;
import com.tianxing.entity.info.ClassInfo;
import com.tianxing.fscteachersedition.R;
import com.tianxing.presenter.child.AssignemntReleaseViewPresenter;
import com.tianxing.presenter.child.AssignmentReleasePresenter;
import com.tianxing.ui.activity.MainView;
import com.tianxing.util.ScreenSize;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * Created by tianxing on 16/7/26.
 * 发布作业界面
 */
public class AssignmentReleaseFragment extends BaseBackFragment {
    public static final String TAG = "ReleaseFragment";

    private int imageCount = 0;
    private List<String> imageFiles = new ArrayList<>(3);

    private List<Integer> radioButtonIdList = new ArrayList<>();//id列表
    private Map<Integer, String> radioButtonIdMap = new HashMap<>();//RadioButtonID对应的 classID

    private String classID;//选择的classID

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
    @BindView(R.id.linearLayout_image_frame1)//图片框1
    LinearLayout imageFrame1;
    @BindView(R.id.linearLayout_image_frame2)//图片框2
    LinearLayout imageFrame2;
    @BindView(R.id.linearLayout_image_frame3)//图片框3
    LinearLayout imageFrame3;
    @BindView(R.id.imageButton)//拍照按钮
    ImageButton captureButton;

    @BindView(R.id.button_commit)
    Button commitButton;


    private boolean isCommitWaiting = false;


    private int pictureCount = 0;//拍摄图片数目


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
        List<ClassInfo> classList = presenter.getClassList();
        radioButtonIdList.add(R.id.radioButton_class1);
        radioButtonIdList.add(R.id.radioButton_class2);
        radioButtonIdList.add(R.id.radioButton_class3);
        radioButtonIdList.add(R.id.radioButton_class4);
        radioButtonIdList.add(R.id.radioButton_class5);
        radioButtonIdList.add(R.id.radioButton_class6);
        radioButtonIdList.add(R.id.radioButton_class7);
        radioButtonIdList.add(R.id.radioButton_class8);
        radioButtonIdList.add(R.id.radioButton_class9);
        radioButtonIdList.add(R.id.radioButton_class10);
        //最多包含10个班级
        if (classList.size() <= 10){
            for (int i = 0; i < classList.size(); i++) {
                RadioButton button = new RadioButton(getContext());
                button.setId(radioButtonIdList.get(i));
                button.setText(classList.get(i).getName());
                radioButtonIdMap.put(radioButtonIdList.get(i), classList.get(i).getId());
                radioGroup.addView(button);
            }
        }else {
            for (int i = 0; i < 10; i++) {
                RadioButton button = new RadioButton(getContext());
                button.setId(radioButtonIdList.get(i));
                button.setText(classList.get(i).getName());
                radioButtonIdMap.put(radioButtonIdList.get(i), classList.get(i).getId());
                radioGroup.addView(button);
            }
        }

        //处理点击
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                classID = radioButtonIdMap.get(checkedId);
            }
        });
    }


    /**
     * 添加图片
     */
    @OnClick(R.id.imageButton)
    public void capture() {
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
                        Log.e(TAG, "压缩成功：" + file.getAbsolutePath());
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
     * 在view中放置图片 最多三张图片   图片显示完成后判断 是否用户已点击发送
     */
    private void showPicture(File imageFile) {
        if (imageCount == 0) {
            ImageView imageView = new ImageView(getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ScreenSize.dp2px(96), ScreenSize.dp2px(96));
            params.setMargins(8, 0, 8, 8);
            imageView.setLayoutParams(params);
            Picasso.with(getContext())
                    .load(imageFile)
                    .resize(ScreenSize.dp2px(96), ScreenSize.dp2px(96))
                    .centerCrop().into(imageView);
            imageFrame1.addView(imageView, 0);
            imageFiles.add(imageFile.getAbsolutePath());
            imageCount += 1;
        } else if (imageCount == 1) {
            ImageView imageView = new ImageView(getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ScreenSize.dp2px(96), ScreenSize.dp2px(96));
            params.setMargins(8, 0, 8, 8);
            imageView.setLayoutParams(params);
            Picasso.with(getContext())
                    .load(imageFile)
                    .resize(ScreenSize.dp2px(96), ScreenSize.dp2px(96))
                    .centerCrop()
                    .into(imageView);
            imageFrame1.addView(imageView, 1);
            imageFiles.add(imageFile.getAbsolutePath());
            imageCount += 1;
        } else if (imageCount == 2) {
            ImageView imageView = new ImageView(getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ScreenSize.dp2px(96), ScreenSize.dp2px(96));
            params.setMargins(8, 0, 8, 8);
            imageView.setLayoutParams(params);
            Picasso.with(getContext())
                    .load(imageFile)
                    .resize(ScreenSize.dp2px(96), ScreenSize.dp2px(96))
                    .centerCrop()
                    .into(imageView);
            imageFrame1.addView(imageView, 2);
            imageFrame1.removeView(captureButton);
            imageFiles.add(imageFile.getAbsolutePath());
            imageCount += 1;
        }
        imageFrame1.invalidate();


        if (isCommitWaiting){
            release();
        }
    }


    /**
     * 发布作业
     */
    @OnClick(R.id.button_commit)
    public void release() {
        CommitDisable();
        if (pictureCount > imageFiles.size()){
            isCommitWaiting = true;
            return;
        }
        String title = editTextTitle.getText().toString();
        String content = editTextContent.getText().toString();
        final List<ImageFile> images = new ArrayList<>();
        final AssignmentUpload assignmentUpload = new AssignmentUpload();
        //判断是否选择了班级
        if (classID == null){
            Toast.makeText(getContext(), "请选择班级", Toast.LENGTH_SHORT).show();
            commitEnable();
            return;
        }
        //判断上传内容是否为空
        if (title.replace(" ", "").equals("") && content.replace(" ", "").equals("") && imageFiles.size() == 0){
            Toast.makeText(getContext(), "请输入作业内容", Toast.LENGTH_SHORT).show();
            commitEnable();
            return;
        }
        assignmentUpload.setClassID(classID);
        assignmentUpload.setContent(content);
        assignmentUpload.setTitle(title);
        //上传图片
        if (imageFiles.size() == 0) {
            //不包含图片直接上传
            uploadAssignment(assignmentUpload);
        } else if (imageFiles.size() == 1) {
            //上传单个图片
            Log.e(TAG, "开始上传单张图片" + imageFiles.get(0));
            presenter.uploadImage(imageFiles.get(0))
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<Response<ImageFile>>() {
                @Override
                public void onCompleted() {
                    //完成后开始上传作业
                    Log.e(TAG, "图片上传成功， 开始上传作业");
                    assignmentUpload.setImages(images);
                    uploadAssignment(assignmentUpload);
                }

                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "发布失败", Toast.LENGTH_SHORT).show();
                    commitEnable();
                }

                @Override
                public void onNext(Response<ImageFile> imageFileResponse) {
                    //保存图片信息
                    Log.e(TAG, "单张图片上传成功 + 返回码" + imageFileResponse.code() );
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
                    e.printStackTrace();
                    Toast.makeText(getContext(), "发布失败", Toast.LENGTH_SHORT).show();
                    commitEnable();
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


    private void commitEnable(){
        commitButton.setClickable(true);
    }


    private void CommitDisable(){
        commitButton.setClickable(false);
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
                e.printStackTrace();
                Toast.makeText(getContext(), "发布失败", Toast.LENGTH_SHORT).show();
                commitEnable();
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
