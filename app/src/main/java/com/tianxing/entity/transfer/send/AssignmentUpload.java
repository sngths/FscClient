package com.tianxing.entity.transfer.send;

import com.tianxing.deprecated.entity.http.json.ImageFile;

import java.util.List;

/**
 * Created by tianxing on 16/10/27.
 * 上传作业类
 */

public class AssignmentUpload {



    private String title;

    private String content;

    private String classID;


    private List<ImageFile> images;

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

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public List<ImageFile> getImages() {
        return images;
    }

    public void setImages(List<ImageFile> images) {
        this.images = images;
    }

}
