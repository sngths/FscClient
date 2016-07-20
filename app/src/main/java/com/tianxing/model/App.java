package com.tianxing.model;

import com.tianxing.model.data.AssignmentDataPool;
import com.tianxing.model.data.Config;

/**
 * Created by tianxing on 16/7/8.
 */
public class App {

    private static App model = null;
    private Config config;

    private AssignmentPool assignmentPool;

    private App(){}


    /**
     *
     * */
    public static App getInstance(){
        if (model == null){
            model = new App();
        }
        return model;
    }


    /**
     * 全局初始化
     * */
    public void initialize(Config config){
        this.config = config;
        assignmentPool = new AssignmentDataPool();//初始化作业查看数据池
    }

    public Config getConfig() {
        return config;
    }

    public AssignmentPool getAssignmentPool() {
        return assignmentPool;
    }
}
