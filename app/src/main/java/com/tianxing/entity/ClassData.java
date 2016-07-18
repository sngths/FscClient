package com.tianxing.entity;

import com.tianxing.entity.assignment.Assignment;

import java.util.ArrayList;

/**
 * Created by tianxing on 16/7/18.
 * 包含单个班级的 作业数据等信息
 */
public class ClassData {

    private String Title;

    private ArrayList<Assignment> classDatas = new ArrayList<>();



    public ClassData(){

    }

    /**
     * 初始化时放入一些数据
     * */
    private void init(){

    }
}
