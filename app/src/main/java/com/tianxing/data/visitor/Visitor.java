package com.tianxing.data.visitor;

import com.tianxing.data.pool.DataPool;

/**
 * Created by tianxing on 2017/3/29.
 * 数据观察者
 */

public interface Visitor {
    <T extends DataPool>void visit(T t);
}
