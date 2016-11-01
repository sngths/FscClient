package com.tianxing.ui.fragment.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.tianxing.fscteachersedition.R;
import com.tianxing.presenter.child.AssignmentDetailPresenter;
import com.tianxing.presenter.child.AssignmentDetailViewPresenter;
import com.tianxing.ui.activity.MainView;
import com.tianxing.ui.adapter.AssignmentDetailListAdapterDeprecated;
import com.tianxing.ui.view.PinnedSectionListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by tianxing on 16/7/25.
 *
 */
public class AssignmentDetailFragmentDeprecated extends BaseBackFragment implements AssignmentDetailView, AdapterView.OnItemClickListener{

    private Unbinder unbinder;
    @BindView(R.id.toolbar_child_Fragment)
    Toolbar toolbar;
    @BindView(R.id.pinnedSectionListView_detail)
    PinnedSectionListView listView;

    private AssignmentDetailPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //取出作业位置信息 创建Presenter
        presenter = new AssignmentDetailViewPresenter(getArguments().getString("classID"), getArguments().getInt("position"));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_assignment_detail_deprecated, container, false);
        unbinder = ButterKnife.bind(this, view);
        toolBarInit(toolbar);
        setToolBarTitle("作业详情");
        listView.setAdapter(new AssignmentDetailListAdapterDeprecated(getContext(), presenter));
        listView.setOnItemClickListener(this);
        return view;
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
        //启动回复详情界面
        Log.e(view.toString(), "  " + String.valueOf(position));
        ((MainView)getActivity()).startAssignmentReplyFragment();
    }
}
