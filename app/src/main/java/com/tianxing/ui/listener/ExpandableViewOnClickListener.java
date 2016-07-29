package com.tianxing.ui.listener;

/**
 * Created by tianxing on 16/7/29.
 */
public interface ExpandableViewOnClickListener {


    /**
     * 折叠列表点击
     * @param position  被点击的子项在折叠列表完全展开时所在的位置
     * */
    void OnClick(Integer position);
}
