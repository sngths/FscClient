package com.tianxing.ui.fragment.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tianxing.fscteachersedition.R;
import com.tianxing.ui.fragment.BaseFragment;

/**
 * Created by tianxing on 16/7/26.
 */
public class AssignemntReleaseFragment extends BaseFragment {
    public static final String TAG = "AssignemntReleaseFragment";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_assignment_release, container, false);
        return view;
    }
}
