package com.tianxing.model.user;

import com.tianxing.deprecated.entity.info.TeacherInfo;

/**
 * Created by tianxing on 16/10/28.
 */

public class TeacherUser extends User {

    private TeacherInfo info;


    @Override
    public UserType getUserType() {
        return UserType.teacher;
    }

    public TeacherInfo getInfo() {
        return info;
    }

    public void setInfo(TeacherInfo info) {
        this.info = info;
    }
}
