package com.tianxing.data.pool;

/**
 * Created by tianxing on 2017/4/6.
 */

public interface AssignmentPool extends DataPool {

    /**
     * 调整作业池大小
     * @param capability 每个班级的作业列表缓存数目
     * */
    void resize(int capability);


    /**
     * 检查当前容量
     * */
}
