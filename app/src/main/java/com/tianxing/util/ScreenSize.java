package com.tianxing.util;

import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by tianxing on 16/1/15.
 * 屏幕像素格式转换
 */
public class ScreenSize {
    private static final String TAG = "ScreenSize";
    private static Float scale;
    private static Boolean isInitialized = false;
    private static int widthPx;
    private static int heightPx;
    private static int widthDp;
    private static int heightDp;

    public static void initialize(Context context){
        //取得屏幕参数
        if (!isInitialized){
            scale = context.getResources().getDisplayMetrics().density;
            Log.e(TAG, String.valueOf(scale));
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point point = new Point();
            display.getSize(point);
            widthPx = point.x;
            heightPx = point.y;
            widthDp = px2dp(widthPx);
            heightDp = px2dp(heightPx);
            isInitialized = true;
        }
    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px(float dpValue) {
        return (int) (dpValue * scale + 0.5f);
    }


    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dp(float pxValue) {
        return (int) (pxValue / scale + 0.5f);
    }


    /**
     * 取得像素宽度
     * */
    public static int getWidthPx() {
        return widthPx;
    }


    /**
     * 取得像素高度
     * */
    public static int getHeightPx() {
        return heightPx;
    }


    /**
     * 取得dp宽度
     * */
    public static int getWidthDp() {
        return widthDp;
    }


    /**
     * 取得dp高度
     * */
    public static int getHeightDp() {
        return heightDp;
    }
}

