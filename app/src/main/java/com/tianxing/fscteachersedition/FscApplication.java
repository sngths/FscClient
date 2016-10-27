package com.tianxing.fscteachersedition;

import android.app.Application;
import android.content.res.Configuration;

import com.tianxing.util.ScreenSize;

/**
 * Created by tianxing on 16/7/5.
 */
public class FscApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ScreenSize.initialize(getApplicationContext());
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }


    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
