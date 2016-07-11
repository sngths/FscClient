package com.tianxing.ui.fragment.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tianxing.fscteachersedition.R;
import com.tianxing.ui.fragment.BaseFragment;

/**
 * Created by tianxing on 16/7/11.
 */
public class ReviewFragment extends BaseFragment {
    public static final String TAG = "ReviewFragment";

    private static ReviewFragment fragment = null;


    public static ReviewFragment getInstance(){
        if (fragment == null){
            fragment = new ReviewFragment();
        }
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_review, container, false);
        return view;
    }
}
