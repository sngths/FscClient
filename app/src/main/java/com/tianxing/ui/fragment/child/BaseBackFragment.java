package com.tianxing.ui.fragment.child;

import android.support.v7.widget.Toolbar;
import android.view.View;

import com.tianxing.fscteachersedition.R;
import com.tianxing.ui.activity.MainView;
import com.tianxing.ui.fragment.BaseFragment;

/**
 * Created by tianxing on 16/7/27.
 * 处理Fragment返回
 */
public class BaseBackFragment extends BaseFragment {


    /**
     *
     * */
    protected void toolBarInit(Toolbar toolbar){
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainView)getActivity()).popBack();
            }
        });
    }

}
