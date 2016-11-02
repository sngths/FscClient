package com.tianxing.ui.fragment.child;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.tianxing.fscteachersedition.R;
import com.tianxing.ui.activity.MainView;
import com.tianxing.ui.fragment.BaseFragment;

/**
 * Created by tianxing on 16/7/27.
 * 处理Fragment返回
 */
public class BaseBackFragment extends BaseFragment {

    private String title = "";
    private TextView textView;

    /**
     * 初始化标题栏
     * */
    protected void toolBarInit(Toolbar toolbar){
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainView)getActivity()).popBack();
                hideKeyboard();
            }
        });
        textView = ((TextView)toolbar.findViewById(R.id.textView_toolBar_title));
        textView.setText(title);
    }


    /**
     * 设置标题栏
     * */
    public void setToolBarTitle(String title){
        this.title = title;
        if (textView != null){
            textView.setText(title);
        }
    }

    /**
     * 标题栏右侧按钮点击监听
     * */
    void setRightButtonOnClickListener(){

    }


    /**
     * 隐藏软键盘
     * */
    protected void hideKeyboard(){
        View view = this.getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
