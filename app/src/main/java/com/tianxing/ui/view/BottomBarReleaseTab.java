package com.tianxing.ui.view;

import android.content.Context;
import android.support.annotation.DrawableRes;

/**
 * Created by tianxing on 16/7/26.
 */
public class BottomBarReleaseTab extends BottomBarTab {



    public BottomBarReleaseTab(Context context){
        this(context, -1, null);
    }

    public BottomBarReleaseTab(Context context, @DrawableRes int icon, CharSequence title) {
        super(context, icon, title);
    }





}
