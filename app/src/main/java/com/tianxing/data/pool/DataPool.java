package com.tianxing.data.pool;

import com.tianxing.data.visitor.Visitor;

/**
 * Created by tianxing on 2017/4/6.
 * 所有数据池继承该接口
 */

public interface DataPool<T extends Visitor> {

    void accept(T t);
}
