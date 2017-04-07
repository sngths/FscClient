package com.tianxing.deprecated.entity.info;

import java.util.List;

/**
 * Created by tianxing on 16/7/13.
 */
//@JsonIgnoreProperties(ignoreUnknown = true)
public class ClassInfo extends GroupInfo implements InfoEntity {

    private String id;//班级名称
    private String name;//班级标题
    private String icon;//图标地址

    private List<StudentInfo> students;//班级所学生列表
    private List<TeacherInfo> teachers;//班级老师列表

    public List<TeacherInfo> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<TeacherInfo> teachers) {
        this.teachers = teachers;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setStudents(List<StudentInfo> students) {
        this.students = students;
    }



    public ClassInfo(){
        //students = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    /**
     * 取得学生总数
     * */
    public Integer getStudentCount(){
        return students.size();
    }

    /**
     * 取得学生信息列表
     * */
    public List<StudentInfo> getStudents() {
        return students;
    }

    /**
     * 取得对应位置的一个学生信息
     * */
    public UserInfo getStudentInfo(Integer position){
        return students.get(position);
    }


    /**
     * 添加一个学生信息
     * */
    public void putUserInfo(StudentInfo studentInfo){
        students.add(studentInfo);
    }
}
