package com.tianxing.model.user;

import com.tianxing.entity.info.StudentInfo;

/**
 * Created by tianxing on 16/10/28.
 */

public class StudentUser extends User {


    private StudentInfo info;


    @Override
    public UserType getUserType() {
        return UserType.student;
    }


    public StudentInfo getInfo() {
        return info;
    }

    public void setInfo(StudentInfo info) {
        this.info = info;
    }
}
