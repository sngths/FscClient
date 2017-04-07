package com.tianxing.deprecated.entity.info;

import java.util.List;

/**
 * Created by tianxing on 16/9/8.
 *
 */
public class TeacherInfo extends UserInfo {

    private String courseID;//科目ID
    private String courseName;//科目名称
    private List<String> classIDs;//班级ID列表

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<String> getClassIDs() {
        return classIDs;
    }

    public void setClassIDs(List<String> classIDs) {
        this.classIDs = classIDs;
    }
}
