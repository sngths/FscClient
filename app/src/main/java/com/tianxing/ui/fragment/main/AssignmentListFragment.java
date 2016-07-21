package com.tianxing.ui.fragment.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tianxing.fscteachersedition.R;
import com.tianxing.presenter.main.AssignmentListPresenter;
import com.tianxing.ui.adapter.AssignmentListAdapter;
import com.tianxing.ui.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by tianxing on 16/7/12.
 */
public class AssignmentListFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, AssignmentListView {

    private AssignmentListAdapter adapter;
    private Unbinder unbinder;
    private AssignmentListPresenter presenter;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    /**
     * 不能再Fragment的构造方法中传递参数 会影响Fragment的销毁重新创建
     */
    public AssignmentListFragment() {

    }

    /**
     * 创建时传入 班级位置信息
     */
    public static AssignmentListFragment newInstance(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        AssignmentListFragment fragment = new AssignmentListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = ((AssignmentFragment) getParentFragment()).getAssignmentPresenter();//取得父Fragment的presenter引用

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_assignment_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {

        swipeRefreshLayout.setColorSchemeColors(R.color.colorPrimary, R.color.colorPrimaryDark);
        swipeRefreshLayout.setOnRefreshListener(this);
        //创建适配器时 传入班级位置
        adapter = new AssignmentListAdapter(getContext(), presenter, getArguments().getInt("position", -1));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //保存Fragment的滑动位置 载入状态等信息 用来恢复Fragment的原先状态

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onRefresh() {
        //通知P层请求数据
        presenter.requestAssignment(getArguments().getInt("position", -1));
    }

    /**
     * 从载数据 撤销下拉载入状态
     */
    @Override
    public void refresh() {
        adapter.notifyDataSetChanged();
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }


    }
}
