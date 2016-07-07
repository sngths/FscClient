package com.tianxing.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by tianxing on 16/7/7.
 * 在Fragment基类中保存Fragment的显示状态 解决内存重启 多Fragment显示重叠问题
 */
public class BaseFragment extends Fragment {
    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //内存恢复时取出原先的显示状态 设置是否显示Fragment
        if (savedInstanceState != null){
            Boolean isHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            if (isHidden){
                transaction.hide(this);
            }else {
                transaction.show(this);
            }
            transaction.commit();
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
    }
}
