package com.tianxing.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.tianxing.presenter.main.AssignmentPresenter;
import com.tianxing.ui.fragment.main.AssignmentListFragment;

import java.util.HashMap;

/**
 * Created by tianxing on 16/7/12.
 * 作业查看界面ViewPager适配器  处理Fragment的销毁
 */
public class AssignemntViewPagerAdapter extends FragmentStatePagerAdapter {

    private String tabTitle[] = {"一年级", "二年级", "三年级"};
    private AssignmentPresenter presenter;//获取ViewPager相关数据信息
    private HashMap<Integer, AssignmentListFragment> fragments = new HashMap<>();//保存Fragment引用
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
        AssignmentListFragment fragment = AssignmentListFragment.newInstance(position);
        fragments.put(position, fragment);
        return fragment;
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

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        //销毁Item时解除Fragment的引用
        fragments.remove(position);
    }

    /**
     * 找到对应位置的Fragment实例 如果不存在则返回null
     * */
    public AssignmentListFragment findItem(Integer position){
        return fragments.get(position);
    }

}
