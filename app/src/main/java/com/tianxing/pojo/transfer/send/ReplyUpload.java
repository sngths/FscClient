package com.tianxing.pojo.transfer.send;

import com.tianxing.deprecated.entity.http.json.ImageFile;

import java.util.List;

/**
 * Created by tianxing on 16/11/1.
 */

public class ReplyUpload {

    private String assignmentID;


    //回复内容
    private String title;
    private String content;
    private List<ImageFile> images;



    public String getAssignmentID() {
        return assignmentID;
    }

    public void setAssignmentID(String assignmentID) {
        this.assignmentID = assignmentID;
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

    public List<ImageFile> getImages() {
        return images;
    }

    public void setImages(List<ImageFile> images) {
        this.images = images;
    }
}
