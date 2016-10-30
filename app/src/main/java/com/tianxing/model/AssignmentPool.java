package com.tianxing.model;

import com.tianxing.entity.ClassData;
import com.tianxing.entity.info.ClassInfo;

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
    void createClassData(ClassInfo classInfo);



    /**
     * 取得对应位置的一个班级数据组
     * */
    T getClassData(int position);

    /**
     * 取得对应classID 的一个班级数据组
     * */
    T getClassData(String classID);

    /**
     * 根据班级ID取得一个班级数据组位置
     * */
    Integer getClassDataPosition(String classID);




}
