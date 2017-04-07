package com.tianxing.data;

import com.tianxing.data.pool.DataPool;
import com.tianxing.data.visitor.Visitor;


/**
 * Created by tianxing on 2016/10/30.
 * 本地数据管理入口
 */

public interface DataManager {


    /**
     * 初始化所有数据池
     * */
    void initialize();

    /**
     * 取得数据池
     * */
    <T extends DataPool> T getDataPool(Class<T> dataPool);


    /**
     * 接受数据访问者
     * */
    void accept(Visitor visitor);

    /**
     * 清除所有数据
     * */

}
