package com.tianxing.pojo.transfer.receive;

import com.tianxing.deprecated.entity.http.json.ImageFile;
import com.tianxing.pojo.transfer.Comment;

import java.util.List;

/**
 * Created by tianxing on 16/11/1.
 * 接收到的一条学生作业回复
 */

public class ReplyReceived {

    private String id;
    private String assignmentID;
    private String studentID;
    private String teacherID;


    //回复内容
    private String title;
    private String date;
    private String content;
    private List<ImageFile> images;

    //教师评语
    private Comment comment;

    public String getId() {
        return id;
    }

    public String getAssignmentID() {
        return assignmentID;
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

    public List<ImageFile> getImages() {
        return images;
    }

    public Comment getComment() {
        return comment;
    }
}
