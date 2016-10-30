package com.tianxing.entity.assignment;

import com.tianxing.entity.http.json.ImageFile;

import java.util.List;

/**
 * Created by tianxing on 16/7/15.
 * 网络获取的一条作业数据
 */
public class AssignmentDownload {


    private String serialNumber;
    private String teacherName;
    private String className;

    private String classID;



    //作业信息
    private String title;
    private String date;
    private String content;

    private List<ImageFile> images;

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getClassName() {
        return className;
    }

    public String getClassID() {
        return classID;
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

    public List<ImageFile> getImages() {
        return images;
    }


}
