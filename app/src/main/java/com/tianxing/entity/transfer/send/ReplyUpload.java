package com.tianxing.entity.transfer.send;

import android.media.Image;

import java.util.List;

/**
 * Created by tianxing on 16/11/1.
 */

public class ReplyUpload {

    private String id;
    private String assignemntID;
    private String classID;
    private String studentID;
    private String teacherID;


    //回复内容
    private String title;
    private String content;
    private List<Image> images;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAssignemntID() {
        return assignemntID;
    }

    public void setAssignemntID(String assignemntID) {
        this.assignemntID = assignemntID;
    }

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
