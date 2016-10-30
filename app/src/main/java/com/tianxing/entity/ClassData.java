package com.tianxing.entity;

import com.tianxing.entity.assignment.AssignmentDownload;

import java.util.ArrayList;

/**
 * Created by tianxing on 16/7/18.
 * 包含单个班级的 作业数据等信息
 */
public class ClassData {


    private String ClassID;
    private String Title;

    private ArrayList<AssignmentDownload> assignmentDownloadList = new ArrayList<>();



    public ClassData(){
    }

    public Integer getAssigmnetsCount(){
        return assignmentDownloadList.size();
    }

    public String getClassID() {
        return ClassID;
    }

    public String getTitle() {
        return Title;
    }

    public AssignmentDownload getAssignment(int position){
        return assignmentDownloadList.get(position);
    }

    public void setClassID(String classID) {
        ClassID = classID;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void putAssignment(AssignmentDownload assignmentDownload){
        assignmentDownloadList.add(assignmentDownload);
    }
}
