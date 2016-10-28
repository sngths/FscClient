package com.tianxing.entity;

/**
 * Created by tianxing on 16/7/20.
 */
public class ClassDataFactory {

    public static ClassData getNewClassData(String title){
        ClassData classData = new ClassData();
        classData.setTitle(title);
        /*int a = 5;
        for (int i = 0; i < a; i++) {
            classData.putAssignment(new AssignmentDownload());
        }*/
        return classData;
    }
}
