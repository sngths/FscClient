package com.tianxing.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.tianxing.presenter.main.AssignmentPresenter;
import com.tianxing.ui.fragment.main.AssignmentListFragment;

/**
 * Created by tianxing on 16/7/12.
 * 作业查看界面ViewPager适配器  处理Fragment的销毁
 */
public class AssignemntViewPagerAdapter extends FragmentStatePagerAdapter {

    private String tabTitle[] = {"一年级", "二年级", "三年级"};
    private AssignmentPresenter presenter;//获取ViewPager相关数据信息

    public AssignemntViewPagerAdapter(FragmentManager fm, AssignmentPresenter presenter) {
        super(fm);
        this.presenter = presenter;
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @Override
    public Fragment getItem(int position) {
        //ViewPager会完全销毁Fragment 只保存Fragment状态数据   每次需要时重新请求Fragment
        return AssignmentListFragment.newInstance(position);
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return presenter.getClassCount();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return presenter.getPageTitle(position);
    }
}
