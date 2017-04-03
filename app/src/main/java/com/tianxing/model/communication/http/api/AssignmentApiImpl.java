package com.tianxing.model.communication.http.api;

import com.tianxing.model.communication.http.service.AssignmentService;

/**
 * Created by tianxing on 2017/3/31.
 */

public class AssignmentApiImpl implements AssignmentApi {

    private static final Class<AssignmentService> CLASS = AssignmentService.class;


    private AssignmentApiImpl(Class<AssignmentService> serviceClass){

    }

    /**
     * 取得Service接口
     */
    @Override
    public Class<AssignmentService> getServiceInterface() {
        return null;
    }





    /**
     * 连接网络
     */

}
