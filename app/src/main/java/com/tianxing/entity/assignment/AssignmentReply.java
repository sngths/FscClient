package com.tianxing.entity.assignment;

import com.tianxing.entity.file.ImageFile;

import java.util.List;

/**
 * Created by tianxing on 2017/4/7.
 * 学生作业回复
 */

public class AssignmentReply extends AbstractAssignment{


    private int replyId;

    private int studentId;
    private String studentName;


    private String title;
    private String content;
    private List<ImageFile> images;

    public int getReplyId() {
        return replyId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
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
