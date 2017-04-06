package com.tianxing.model.communication;

import com.tianxing.model.communication.http.api.RetrofitApi;
import com.tianxing.model.communication.http.api.RxApi;
import com.tianxing.model.communication.http.service.Service;



/**
 * Created by tianxing on 2017/3/30.
 * 不包含具体的 请求方法
 */

public interface HttpClient {




    /**
     * 尝试连接服务器
     * */


    /**
     * 取得服务接口
     * */
    <T extends Service> T getService(Class<T> service);



    /**
     * 文件上传下载
     * */
}
