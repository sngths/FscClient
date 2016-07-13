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
import com.tianxing.ui.adapter.AssignemntAdapter;
import com.tianxing.ui.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by tianxing on 16/7/11.
 */
public class AssignmentFragment extends BaseFragment {
    public static final String TAG = "AssignmentFragment";


    private static AssignmentFragment fragment = null;


    private Unbinder unbinder;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;


    public static AssignmentFragment getInstance(){
        if (fragment == null){
            fragment = new AssignmentFragment();
        }
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
     *
     * */
    private void initView(){
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        viewPager.setAdapter(new AssignemntAdapter(getChildFragmentManager()));
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
}
