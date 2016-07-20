package com.tianxing.entity;

import com.tianxing.entity.assignment.Assignment;

import java.util.ArrayList;

/**
 * Created by tianxing on 16/7/18.
 * 包含单个班级的 作业数据等信息
 */
public class ClassData {


    private String ClassID = "";
    private String Title = "";

    private ArrayList<Assignment> assignmentList = new ArrayList<>();



    public ClassData(){

    }

    public Integer getAssigmnetsCount(){
        return assignmentList.size();
    }

    public String getClassID() {
        return ClassID;
    }

    public String getTitle() {
        return Title;
    }

    public Assignment getAssignment(int position){
        return assignmentList.get(position);
    }

    public void setClassID(String classID) {
        ClassID = classID;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void putAssignment(Assignment assignment){
        assignmentList.add(assignment);
    }
}
