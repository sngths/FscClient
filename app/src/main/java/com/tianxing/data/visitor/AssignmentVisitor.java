package com.tianxing.data.visitor;


import com.tianxing.data.pool.AssignmentPool;
import com.tianxing.data.pool.DataPool;

/**
 * Created by tianxing on 2017/4/6.
 *
 */

public class AssignmentVisitor implements Visitor {


    private AssignmentPool assignmentPool;

    

    @Override
    public <T extends DataPool> void visit(T t) {
        assignmentPool = t instanceof AssignmentPool ? ((AssignmentPool) t) : null;
    }


}
