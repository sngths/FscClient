package com.tianxing.ui.fragment.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.tianxing.fscteachersedition.R;
import com.tianxing.presenter.main.ContactsPresenter;
import com.tianxing.presenter.main.ContactsViewPresenter;
import com.tianxing.ui.activity.MainView;
import com.tianxing.ui.adapter.ContactsExpandableListAdapter;
import com.tianxing.ui.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by tianxing on 16/7/11.
 */
public class ContactsFragment extends BaseFragment implements ContactsView, ExpandableListView.OnChildClickListener{
    public static final String TAG = "ContactsFragment";

    private static ContactsFragment fragment = null;

    //@BindView(R.id.recyclerView)
    //RecyclerView recyclerView;
    @BindView(R.id.expandableListView)
    ExpandableListView expandableListView;
    private Unbinder unbinder;

    private ContactsPresenter presenter;
    //private ArrayList<ContactsParentItemList> parentItemLists;//折叠列表数据
    //private ContactsExpandableViewAdaper expandableViewAdaper;


    public ContactsFragment(){
        presenter = new ContactsViewPresenter(this);
        //parentItemLists = new ArrayList<>();
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
        //ExpandableViewInitialize();
        expandableListView.setAdapter(new ContactsExpandableListAdapter(getContext(), presenter));
        expandableListView.setOnChildClickListener(this);
        return view;
    }


    /**
     * 通过Presenter获取数据来初始化可折叠列表
     *
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
    }*/


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * Callback method to be invoked when a child in this expandable list has
     * been clicked.
     *
     * @param parent        The ExpandableListView where the click happened
     * @param v             The view within the expandable list/ListView that was clicked
     * @param groupPosition The group position that contains the child that
     *                      was clicked
     * @param childPosition The child position within the group
     * @param id            The row id of the child that was clicked
     * @return True if the click was handled
     */
    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        ((MainView)getActivity()).startChatFragment(groupPosition, childPosition);
        return true;
    }


    /**
     * 折叠列表点击
     *
     * @param position 被点击的子项在折叠列表完全展开时所在的位置
     */
    /*@Override
    public void OnClick(Integer position) {
        //presenter层获取数据 返回给主Activity 启动新界面
        int parentPosition = 0;
        int childPosition = 0;
        int itemCount = 0;
        for (int i = 0; i < presenter.getClassCount() + 2; i++) {
            if (position > itemCount + presenter.getChildItemCount(i) + i){

            } else {
                parentPosition = i ;
                childPosition = position - itemCount - i - 1;
                break;
            }
            itemCount = itemCount + presenter.getChildItemCount(i);
        }
        Log.e(String.valueOf(parentPosition), String.valueOf(childPosition) + String.valueOf(position));
        ((MainView)getActivity()).startChatFragment(parentPosition, childPosition);
    }*/
}
