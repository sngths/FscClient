package com.tianxing.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.tianxing.ui.fragment.main.AssignmentListFragment;

/**
 * Created by tianxing on 16/7/12.
 */
public class AssignemntAdapter extends FragmentStatePagerAdapter {

    private String tabTitle[] = {"一年级", "二年级", "三年级", "一年级", "二年级", "三年级"
            , "一年级", "二年级", "三年级", "一年级", "二年级", "三年级", "二年级", "三年级"
            , "一年级", "二年级", "三年级", "一年级", "二年级", "三年级", "一年级", "二年级"
            , "三年级", "二年级", "三年级", "一年级", "二年级", "三年级", "一年级", "二年级"
            , "三年级", "一年级", "二年级", "三年级"};

    public AssignemntAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @Override
    public Fragment getItem(int position) {
        return new AssignmentListFragment();
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitle[position];
    }
}
