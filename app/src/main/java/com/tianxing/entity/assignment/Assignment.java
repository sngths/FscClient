package com.tianxing.entity.assignment;

/**
 * Created by tianxing on 16/7/15.
 */
public class Assignment {


    private String serialNumber = "";
    private String teacherName = "";
    private String className = "";



    //作业信息
    private String title = "";
    private String date = "";
    private String content;
    private String image;

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getClassName() {
        return className;
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

    public String getImage() {
        return image;
    }
}
