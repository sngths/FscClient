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
import com.tianxing.ui.adapter.ContactsExpandableViewAdaper;
import com.tianxing.ui.adapter.ContactsParentItemList;
import com.tianxing.ui.fragment.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by tianxing on 16/7/11.
 */
public class ContactsFragment extends BaseFragment implements ExpandableRecyclerAdapter.ExpandCollapseListener{
    public static final String TAG = "ContactsFragment";

    private static ContactsFragment fragment = null;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private Unbinder unbinder;

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
        //创建可折叠列表

        ArrayList<ContactsParentItemList> parentItemLists = new ArrayList<>();
        parentItemLists.add(new ContactsParentItemList());
        parentItemLists.add(new ContactsParentItemList());
        ContactsExpandableViewAdaper expandableViewAdaper = new ContactsExpandableViewAdaper(getContext(), parentItemLists);
        expandableViewAdaper.setExpandCollapseListener(this);
        recyclerView.setAdapter(expandableViewAdaper);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
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
}
