package com.tianxing.entity;

import com.tianxing.entity.assignment.Assignment;

import java.util.Random;

/**
 * Created by tianxing on 16/7/20.
 */
public class ClassDataFactory {

    public static ClassData getNewClassData(String title){
        ClassData classData = new ClassData();
        classData.setTitle(title);
        Random random = new Random(20);
        int a = 5;
        for (int i = 0; i < a; i++) {
            classData.putAssignment(new Assignment());
        }
        return classData;
    }
}
