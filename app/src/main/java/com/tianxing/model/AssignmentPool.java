package com.tianxing.model;

import com.tianxing.entity.ClassData;

/**
 * Created by tianxing on 16/7/18.
 * 作业查看相关数据的操作入口
 */
public interface AssignmentPool <T extends ClassData>{



    /**
     * 取得班级总数
     * */
    Integer getClassCount();


    /**
     * 创建一个班级数据
     * */
    void createClassData(String title);



    /**
     * 取得对应位置的一个班级数据组
     * */
    T getClassData(int position);




}
