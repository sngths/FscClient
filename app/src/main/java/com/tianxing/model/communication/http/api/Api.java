package com.tianxing.model.communication.http.api;

/**
 * Created by tianxing on 2017/3/31.
 * 包含网络连接的基本接口
 */

public interface Api<S> {




    /**
     * 取得Service接口
     * */
    Class<S> getServiceInterface();

    /**
     * 连接网络
     * */

}
