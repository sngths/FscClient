package com.tianxing.entity.assignment;

/**
 * Created by tianxing on 2017/4/7.
 *
 */

public class AbstractAssignment implements Assignment {

    private int assignmentID;

    private int teacherID;
    private String teacherName;
    private int classID;
    private String className;

    private long timestamp;//创建时间
    private long modificationime;//修改时间


}

