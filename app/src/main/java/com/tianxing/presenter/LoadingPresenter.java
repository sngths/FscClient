package com.tianxing.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.tianxing.model.App;
import com.tianxing.data.Config;
import com.tianxing.data.LocalConfig;

/**
 * Created by tianxing on 16/7/8.
 */
public class LoadingPresenter {

    private Boolean isInitialized = false;
    private App app;

    public LoadingPresenter(){
        app = App.getInstance();
    }


    /**
     * 应用初始化 不持有Context的引用
     * */
    public void appInitializ(Context context){
        if (!isInitialized){
            Config config = new Config();
            config.setLocalConfig(readLocalConfig(context));
            app.initialize(config);
        }
        isInitialized = true;
    }


    /**
     * 取得本地配置
     * */
    public LocalConfig getLocalConfig(){
        return app.getConfig().getLocalConfig();
    }






    /**
     * 从SharePreference中取得配置参数
     * */
    private LocalConfig readLocalConfig(Context context){
        LocalConfig config = new LocalConfig();

        SharedPreferences setting = context.getSharedPreferences(LocalConfig.PreferenceConfig, Context.MODE_PRIVATE);
        String refreshToken = setting.getString("REFRESH_TOKEN", "");
        config.setRefresh_token(refreshToken);



        return config;
    }
}
