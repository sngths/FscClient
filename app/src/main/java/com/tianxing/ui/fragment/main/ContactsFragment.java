package com.tianxing.ui.fragment.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.tianxing.fscteachersedition.R;
import com.tianxing.presenter.main.ContactsPresenter;
import com.tianxing.presenter.main.ContactsViewPresenter;
import com.tianxing.ui.activity.MainActivity;
import com.tianxing.ui.adapter.ContactsListAdapter;
import com.tianxing.ui.fragment.BaseFragment;
import com.tianxing.ui.view.PinnedSectionListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by tianxing on 16/7/11.
 */
public class ContactsFragment extends BaseFragment implements ContactsView, AdapterView.OnItemClickListener{
    public static final String TAG = "ContactsFragment";

    private static ContactsFragment fragment = null;


    //@BindView(R.id.expandableListView)
    //ExpandableListView expandableListView;
    @BindView(R.id.pinnedSectionListView_contacts)
    PinnedSectionListView listView;
    private Unbinder unbinder;

    private ContactsPresenter presenter;



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
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        unbinder = ButterKnife.bind(this, view);
        //ExpandableViewInitialize();
        //expandableListView.setAdapter(new ContactsExpandableListAdapter(getContext(), presenter));
        //expandableListView.setOnChildClickListener(this);
        listView.setAdapter(new ContactsListAdapter(getContext(), presenter));
        listView.setOnItemClickListener(this);
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
     * Callback method to be invoked when an item in this AdapterView has
     * been clicked.
     * <p/>
     * Implementers can call getItemAtPosition(position) if they need
     * to access the data associated with the selected item.
     *
     * @param parent   The AdapterView where the click happened.
     * @param view     The view within the AdapterView that was clicked (this
     *                 will be a view provided by the adapter)
     * @param position The position of the view in the adapter.
     * @param id       The row id of the item that was clicked.
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.e(TAG, String.valueOf(presenter.getItemTitle(position)));
        //启动聊天界面
        ((MainActivity)getActivity()).startChatFragment(presenter.getParentPosition(position), presenter.getChildPosition(position));

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
