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

    public int getAssignmentID() {
        return assignmentID;
    }

    public void setAssignmentID(int assignmentID) {
        this.assignmentID = assignmentID;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getModificationime() {
        return modificationime;
    }

    public void setModificationime(long modificationime) {
        this.modificationime = modificationime;
    }
}

