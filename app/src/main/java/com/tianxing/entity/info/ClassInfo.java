package com.tianxing.entity.info;

import java.util.List;

/**
 * Created by tianxing on 16/7/13.
 */
//@JsonIgnoreProperties(ignoreUnknown = true)
public class ClassInfo implements InfoEntity {

    private String name = "";//班级名称
    private String title = "";//班级标题

    private List<StudentInfo> students;

    public ClassInfo(){
        //students = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
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
