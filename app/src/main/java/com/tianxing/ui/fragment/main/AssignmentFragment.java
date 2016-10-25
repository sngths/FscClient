package com.tianxing.ui.fragment.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tianxing.fscteachersedition.R;
import com.tianxing.presenter.main.AssignmentPresenter;
import com.tianxing.presenter.main.AssignmentViewPresenter;
import com.tianxing.ui.AssingmentView;
import com.tianxing.ui.adapter.AssignemntViewPagerAdapter;
import com.tianxing.ui.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by tianxing on 16/7/11.
 */
public class AssignmentFragment extends BaseFragment implements AssingmentView{
    public static final String TAG = "AssignmentFragment";


    private final AssignmentViewPresenter presenter;
    private AssignemntViewPagerAdapter adapter;

    private Unbinder unbinder;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;


    public AssignmentFragment(){
        presenter = new AssignmentViewPresenter(this);
    }




    public static AssignmentFragment newInstance(){
        Bundle args = new Bundle();
        AssignmentFragment fragment = new AssignmentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_assignment, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    /**
     * 初始化作业主界面
     * */
    private void initView(){
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        //tabLayout.addTab(tabLayout.newTab());
        //tabLayout.addTab(tabLayout.newTab());
        //tabLayout.addTab(tabLayout.newTab());
        adapter = new AssignemntViewPagerAdapter(getChildFragmentManager(), presenter);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
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


    /**
     * 刷新作业数据
     *
     * @param classId
     */
    @Override
    public void refreshAssignment(Integer classId) {
        //通知相应的Fragment跟新界面

        AssignmentListView view = adapter.findItem(classId);

        if (view != null){
            view.refresh();
        }

    }

    /**
     * 供子Fragment取得P层引用
     * */
    public AssignmentPresenter getAssignmentPresenter(){
        return presenter;
    }
}
