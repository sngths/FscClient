package com.tianxing.ui.fragment.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.tianxing.fscteachersedition.R;
import com.tianxing.presenter.main.ContactsPresenter;
import com.tianxing.presenter.main.ContactsViewPresenter;
import com.tianxing.ui.adapter.ContactsExpandableViewAdaper;
import com.tianxing.ui.adapter.ContactsParentItemList;
import com.tianxing.ui.fragment.BaseFragment;
import com.tianxing.ui.listener.ExpandableViewOnClickListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by tianxing on 16/7/11.
 */
public class ContactsFragment extends BaseFragment implements ExpandableRecyclerAdapter.ExpandCollapseListener, ContactsView, ExpandableViewOnClickListener{
    public static final String TAG = "ContactsFragment";

    private static ContactsFragment fragment = null;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private Unbinder unbinder;

    private ContactsPresenter presenter;
    private ArrayList<ContactsParentItemList> parentItemLists;//折叠列表数据
    private ContactsExpandableViewAdaper expandableViewAdaper;


    public ContactsFragment(){
        presenter = new ContactsViewPresenter(this);
        parentItemLists = new ArrayList<>();
    }

    public static ContactsFragment getInstance(){
        if (fragment == null){
            fragment = new ContactsFragment();
        }

        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contects, container, false);
        unbinder = ButterKnife.bind(this, view);
        ExpandableViewInitialize();
        return view;
    }


    /**
     * 通过Presenter获取数据来初始化可折叠列表
     * */
    private void ExpandableViewInitialize(){

        ArrayList<ContactsParentItemList> parentItemLists = new ArrayList<>();
        //群组列表
        parentItemLists.add(new ContactsParentItemList<>(presenter.getGroupInfos()));
        //好友列表
        parentItemLists.add(new ContactsParentItemList<>(presenter.getFriendsInfo()));
        //班级学生列表
        for (int i = 0; i < presenter.getClassCount(); i++) {
            parentItemLists.add(new ContactsParentItemList<>(presenter.getStudentList(i)));
        }
        expandableViewAdaper = new ContactsExpandableViewAdaper(getContext(), parentItemLists);
        expandableViewAdaper.setExpandCollapseListener(this);
        expandableViewAdaper.setOnClickListener(this);
        recyclerView.setAdapter(expandableViewAdaper);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }



    /**
     * Called when a list item is expanded.
     *
     * @param position The index of the item in the list being expanded
     */
    @Override
    public void onListItemExpanded(int position) {

    }

    /**
     * Called when a list item is collapsed.
     *
     * @param position The index of the item in the list being collapsed
     */
    @Override
    public void onListItemCollapsed(int position) {

    }

    /**
     * 折叠列表点击
     *
     * @param position 被点击的子项在折叠列表完全展开时所在的位置
     */
    @Override
    public void OnClick(Integer position) {
        //presenter层获取数据 返回给主Activity 启动新界面
        int parentPosition;
        int childPosition;
        if (position <= presenter.getGroupCount()){
            parentPosition = 0;
            childPosition = position - 1;
        }else if (position > presenter.getGroupCount() && position <= (presenter.getGroupCount() + presenter.getFriendsCount() + 1)){
            parentPosition = 1;
            childPosition = position - presenter.getGroupCount() - 2;
        }else {

        }



        //((MainView)getActivity()).startChatFragment(parentPosition, childPosition);
    }
}
