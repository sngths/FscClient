package com.tianxing.data.pool;

import com.tianxing.data.visitor.Visitor;

/**
 * Created by tianxing on 2017/4/6.
 */

public abstract class AbstractDataPool implements DataPool {

    @Override
    public <T extends Visitor> void accept(T t) {
        t.visit(this);
    }
}
