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
    private int studentName;


    private String title;
    private String contact;
    private List<ImageFile> images;

}
