package com.tianxing.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.tianxing.fscteachersedition.R;
import com.tianxing.ui.fragment.child.AssignemntReleaseFragment;
import com.tianxing.ui.fragment.child.AssignmentDetailFragment;
import com.tianxing.ui.fragment.child.ChatFragment;
import com.tianxing.ui.fragment.main.MainFragment;

/**
 * Created by tianxing on 16/7/5.
 *
 */
public class MainActivity extends BaseActivity implements MainView {


    private String currentFragmentTag; //当前正在显示的Fragment的Tag



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentFragmentTag = MainFragment.TAG;
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
    public void startAssignmentDetailFragment(Integer classID, Integer position) {
        AssignmentDetailFragment fragment = new AssignmentDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("classID", classID);
        bundle.putInt("position", position);
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frameLayout, fragment)
                .hide(getSupportFragmentManager().findFragmentByTag(MainFragment.TAG))
                .addToBackStack(null)
                .commit();
    }

    /**
     * 启动作业发布界面
     */
    @Override
    public void startAssignmentReleaseFragment() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frameLayout, new AssignemntReleaseFragment(), AssignemntReleaseFragment.TAG)
                .hide(getSupportFragmentManager().findFragmentByTag(MainFragment.TAG))
                .addToBackStack(null)
                .commit();
    }

    /**
     * 启动聊天界面 包括群聊和一对一聊天界面
     *
     * @param parentPosition
     * @param childPosition
     */
    @Override
    public void startChatFragment(Integer parentPosition, Integer childPosition) {
        //启动对应会话界面
        Bundle bundle = new Bundle();
        bundle.putInt("parentPosition", parentPosition);
        bundle.putInt("childPosition", childPosition);
        ChatFragment chatFragment = new ChatFragment();
        chatFragment.setArguments(bundle);
    }

    /**
     * 返回 操作
     */
    @Override
    public void popBack() {
        getSupportFragmentManager().popBackStack();
    }





}
