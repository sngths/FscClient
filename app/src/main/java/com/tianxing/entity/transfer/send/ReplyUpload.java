package com.tianxing.entity.transfer.send;

import android.media.Image;

import java.util.List;

/**
 * Created by tianxing on 16/11/1.
 */

public class ReplyUpload {

    private String assignmentID;


    //回复内容
    private String title;
    private String content;
    private List<Image> images;



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

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
