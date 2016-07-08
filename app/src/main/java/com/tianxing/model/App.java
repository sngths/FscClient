package com.tianxing.model;

/**
 * Created by tianxing on 16/7/8.
 */
public class App {

    private static App model = null;

    private App(){}
    /**
     * 不支持多线程调用
     * */
    public static App getInstance(){
        if (model == null){
            model = new App();
        }
        return model;
    }


    /**
     *
     * */
    public void Initializ(){

    }


}
