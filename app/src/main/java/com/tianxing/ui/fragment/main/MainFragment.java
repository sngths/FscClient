package com.tianxing.ui.fragment.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tianxing.fscteachersedition.R;
import com.tianxing.ui.activity.MainActivity;
import com.tianxing.ui.fragment.BaseFragment;
import com.tianxing.ui.view.BottomBar;
import com.tianxing.ui.view.BottomBarReleaseTab;
import com.tianxing.ui.view.BottomBarTab;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by tianxing on 16/7/11.
 * 包含整个界面的Fragment
 */
public class MainFragment extends BaseFragment {
    public static final String TAG = "MainFragment";

    private Unbinder unbinder;
    @BindView(R.id.bottomBar)
    BottomBar bottomBar;
    @BindView(R.id.toolBar)
    Toolbar toolbar;

    private Fragment[] fragments = new Fragment[4];
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, view);
        toolbar.setTitle("作业");
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        AssignmentFragment assignmentFragment;
        ReviewFragment reviewFragment;
        ContactsFragment contactsFragment;
        ConfigurationFragment configurationFragment;

        if (savedInstanceState != null) {
            //内存恢复重启 找出并恢复Fragment状态
            assignmentFragment = (AssignmentFragment) getChildFragmentManager().findFragmentByTag(AssignmentFragment.TAG);
            reviewFragment = (ReviewFragment) getChildFragmentManager().findFragmentByTag(ReviewFragment.TAG);
            contactsFragment = (ContactsFragment) getChildFragmentManager().findFragmentByTag(ContactsFragment.TAG);
            configurationFragment = (ConfigurationFragment) getChildFragmentManager().findFragmentByTag(ConfigurationFragment.TAG);


        } else {
            //正常启动 创建新的Fragment
            assignmentFragment = AssignmentFragment.newInstance();
            reviewFragment = ReviewFragment.getInstance();
            contactsFragment = ContactsFragment.getInstance();
            configurationFragment = ConfigurationFragment.getInstance();
            getChildFragmentManager().beginTransaction()
                    .add(R.id.frameLayout_main_container, assignmentFragment, AssignmentFragment.TAG)
                    .add(R.id.frameLayout_main_container, reviewFragment, ReviewFragment.TAG)
                    .add(R.id.frameLayout_main_container, contactsFragment, ContactsFragment.TAG)
                    .add(R.id.frameLayout_main_container, configurationFragment, ConfigurationFragment.TAG)
                    .show(assignmentFragment)
                    .hide(reviewFragment)
                    .hide(contactsFragment)
                    .hide(configurationFragment)
                    .commit();
        }
        initView();
        fragments[0] = assignmentFragment;
        fragments[1] = reviewFragment;
        fragments[2] = contactsFragment;
        fragments[3] = configurationFragment;
        return view;
    }


    private void initView(){
        bottomBar.addItem(new BottomBarTab(getContext(), R.mipmap.ic_bottombar_icon1_24dp, "作业"));
        bottomBar.addItem(new BottomBarTab(getContext(), R.mipmap.ic_bottombar_icon2_24dp, "课堂"));
        bottomBar.addItem(new BottomBarReleaseTab(getContext()));//发布按钮
        bottomBar.addItem(new BottomBarTab(getContext(), R.mipmap.ic_bottombar_icon3_24dp, "学生"));
        bottomBar.addItem(new BottomBarTab(getContext(), R.mipmap.ic_bottombar_icon3_24dp, "设置"));
        bottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                //显示对应位置的Fragment
                if (position == prePosition)
                    return;
                ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
                switch (position){
                    case 0:
                        getChildFragmentManager().beginTransaction().hide(fragments[1]).hide(fragments[2]).hide(fragments[3]).show(fragments[0]).commit();
                        if (actionBar != null){
                            toolbar.setTitle("作业");
                        }
                        break;
                    case 1:
                        getChildFragmentManager().beginTransaction().hide(fragments[0]).hide(fragments[2]).hide(fragments[3]).show(fragments[1]).commit();
                        if (actionBar != null){
                            toolbar.setTitle("课堂");
                        }
                        break;
                    case 2:
                        break;
                    case 3:
                        getChildFragmentManager().beginTransaction().hide(fragments[0]).hide(fragments[1]).hide(fragments[3]).show(fragments[2]).commit();
                        if (actionBar != null){
                            toolbar.setTitle("学生");
                        }
                        break;
                    case 4:
                        getChildFragmentManager().beginTransaction().hide(fragments[0]).hide(fragments[1]).hide(fragments[2]).show(fragments[3]).commit();
                        if (actionBar != null){
                            toolbar.setTitle("设置");
                        }
                        break;
                }
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {
                //Tab点击 执行刷新等操作
            }

            @Override
            public void onClick() {
                startAssignmentReleaseFragment();
            }
        });
    }


    /**
     * 启动作业发布界面
     * */
    private void startAssignmentReleaseFragment(){
        ((MainActivity)getActivity()).startAssignmentReleaseFragment();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
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
}
