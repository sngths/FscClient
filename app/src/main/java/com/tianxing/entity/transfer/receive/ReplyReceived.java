package com.tianxing.entity.transfer.receive;

import android.media.Image;

import java.util.List;

/**
 * Created by tianxing on 16/11/1.
 * 接收到的一条学生作业回复
 */

public class ReplyReceived {
    private String id;
    private String assignemntID;
    private String classID;
    private String studentID;
    private String teacherID;


    //回复内容
    private String title;
    private String date;
    private String content;
    private List<Image> images;

    public String getId() {
        return id;
    }

    public String getAssignemntID() {
        return assignemntID;
    }

    public String getClassID() {
        return classID;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getTeacherID() {
        return teacherID;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }

    public List<Image> getImages() {
        return images;
    }
}
