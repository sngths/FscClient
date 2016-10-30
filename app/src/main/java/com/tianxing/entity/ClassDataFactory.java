package com.tianxing.entity;

import com.tianxing.entity.info.ClassInfo;

/**
 * Created by tianxing on 16/7/20.
 */
public class ClassDataFactory {

    public static ClassData getNewClassData(ClassInfo classInfo){
        ClassData classData = new ClassData();
        classData.setClassID(classInfo.getId());
        classData.setTitle(classInfo.getName());

        return classData;
    }
}
