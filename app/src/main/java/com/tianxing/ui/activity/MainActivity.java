package com.tianxing.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.tianxing.fscteachersedition.R;
import com.tianxing.model.App;
import com.tianxing.model.user.User;
import com.tianxing.ui.fragment.child.teacher.AssignmentCommentFragment;
import com.tianxing.ui.fragment.child.teacher.AssignmentDetailFragment;
import com.tianxing.ui.fragment.child.AssignmentReleaseFragment;
import com.tianxing.ui.fragment.child.BaseBackFragment;
import com.tianxing.ui.fragment.child.ChatFragment;
import com.tianxing.ui.fragment.child.ChatGroupFragment;
import com.tianxing.ui.fragment.main.MainFragment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Stack;

/**
 * Created by tianxing on 16/7/5.
 *
 */
public class MainActivity extends BaseActivity implements MainView {
    private static final String TAG = "MainActivity";
    public static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1;
    private File imageFile;
    private CaptureResult result;

    private User user;

    private String currentFragmentTag; //当前正在显示的Fragment的Tag
    Stack<BaseBackFragment> fragmentStack = new Stack<>();//Fragment栈


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentFragmentTag = MainFragment.TAG;
        user = App.getInstance().getCurrentUser();
        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout, new MainFragment(), MainFragment.TAG).commit();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在被销毁时保存状态



    }


    /**
     * 启动一个作业详情界面
     *
     * @param classID
     * @param position
     */
    @Override
    public void startAssignmentDetailFragment(String classID, Integer position) {
        BaseBackFragment fragment;
        if(user.getUserType() == User.UserType.student){
            fragment = new com.tianxing.ui.fragment.child.student.AssignmentDetailFragment();
        }else if (user.getUserType() == User.UserType.teacher){
            fragment = new AssignmentDetailFragment();
        }else {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("classID", classID);
        bundle.putInt("position", position);//作业数据所在列表中的位置
        fragment.setArguments(bundle);
        startFragment(fragment);
    }

    /**
     * 启动回复详情界面
     */
    @Override
    public void startAssignmentCommentFragment(String assignmentID, String studentID) {
        AssignmentCommentFragment fragment = new AssignmentCommentFragment();
        Bundle bundle = new Bundle();
        bundle.putString("assignmentID", assignmentID);
        bundle.putString("studentID", studentID);
        fragment.setArguments(bundle);
        startFragment(fragment);
    }

    /**
     * 启动作业发布界面
     */
    @Override
    public void startAssignmentReleaseFragment() {
        startFragment(new AssignmentReleaseFragment());
    }



    @Override
    public void startChatFragment(String username) {
        Bundle bundle = new Bundle();
        bundle.putString("userName", username);
        ChatFragment fragment = new ChatFragment();
        fragment.setArguments(bundle);
        startFragment(fragment);
    }



    @Override
    public void startGroupChatFragment(String roomName) {
        Bundle bundle = new Bundle();
        bundle.putString("roomName", roomName);
        ChatGroupFragment fragment = new ChatGroupFragment();
        fragment.setArguments(bundle);
        startFragment(fragment);
    }

    /**
     * 启动聊天界面 包括群聊和一对一聊天界面
     *
     * @param parentPosition
     * @param childPosition
*/    /*@Override
    public void startChatFragment(Integer parentPosition, Integer childPosition) {
        //启动对应会话界面

        //取得用户名

        Bundle bundle = new Bundle();
        if (parentPosition == 1){//好友组
            bundle.putString("userName", App.getInstance().getContactsPool().getFriendInfo(childPosition).getUsername());
        }else if (parentPosition > 1){//学生组
            bundle.putString("userName", App.getInstance().getContactsPool().getStudentInfo(parentPosition - 2 , childPosition).getUsername());
        }

        BaseBackFragment fragment;
        if (parentPosition == 0){
            fragment = new ChatGroupFragment();
        }else {
            fragment = new ChatFragment();
        }
        fragment.setArguments(bundle);
        startFragment(fragment);
    }*/





    /**
     * 启动一个Fragment
     * */
    private void startFragment(BaseBackFragment fragment){
        Fragment currentFragment;
        if (fragmentStack.empty()){
            currentFragment = getSupportFragmentManager().findFragmentByTag(MainFragment.TAG);
        }else {
            currentFragment = fragmentStack.peek();
        }
        fragmentStack.push(fragment);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frameLayout, fragment)
                .hide(currentFragment)
                .addToBackStack(null)
                .commit();
    }

    /**
     * 返回 操作
     */
    @Override
    public void popBack() {
        getSupportFragmentManager().popBackStack();
        if (!fragmentStack.empty()){
            fragmentStack.pop();
        }
    }

    /**
     * 启动拍照Activity
     */
    @Override
    public void startCapture(CaptureResult result) {
        this.result = result;
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "FscImage");
        if (!mediaStorageDir.exists()){
            mediaStorageDir.mkdir();
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        imageFile = new File(mediaStorageDir.getPath(),"IMG"+timeStamp+".jpg");
        Uri fileUri = Uri.fromFile(imageFile);
        Log.e("照片保存地址", fileUri.getPath());

        //getOutputMediaFileUri(MEDIA_TYPE_IMAGE); // create a file to save the image
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file name

        // start the image capture Intent
        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null){
                    data.getData().toString();
                    Log.e(TAG, "拍照完成" + data.getData());
                }

                Log.e(TAG, "拍照成功");
                //Log.e("拍照完成", fileUri.toString());
                //FileTransfer.upload(fileUri);
                // Image captured and saved to fileUri specified in the Intent
                //Toast.makeText(this, "Image saved to:\n" +data.getData(), Toast.LENGTH_LONG).show();

                result.Successed(imageFile);
            } else if (resultCode == Activity.RESULT_CANCELED) {
                // User cancelled the image capture
                result.Cancelled();
            } else {
                result.Failed();
                // Image capture failed, advise user
            }
        }
    }

    @Override
    public void onBackPressed() {
        //按键返回时Fragment出栈
        if (!fragmentStack.empty()){
            fragmentStack.pop();
            //fragment栈不为空则返回交个系统处理
            super.onBackPressed();
        }else {
            //拦截返回 退出提示框

        }

    }
}
